package uk.gov.laa.ccms.data.repository;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.AbstractIntegrationTest;
import uk.gov.laa.ccms.data.entity.Provider;


@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/providers_create_schema.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/sql/providers_drop_schema.sql")
public class ProviderRepositoryIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private ProviderRepository providerRepository;

  @Test
  @Sql(statements = {
      "INSERT INTO XXCCMS_PROVIDERFIRMS_V (PROVIDERFIRM_ID, PROVIDERFIRM_NAME) " +
          "VALUES (1000, 'Provider 1');",
      "INSERT INTO XXCCMS_PROVIDERFIRMS_V (PROVIDERFIRM_ID, PROVIDERFIRM_NAME) " +
          "VALUES (1001, 'Provider 2');",
      "INSERT INTO XXCCMS_PROVIDER_OFFICES_V (OFFICE_ID, OFFICE_NAME, PROVIDERFIRM_ID) " +
          "VALUES (100, 'Office 1 for Provider 1', 1000);",
      "INSERT INTO XXCCMS_PROVIDER_OFFICES_V (OFFICE_ID, OFFICE_NAME, PROVIDERFIRM_ID) " +
          "VALUES (101, 'Office 2 for Provider 1', 1000);",
      "INSERT INTO XXCCMS_PROVIDER_OFFICES_V (OFFICE_ID, OFFICE_NAME, PROVIDERFIRM_ID) " +
          "VALUES (102, 'Office 1 for Provider 2', 1001);",
      "INSERT INTO XXCCMS_FEE_EARNERS_V (CONTACT_ID, CONTACT_NAME, PROVIDERFIRM_ID) " +
          "VALUES (1, 'Fee Earner 1 in Provider 1 Office 1', 1000)",
      "INSERT INTO XXCCMS_FEE_EARNER_OFFICES_V (CONTACT_ID, CONTACT_NAME, PROVIDERFIRM_ID, " +
          "OFFICE_ID, OFFICE_NAME) " +
          "VALUES (1, 'Fee Earner 1 in Provider 1 Office 1', 1000, 100, 'Office 1 for Provider 1')",
      "INSERT INTO XXCCMS_FEE_EARNERS_V (CONTACT_ID, CONTACT_NAME, PROVIDERFIRM_ID) " +
          "VALUES (2, 'Fee Earner 2 in Provider 1 Office 1', 1000)",
      "INSERT INTO XXCCMS_FEE_EARNER_OFFICES_V (CONTACT_ID, CONTACT_NAME, PROVIDERFIRM_ID, " +
          "OFFICE_ID, OFFICE_NAME) " +
          "VALUES (2, 'Fee Earner 2 in Provider 1 Office 1', 1000, 100, 'Office 1 for Provider 1')",
      "INSERT INTO XXCCMS_FEE_EARNERS_V (CONTACT_ID, CONTACT_NAME, PROVIDERFIRM_ID) " +
          "VALUES (3, 'Fee Earner 3 in Provider 2 Office 1', 1001)",
      "INSERT INTO XXCCMS_FEE_EARNER_OFFICES_V (CONTACT_ID, CONTACT_NAME, PROVIDERFIRM_ID, " +
          "OFFICE_ID, OFFICE_NAME) " +
          "VALUES (3, 'Fee Earner 3 in Provider 2 Office 1', 1001, 102, 'Office 1 for Provider 2')"
  })
  public void testFindById() {
    Integer providerId = 1000;

    // Call the repository method
    Optional<Provider> result = providerRepository.findById(providerId);

    // Assert the result
    assertTrue(result.isPresent());

    Provider provider = result.get();
    assertEquals(providerId, provider.getId());
    assertNotNull(provider.getOffices());
    assertEquals(2, provider.getOffices().size());
    assertEquals(100, provider.getOffices().get(0).getId());
    assertEquals(101, provider.getOffices().get(1).getId());
    assertNotNull(provider.getOffices().get(0).getFeeEarners());
    assertEquals(2, provider.getOffices().get(0).getFeeEarners().size());
    assertEquals(1, provider.getOffices().get(0).getFeeEarners().get(0).getId());
    assertEquals(2, provider.getOffices().get(0).getFeeEarners().get(1).getId());
    assertTrue(provider.getOffices().get(1).getFeeEarners().isEmpty());
  }
}
