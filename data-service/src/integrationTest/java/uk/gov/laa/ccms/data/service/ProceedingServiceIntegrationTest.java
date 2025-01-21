package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.IntegrationTestInterface;
import uk.gov.laa.ccms.data.model.ProceedingDetail;
import uk.gov.laa.ccms.data.model.ProceedingDetails;

@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/proceedings_create_schema.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/sql/proceedings_drop_schema.sql")
public class ProceedingServiceIntegrationTest implements IntegrationTestInterface {

  @Autowired
  private ProceedingService proceedingService;

  @Test
  @Sql(statements = {
      "INSERT INTO XXCCMS.XXCCMS_PROCEEDING_V (PROCEEDING_CODE, PROCEEDING_NAME, DESCRIPTION, CATEGORY_OF_LAW_CODE, "
          + "STAGE_END_LOV, OUTCOME_RESULT_LOV, MATTER_TYPE, AMENDMENT_ONLY, ENABLED_FLAG, ORDER_TYPE_REQUIRED, PROC_LAR_SCOPE) " +
          "VALUES ('PROC1', 'Proceeding 1', 'The first proceeding', 'CAT1', 'stageendlov1', 'outcomeresultlov1', 'MAT1', 'Y', 'Y', 'Y', 'proclarscope1');",
      "INSERT INTO XXCCMS.XXCCMS_PROCEEDING_V (PROCEEDING_CODE, PROCEEDING_NAME, DESCRIPTION, CATEGORY_OF_LAW_CODE, "
          + "STAGE_END_LOV, OUTCOME_RESULT_LOV, MATTER_TYPE, AMENDMENT_ONLY, ENABLED_FLAG, ORDER_TYPE_REQUIRED, PROC_LAR_SCOPE) " +
          "VALUES ('PROC2', 'Proceeding 2', 'The first proceeding', 'CAT2', 'stageendlov2', 'outcomeresultlov2', 'MAT2', 'N', 'Y', 'Y', 'proclarscope2');"})
  public void testGetProceeding() {
    String code = "PROC1";

    // Call the service method
    Optional<ProceedingDetail> result = proceedingService.getProceeding(code);

    // Assert the proceeding
    assertTrue(result.isPresent());

    ProceedingDetail proceeding = result.get();
    assertEquals(code, proceeding.getCode());
    assertEquals("Proceeding 1", proceeding.getName());
    assertEquals("The first proceeding", proceeding.getDescription());
    assertTrue(proceeding.getEnabled());
    assertEquals("proclarscope1", proceeding.getLarScope());
    assertEquals("MAT1", proceeding.getMatterType());
    assertEquals("stageendlov1", proceeding.getStageEndLov());
    assertEquals("outcomeresultlov1", proceeding.getOutcomeResultLov());
    assertTrue(proceeding.getAmendmentOnly());
    assertTrue(proceeding.getOrderTypeRequired());
    assertEquals("CAT1", proceeding.getCategoryOfLawCode());
  }

  @Test
  @Sql(statements = {
      "INSERT INTO XXCCMS.XXCCMS_PROCEEDING_V (PROCEEDING_CODE, PROCEEDING_NAME, DESCRIPTION, CATEGORY_OF_LAW_CODE, "
          + "STAGE_END_LOV, OUTCOME_RESULT_LOV, MATTER_TYPE, AMENDMENT_ONLY, ENABLED_FLAG, ORDER_TYPE_REQUIRED, PROC_LAR_SCOPE) " +
          "VALUES ('PROC1', 'Proceeding 1', 'The first proceeding', 'CAT1', 'stageendlov1', 'outcomeresultlov1', 'MAT1', 'Y', 'Y', 'Y', 'proclarscope1');",
      "INSERT INTO XXCCMS.XXCCMS_PROCEEDING_V (PROCEEDING_CODE, PROCEEDING_NAME, DESCRIPTION, CATEGORY_OF_LAW_CODE, "
          + "STAGE_END_LOV, OUTCOME_RESULT_LOV, MATTER_TYPE, AMENDMENT_ONLY, ENABLED_FLAG, ORDER_TYPE_REQUIRED, PROC_LAR_SCOPE) " +
          "VALUES ('PROC2', 'Proceeding 2', 'The first proceeding', 'CAT2', 'stageendlov2', 'outcomeresultlov2', 'MAT2', 'N', 'Y', 'Y', 'proclarscope2');"})
  public void testGetProceedings() {
    String categoryOfLawCode = "CAT2";

    // Call the service method
    ProceedingDetails result = proceedingService.getProceedings(
        categoryOfLawCode, null, null, null, Pageable.ofSize(10).withPage(0));

    // Assert the proceeding
    assertNotNull(result);
    assertEquals(1, result.getTotalElements());

    assertEquals("PROC2", result.getContent().get(0).getCode());
  }
}
