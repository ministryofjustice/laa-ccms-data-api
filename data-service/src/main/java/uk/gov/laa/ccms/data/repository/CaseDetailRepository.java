package uk.gov.laa.ccms.data.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.CaseInqRSXml;

@Slf4j
@Repository
public class CaseDetailRepository {

  private final JdbcTemplate jdbcTemplate;
  private final XmlMapper xmlMapper;

  public CaseDetailRepository(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
    this.xmlMapper = new XmlMapper();
    this.xmlMapper.registerModule(new JavaTimeModule());

    // TODO: Temporary flag, should really map everything
    this.xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public CaseInqRSXml getCaseDetailXml(String caseReference, Long providerId, String clientFirstName)
      throws SQLException, JsonProcessingException {
    String caseDetailViaFunction = getCaseDetailViaFunction(caseReference, providerId,
        clientFirstName);
    return this.xmlMapper.readValue(caseDetailViaFunction, CaseInqRSXml.class);
  }

  @Deprecated
  public static class SqlStructMapper implements SqlReturnType {

    @Override
    public Object getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName) throws SQLException {
      Struct struct = (Struct) cs.getObject(paramIndex);
      return struct;
    }
  }

  @Deprecated
  private Struct getCaseDetailObjectViaFunction(String caseReference, Long providerId, String clientFirstName)
      throws SQLException {
    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
        .withSchemaName("XXCCMS")
        .withCatalogName("XXCCMS_SOA_REPLACE_PKG")
        .withFunctionName("GET_CASE_DETAILS_OBJECT")
        .declareParameters(
            new SqlOutParameter("result", Types.STRUCT, "XXCCMS.XXCCMS_CASE_TYPE", new SqlStructMapper()),
            new SqlParameter("p_case_reference_number", Types.VARCHAR),
            new SqlParameter("l_provider_firm_party_id", Types.NUMERIC),
            new SqlParameter("p_user_name", Types.VARCHAR)
        );

    Map<String, Object> params = new HashMap<>();
    params.put("p_case_reference_number", caseReference);
    params.put("l_provider_firm_party_id", providerId);
    params.put("p_user_name", clientFirstName);

    Struct result = jdbcCall.executeFunction(Struct.class, params);

    for(var field : result.getAttributes()){
      log.info(String.valueOf(field));
    }

    return result;
  }

  private String getCaseDetailViaFunction(String caseReference, Long providerId, String clientFirstName)
      throws SQLException {
    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
        .withSchemaName("XXCCMS")
        .withCatalogName("XXCCMS_SOA_REPLACE_PKG")
        .withFunctionName("GET_CASE_DETAILS")
        .withoutProcedureColumnMetaDataAccess()
        .declareParameters(
            new SqlOutParameter("clob", Types.CLOB),
            new SqlParameter("p_case_reference_number", Types.VARCHAR),
            new SqlParameter("p_user_providerfirm_id", Types.NUMERIC),
            new SqlParameter("p_user_name", Types.VARCHAR)
        );

    Map<String, Object> params = new HashMap<>();
    params.put("p_case_reference_number", caseReference);
    params.put("p_user_providerfirm_id", providerId);
    params.put("p_user_name", clientFirstName);

    Clob result = jdbcCall.executeFunction(Clob.class, params);

    // TODO: Temporarily removing Awards as for some reason the DB adds a rogue closing tag
    //  for awards
    return result.getSubString(1, (int) result.length()).replaceAll("</case:Awards>", "");
  }
}
