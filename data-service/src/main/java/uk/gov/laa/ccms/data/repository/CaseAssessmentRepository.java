package uk.gov.laa.ccms.data.repository;

import java.io.Reader;
import java.sql.Array;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.laa.ccms.data.exception.EbsApiRuntimeException;
import uk.gov.laa.ccms.data.model.AssessmentType;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetail;

/**
 * {@link CaseAssessmentRepository} is a data access class for retrieving case assessment details
 * from the database. It uses {@link JdbcTemplate} to interact with the "XXCCMS_SOA_REPLACE"
 * package to utilize the "GET_ASSESSMENT_DETAILS" function.
 *
 * @see JdbcTemplate
 * @see CaseAssessmentDetail
 * @author Jamie Briggs
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class CaseAssessmentRepository {

  private final JdbcTemplate jdbcTemplate;

  /**
   * Retrieves a list of case assessment details associated with a specific case reference
   * and assessment type. The details are fetched using a database call to the "XXCCMS_SOA_REPLACE"
   * package, containing the "GET_ASSESSMENT_DETAILS" function.
   *
   * @param caseReference The unique identifier of the case for which assessment details
   *                      are required.
   * @param assessmentType The type of assessment, specifying the kind of details to retrieve.
   * @return A list of {@link CaseAssessmentDetail} objects, each containing detailed case
   *          assessment information. If no details are found, an empty list is returned.
   * @throws EbsApiRuntimeException If there is an error retrieving or processing the assessment
   *          details from the database.
   */
  @Transactional(readOnly = true)
  public List<CaseAssessmentDetail> getCaseAssessmentDetails(String caseReference,
      AssessmentType assessmentType) {

    log.info("Get case assessment details for case reference {}", caseReference);
    Array arrayType = jdbcTemplate.queryForObject("""
        SELECT XXCCMS.XXCCMS_SOA_REPLACE_PKG.GET_ASSESSMENT_DETAILS(?,?) FROM dual
        """, Array.class, caseReference, assessmentType.getValue());

    try {
      Object[] myArrayElement = (Object[]) arrayType.getArray();

      return Arrays.stream(myArrayElement)
          .filter(Struct.class::isInstance) // Filter only Struct objects
          .map(obj -> mapStructToCaseAssessmentDetail((Struct) obj))
          .toList();

    } catch (SQLException e) {
      throw new EbsApiRuntimeException(e);
    }
  }

  private CaseAssessmentDetail mapStructToCaseAssessmentDetail(Struct struct) {
    try {
      CaseAssessmentDetail caseAssessmentDetail = new CaseAssessmentDetail();
      caseAssessmentDetail.setEntityName((String) struct.getAttributes()[0]);
      caseAssessmentDetail.setInstanceLabel((String) struct.getAttributes()[1]);
      caseAssessmentDetail.setAttributeName((String) struct.getAttributes()[2]);
      caseAssessmentDetail.setAttributeValue(getAssessmentValue(struct));
      caseAssessmentDetail.setAttributeType((String) struct.getAttributes()[4]);
      caseAssessmentDetail.setAttributeUserDefinedIndicator(Boolean.valueOf(
          (String) struct.getAttributes()[5]));
      return caseAssessmentDetail;
    } catch (Exception e) {
      throw new EbsApiRuntimeException(e);
    }
  }

  private static String getAssessmentValue(Struct struct) throws SQLException {
    Clob assessmentValueClob = (Clob) struct.getAttributes()[3]; // Attribute 3 holds the Clob
    if (Objects.isNull(assessmentValueClob)) {
      return ""; // Return empty string if Clob is null
    }

    // Use getCharacterStream() to read the Clob content
    try (Reader reader = assessmentValueClob.getCharacterStream()) {
      StringBuilder result = new StringBuilder();
      char[] buffer = new char[5125];
      int bytesRead;
      while ((bytesRead = reader.read(buffer)) != -1) {
        result.append(buffer, 0, bytesRead);
      }
      return result.toString(); // Return the full Clob content as a String
    } catch (Exception e) {
      throw new SQLException("Error reading Clob data", e); // Handle errors
    }
  }
}
