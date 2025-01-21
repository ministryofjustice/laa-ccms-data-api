package uk.gov.laa.ccms.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.laa.ccms.data.entity.TransactionStatus;

@DataJpaTest
@ActiveProfiles("h2-test")
@DisplayName("Transaction Status Repository Integration Test")
public class TransactionStatusRepositoryIntegrationTest {

  @Autowired
  private TransactionStatusRepository transactionStatusRepository;

  @PersistenceContext
  private EntityManager entityManager;

  private TransactionStatus createClientTransactionStatus;
  private TransactionStatus updateClientTransactionStatus;
  private TransactionStatus userFuncOne;
  private TransactionStatus userFuncTwo;
  private TransactionStatus userFuncErr;

  @BeforeEach
  void setUp() {
    // Insert test data into the in-memory database
    createClientTransactionStatus = TransactionStatus.builder()
        .requestId("1")
        .processName("CreateClient")
        .recordRefKey("CLIENT_REF_NUMBER")
        .recordRefValue("6505")
        .status("Success")
        .errorDescription("Party Successfully Created")
        .transactionOccurrenceDate(LocalDateTime.of(2024, 1, 1, 1, 1))
        .build();
    updateClientTransactionStatus = TransactionStatus.builder()
        .requestId("2")
        .processName("UpdateClient")
        .recordRefKey("CLIENT_REF_NUMBER")
        .recordRefValue("6505")
        .status("Success")
        .errorDescription("Party Successfully Created")
        .transactionOccurrenceDate(LocalDateTime.of(2024, 1, 1, 1, 2))
        .build();
    // Both with same request ID, this happens in EBS
    userFuncOne = TransactionStatus.builder()
        .requestId("1")
        .processName("XXCCMS_COMMON_UTIL.USER_FUNC_AUTH")
        .recordRefKey("CLIENT_REF_NUMBER")
        .recordRefValue("6505")
        .status("Success")
        .errorDescription("Party Successfully Created")
        .transactionOccurrenceDate(LocalDateTime.of(2024, 1, 1, 1, 3))
        .build();
    userFuncTwo = TransactionStatus.builder()
        .requestId("1")
        .processName("XXCCMS_COMMON_UTIL.USER_FUNC_AUTH")
        .recordRefKey("CLIENT_REF_NUMBER")
        .recordRefValue("6505")
        .status("Success")
        .errorDescription("Party Successfully Created")
        .transactionOccurrenceDate(LocalDateTime.of(2024, 1, 1, 1, 4))
        .build();
    userFuncErr = TransactionStatus.builder()
        .requestId("500")
        .processName("USER_FUNC_AUTH")
        .recordRefKey("CLIENT_REF_NUMBER")
        .recordRefValue("6505")
        .status("Error")
        .errorDescription("Party Successfully Created")
        .transactionOccurrenceDate(LocalDateTime.of(2024, 1, 1, 1, 5))
        .build();

    // Use entityManager as NotificationRepository extends ReadOnlyRepository.
    entityManager.persist(createClientTransactionStatus);
    entityManager.persist(updateClientTransactionStatus);
    entityManager.persist(userFuncOne);
    entityManager.persist(userFuncTwo);
    entityManager.persist(userFuncErr);
  }

  @Nested
  @DisplayName("findClientTransactionByTransactionId() Tests")
  class FindClientTransactionByTransactionIdTests {

    @Test
    @DisplayName("Should not return client transaction status")
    void shouldNotReturnClientTransactionStatus() {
      // Given
      String requestId = "404";
      // When
      Optional<TransactionStatus> result =
          transactionStatusRepository.findClientTransactionByTransactionId(
              requestId);
      // Then
      assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should get only create client transaction status")
    void shouldGetOnlyCreateClientTransactionStatus() {
      // Given
      String requestId = "1";
      // When
      Optional<TransactionStatus> result =
          transactionStatusRepository.findClientTransactionByTransactionId(
              requestId);
      // Then
      assertFalse(result.isEmpty());
      assertEquals(createClientTransactionStatus, result.get());
    }

    @Test
    @DisplayName("Should get only update client transaction status")
    void shouldGetOnlyUpdateClientTransactionStatus() {
      // Given
      String requestId = "2";
      // When
      Optional<TransactionStatus> result =
          transactionStatusRepository.findClientTransactionByTransactionId(
              requestId);
      // Then
      assertFalse(result.isEmpty());
      assertEquals(updateClientTransactionStatus, result.get());
    }
  }

  @Nested
  @DisplayName("findUserFunctionTransactionsByTransactionId() Tests")
  class FindUserFunctionTransactionsByTransactionIdTests {

    @Test
    @DisplayName("Should get multiple user function transactions")
    void shouldGetMultipleUserFunctionTransactions() {
      // Given
      String requestId = "1";
      // When
      List<TransactionStatus> result =
          transactionStatusRepository.findUserFunctionTransactionsByTransactionId(
              requestId);
      // Then
      assertEquals(2, result.size());
      assertTrue(result.contains(userFuncOne));
      assertTrue(result.contains(userFuncTwo));
    }

    @Test
    @DisplayName("Should get error user function transactions")
    void shouldGetErrorUserFunctionTransactions() {
      // Given
      String requestId = "500";
      // When
      List<TransactionStatus> result =
          transactionStatusRepository.findUserFunctionTransactionsByTransactionId(
              requestId);
      // Then
      assertEquals(1, result.size());
      assertTrue(result.contains(userFuncErr));
    }
  }
}
