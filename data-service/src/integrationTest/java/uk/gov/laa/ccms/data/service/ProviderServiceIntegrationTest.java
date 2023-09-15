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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.AbstractIntegrationTest;
import uk.gov.laa.ccms.data.model.OfficeDetail;
import uk.gov.laa.ccms.data.model.ProviderDetail;


@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/providers_create_schema.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/sql/providers_drop_schema.sql")
public class ProviderServiceIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private ProviderService providerService;

  @Test
  @Sql(scripts = "/sql/providers_data.sql")
  public void testGetProvider() {
    Integer providerId = 1000;

    // Call the repository method
    Optional<ProviderDetail> result = providerService.getProvider(providerId);

    // Assert the result
    assertTrue(result.isPresent());

    ProviderDetail provider = result.get();
    assertEquals(providerId, provider.getId());

    // Check Offices
    assertNotNull(provider.getOffices());
    assertEquals(2, provider.getOffices().size());
    OfficeDetail office1 = provider.getOffices().get(0);
    OfficeDetail office2 = provider.getOffices().get(1);
    assertEquals(100, office1.getId());
    assertEquals(101, office2.getId());

    // Check FeeEarners
    assertNotNull(office1.getFeeEarners());
    assertEquals(2, office1.getFeeEarners().size());
    assertEquals(1, office1.getFeeEarners().get(0).getId());
    assertEquals(2, office1.getFeeEarners().get(1).getId());

    assertTrue(office2.getFeeEarners().isEmpty());
  }
}
