package uk.gov.laa.ccms.data.repository;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.mapper.xml.CaseInqRSXml;

@Slf4j
@Repository
@AllArgsConstructor
public class CaseDetailRepository {

  private final JdbcTemplate jdbcTemplate;

  public CaseInqRSXml getCaseDetail(String caseReference, Long providerId, String clientFirstName)
      throws SQLException, JsonProcessingException {

    return getCaseDetailViaFunction(caseReference, providerId, clientFirstName);
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
  private String getCaseDetailObjectViaFunction(String caseReference, Long providerId, String clientFirstName)
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

    return result.toString();
  }

  private CaseInqRSXml getCaseDetailViaFunction(String caseReference, Long providerId, String clientFirstName)
      throws SQLException, JsonProcessingException {
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

    // This turns into an XML format
    String clobString = result.getSubString(1, (int) result.length());

    // TODO: Temporarily removing Awards as for some reason the DB adds a rogue closing tag
    //  for awards
    clobString = clobString.replaceAll("</case:Awards>", "");
    XmlMapper xmlMapper = new XmlMapper();
    // TODO: Temporary flag, should really map everything
    xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return xmlMapper.readValue(clobString, CaseInqRSXml.class);
  }
}
