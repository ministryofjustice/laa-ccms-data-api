package uk.gov.laa.ccms.data.repository;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.OracleIntegrationTestInterface;
import uk.gov.laa.ccms.data.entity.ClientDetail;

@SpringBootTest
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_CLASS, scripts = {
    "/sql/get_client_details_create_schema.sql"
})
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_CLASS, scripts = {
    "/sql/get_client_details_drop_schema.sql"
})
@DisplayName("Client search repository integration tests")
public class ClientDetailRepositoryIntegrationTest implements OracleIntegrationTestInterface {

  @Autowired
  private ClientDetailRepository repository;

  @Test
  @DisplayName("Should return two notifications")
  void shouldReturnTwoNotifications() {
    // Given
    // When
    Page<ClientDetail> result = repository.findAll(null, null, null,
        null, null, null, null,
        PageRequest.of(0, 10));
    // Then
    assertEquals(2L, result.getTotalElements());
  }

  @Test
  @DisplayName("Should return single notification")
  void shouldReturnSingleNotification(){
    // Given
    // When
    Page<ClientDetail> result = repository.findAll(null, null, null,
        null, null, null, null,
        PageRequest.of(0, 1));
    // Then
    assertEquals(1L, result.getContent().size());
    assertEquals(2, result.getTotalPages());
    assertEquals(2L, result.getTotalElements());
  }
}
