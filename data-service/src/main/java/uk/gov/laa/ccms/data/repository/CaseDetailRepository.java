package uk.gov.laa.ccms.data.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.CaseInqRsXml;

/**
 * Repository class for retrieving case details using database functions.
 *
 * <p>This class provides methods for interacting with stored database functions
 * to retrieve detailed information about cases. It utilizes Spring's
 * {@link JdbcTemplate}, Jackson's {@link XmlMapper}, and custom database
 * functions to fetch case information in XML format and map it to objects.</p>
 *
 * @author Jamie Briggs
 */
@Slf4j
@Repository
public class CaseDetailRepository {

  private final JdbcTemplate jdbcTemplate;
  private final XmlMapper xmlMapper;

  /**
   * Constructs a new instance of {@link CaseDetailRepository}, which is responsible for
   * initializing the required database access components and configuring the XML mapper
   * for deserializing XML data into Java objects.
   *
   * @param jdbcTemplate the {@link JdbcTemplate} instance used for interacting with the database.
   */
  public CaseDetailRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.xmlMapper = new XmlMapper();
    this.xmlMapper.registerModule(new JavaTimeModule());
    this.xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * Retrieves case details in XML format by invoking a database function and mapping the result
   *     to a {@code CaseInqRsXml} object.
   *
   * <p>This method uses a stored database function to fetch case details based on the
   *     provided case reference, provider ID, and client first name. The resulting XML
   *     data is deserialized into a {@code CaseInqRsXml} object.</p>
   *
   * @param caseReference the unique reference identifier for the case
   * @param providerId the identifier of the provider associated with the case
   * @param clientFirstName the first name of the client associated with the case
   * @return a {@code CaseInqRsXml} object containing the case details mapped from the XML data
   * @throws SQLException if a database access error occurs or there is an error with the
   *     SQL function invocation
   * @throws JsonProcessingException if an error occurs while deserializing the XML response
   *     into the {@code CaseInqRsXml} object
   */
  public CaseInqRsXml getCaseDetailXml(String caseReference, Long providerId,
      String clientFirstName) throws SQLException {
    String caseDetailViaFunction = getCaseDetailViaFunction(caseReference, providerId,
        clientFirstName);
    try{
      return this.xmlMapper.readValue(caseDetailViaFunction, CaseInqRsXml.class);
    }catch (JsonProcessingException e){
      // Due to how the EBS function works, if the case can't be found, XML is still returned but
      //  in a bad format. Just return null if this is the case.
      return null;
    }
  }

  private String getCaseDetailViaFunction(String caseReference, Long providerId,
      String clientFirstName) throws SQLException {
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

    return result.getSubString(1, (int) result.length());
  }


}
