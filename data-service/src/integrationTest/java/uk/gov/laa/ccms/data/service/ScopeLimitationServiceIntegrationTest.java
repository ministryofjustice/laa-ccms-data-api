package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.OracleIntegrationTestInterface;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetails;

@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/scope_limitations_create_schema.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/sql/scope_limitations_drop_schema.sql")
public class ScopeLimitationServiceIntegrationTest implements OracleIntegrationTestInterface {

  @Autowired
  private ScopeLimitationService scopeLimitationService;

  @Test
  @Sql(statements = {
      "INSERT INTO XXCCMS.XXCCMS_SCOPELIMITATIONS_V (SCOPELIMITATIONS, DESCRIPTION, CATEGORY_OF_LAW_CODE, "
          + "MATTER_TYPE, PROCEEDING_CODE, LEVEL_OF_SERVICE_CODE, STAGE, SCOPE_DEFAULT, "
          + "DEFAULT_CODE, COST_LIMITATION, EMERGENCY, EMERGENCY_SCOPE_DEFAULT, "
          + "NON_STD_WORDING_REQD, DEFAULT_WORDING, EMERGENCY_COST_LIMITATION) " +
          "VALUES ('scopelimit1', 'Scope Limit 1', 'CAT1', "
          + "'MAT1', 'PROC1', 'LOS1', 1, 'Y', "
          + "'Y', 50.00, 'Y', 'Y', "
          + "'N', 'Default wording', 1350.00);",
      "INSERT INTO XXCCMS.XXCCMS_SCOPELIMITATIONS_V (SCOPELIMITATIONS, DESCRIPTION, CATEGORY_OF_LAW_CODE, "
          + "MATTER_TYPE, PROCEEDING_CODE, LEVEL_OF_SERVICE_CODE, STAGE, SCOPE_DEFAULT, "
          + "DEFAULT_CODE, COST_LIMITATION, EMERGENCY, EMERGENCY_SCOPE_DEFAULT, "
          + "NON_STD_WORDING_REQD, DEFAULT_WORDING, EMERGENCY_COST_LIMITATION) " +
          "VALUES ('scopelimit1', 'Scope Limit 1', 'CAT2', "
          + "'MAT2', 'PROC2', 'LOS2', 2, 'N', "
          + "'N', 150.00, 'N', 'N', "
          + "'Y', 'Default wording 2', 2350.00);"})
  public void testGetScopeLimitations_MultiMatch() {
    uk.gov.laa.ccms.data.model.ScopeLimitationDetail scopeLimitationDetail =
        new uk.gov.laa.ccms.data.model.ScopeLimitationDetail()
        .scopeLimitations("scopelimit1");

    // Call the service method
    ScopeLimitationDetails result = scopeLimitationService.getScopeLimitations(
        scopeLimitationDetail, Pageable.unpaged());

    // Assert the proceeding
    assertNotNull(result);

    assertEquals(2, result.getSize());
    assertNotNull(result.getContent());
    assertEquals(scopeLimitationDetail.getScopeLimitations(), result.getContent().getFirst().getScopeLimitations());
    assertEquals("CAT1", result.getContent().getFirst().getCategoryOfLaw());
    assertEquals(scopeLimitationDetail.getScopeLimitations(), result.getContent().get(1).getScopeLimitations());
    assertEquals("CAT2", result.getContent().get(1).getCategoryOfLaw());
  }

  @Test
  @Sql(statements = {
      "INSERT INTO XXCCMS.XXCCMS_SCOPELIMITATIONS_V (SCOPELIMITATIONS, DESCRIPTION, CATEGORY_OF_LAW_CODE, "
          + "MATTER_TYPE, PROCEEDING_CODE, LEVEL_OF_SERVICE_CODE, STAGE, SCOPE_DEFAULT, "
          + "DEFAULT_CODE, COST_LIMITATION, EMERGENCY, EMERGENCY_SCOPE_DEFAULT, "
          + "NON_STD_WORDING_REQD, DEFAULT_WORDING, EMERGENCY_COST_LIMITATION) " +
          "VALUES ('scopelimit1', 'Scope Limit 1', 'CAT1', "
          + "'MAT1', 'PROC1', 'LOS1', 1, 'Y', "
          + "'Y', 50.00, 'Y', 'Y', "
          + "'N', 'Default wording', 1350.00);",
      "INSERT INTO XXCCMS.XXCCMS_SCOPELIMITATIONS_V (SCOPELIMITATIONS, DESCRIPTION, CATEGORY_OF_LAW_CODE, "
          + "MATTER_TYPE, PROCEEDING_CODE, LEVEL_OF_SERVICE_CODE, STAGE, SCOPE_DEFAULT, "
          + "DEFAULT_CODE, COST_LIMITATION, EMERGENCY, EMERGENCY_SCOPE_DEFAULT, "
          + "NON_STD_WORDING_REQD, DEFAULT_WORDING, EMERGENCY_COST_LIMITATION) " +
          "VALUES ('scopelimit1', 'Scope Limit 1', 'CAT2', "
          + "'MAT2', 'PROC2', 'LOS2', 2, 'N', "
          + "'N', 150.00, 'N', 'N', "
          + "'Y', 'Default wording 2', 2350.00);"})
  public void testGetScopeLimitations_SingleMatch() {
    uk.gov.laa.ccms.data.model.ScopeLimitationDetail scopeLimitationDetail =
        new uk.gov.laa.ccms.data.model.ScopeLimitationDetail()
            .scopeLimitations("scopelimit1")
            .categoryOfLaw("CAT2");

    // Call the service method
    ScopeLimitationDetails result = scopeLimitationService.getScopeLimitations(
        scopeLimitationDetail, Pageable.unpaged());

    // Assert the proceeding
    assertNotNull(result);

    assertEquals(1, result.getSize());
    assertNotNull(result.getContent());
    assertEquals(scopeLimitationDetail.getScopeLimitations(), result.getContent().getFirst().getScopeLimitations());
    assertEquals("CAT2", result.getContent().getFirst().getCategoryOfLaw());
  }
}
