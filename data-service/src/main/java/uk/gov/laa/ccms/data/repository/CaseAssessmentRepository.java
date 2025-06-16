package uk.gov.laa.ccms.data.repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.dbtypes.AssessmentDetailObjectSqlType;
import uk.gov.laa.ccms.data.model.AssessmentType;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetail;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CaseAssessmentRepository {

  private final JdbcTemplate jdbcTemplate;

  public List<CaseAssessmentDetail> getCaseAssessmentDetails(String caseReference,
      AssessmentType assessmentType) {
    final String ASSESSMENT_TAB_TYPE = "xxccms_assessment_tab";
    jdbcTemplate.setFetchSize(500);
    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
        .withSchemaName("XXCCMS")
        .withCatalogName("XXCCMS_SOA_REPLACE_PKG")
        .withFunctionName("GET_ASSESSMENT_DETAILS")
        .withoutProcedureColumnMetaDataAccess()
        .declareParameters(
            new SqlOutParameter(ASSESSMENT_TAB_TYPE, Types.ARRAY,
                "XXCCMS.XXCCMS_ASSESSMENT_TAB",
                (cs, paramIndex, sqlType, typeName) -> {
                  Connection conn = cs.getConnection();
                  Map<String, Class<?>> typeMap = new HashMap<>();
                  typeMap.put("XXCCMS.XXCCMS_ASSESSMENT_OBJ", AssessmentDetailObjectSqlType.class);
                  conn.setTypeMap(typeMap);
                  return cs.getObject(paramIndex);
                }),
            new SqlParameter("p_case_ref_number", Types.VARCHAR),
            new SqlParameter("p_assessment_type", Types.VARCHAR));

    Map<String, Object> params = new HashMap<>();
    params.put("p_case_ref_number", caseReference);
    params.put("p_assessment_type", assessmentType.getValue());

    Map<String, Object> result = jdbcCall.execute(params);

    // Map result to list of CaseAssessmentDetail
    List<CaseAssessmentDetail> assessmentDetails = new ArrayList<>();
    if ((result.containsKey(ASSESSMENT_TAB_TYPE)
        && result.get(ASSESSMENT_TAB_TYPE) != null)) {
        log.debug("Found assessment details for case {}", caseReference);

      try {
        Object[] resultArray = (Object[]) ((Array) result.get(ASSESSMENT_TAB_TYPE)).getArray();
        for (Object obj : resultArray) {
          if (obj instanceof AssessmentDetailObjectSqlType detailSqlType) {
            assessmentDetails.add(detailSqlType);
          } else {
            log.warn("Encountered unsupported object type: {}", obj.getClass().getName());
          }
        }

      } catch (SQLException e) {
        log.error("Error occurred", e);
      }

      log.debug("Finished mapping assessment details for case {}", caseReference);
    }
    return assessmentDetails;
  }
}
