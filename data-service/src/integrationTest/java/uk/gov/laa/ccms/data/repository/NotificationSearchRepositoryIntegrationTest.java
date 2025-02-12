package uk.gov.laa.ccms.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.OracleIntegrationTestInterface;
import uk.gov.laa.ccms.data.entity.NotificationInfo;

@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase=BEFORE_TEST_CLASS,scripts= "/sql/get_notif_info_create_schema.sql")
@Sql(executionPhase=AFTER_TEST_CLASS,scripts= "/sql/get_notif_info_drop_schema.sql")
@DisplayName("Notification Info Repository Integration Test")
class NotificationSearchRepositoryIntegrationTest implements OracleIntegrationTestInterface {
  
  private NotificationSearchRepository notificationRepository;
  
  @PersistenceContext
  private EntityManager entityManager;

  private NotificationInfo n1;
  private NotificationInfo n2;

  @BeforeEach
  void setUp() {
    notificationRepository = new NotificationSearchRepository(entityManager);
    n1 = NotificationInfo.builder().notificationId(1L)
        .userId("test_user")
        .userLoginId("test_login")
        .providerFirmId(10L)
        .dateAssigned(LocalDate.of(2025, 1, 1))
        .subject("Subject")
        .dueDate(LocalDate.of(2027, 1, 1))
        .assignedTo("JBriggs")
        .status("open")
        .lscCaseRefReference("1001")
        .providerCaseReference("First Case Reference")
        .clientName("Jamie Briggs")
        .feeEarner("Fee")
        .personLastName("Briggs")
        .feeEarnerPartyId(3001L)
        .actionNotificationInd("N")
        .isOpen(true)
        .build();
    n2 = NotificationInfo.builder().notificationId(2L)
        .userId("test_user")
        .userLoginId("test_login")
        .providerFirmId(10L)
        .dateAssigned(LocalDate.of(2026, 1, 1))
        .subject("Subject")
        .dueDate(LocalDate.of(2027, 1, 1))
        .assignedTo("SMonday")
        .status("open")
        .lscCaseRefReference("2100")
        .providerCaseReference("Second Case Reference")
        .clientName("Ski Monday")
        .feeEarner("Fee")
        .personLastName("Bri-Monday")
        .feeEarnerPartyId(3002L)
        .actionNotificationInd("O")
        .isOpen(false)
        .build();
  }

  @Test
  @DisplayName("Should not get any Notifications")
  void shouldNotGetAnyNotifications(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(100L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertTrue(result.getContent().isEmpty());
  }

  @Test
  @DisplayName("Should get all Notifications by provider ID")
  void shouldGetAllNotifications(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }
  
  @Test
  @DisplayName("Should filter by case reference number")
  void shouldFilterByCaseReferenceNumber(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        "1001",
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by similar case reference number")
  void shouldFilterBySimilarCaseReferenceNumber(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        "100",
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
    assertEquals(2, result.getTotalElements());
  }

  @Test
  @DisplayName("Should filter by provider case reference number")
  void shouldFilterByProviderCaseReferenceNumber(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        "First",
        null,
        null,
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by provider case reference number case insensitive")
  void shouldFilterByProviderCaseReferenceNumberCaseInsensitive(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        "FIRST case REF",
        null,
        null,
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by similar provider case reference number")
  void shouldFilterBySimilarProviderCaseReferenceNumber(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        "Case Reference",
        null,
        null,
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by assigned to user ID")
  void shouldFilterByAssignedToUserID(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        "JBriggs",
        null,
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by user surname")
  void shouldFilterByUserSurname(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        null,
        "Briggs",
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by like user surname")
  void shouldFilterByLikeUserSurname(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        null,
        "Bri",
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by like user surname case insensitive")
  void shouldFilterByLikeUserSurnameCaseInsensitive(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        null,
        "bri-MONDAY",
        null,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertFalse(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by fee earner ID")
  void shouldFilterByFeeEarnerID(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        null,
        null,
        3001,
        true,
        null,
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by notification type")
  void shouldFilterByNotificationType(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        null,
        null,
        null,
        true, "N",
        null,
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by date from")
  void shouldFilterByDateFrom(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        LocalDate.of(2025, 2, 1),
    null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by date from inclusive")
  void shouldFilterByDateFromInclusive(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        LocalDate.of(2024, 1, 1),
        null, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by date to")
  void shouldFilterByDateTo(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        LocalDate.of(2025, 12, 1), Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by date to inclusive")
  void shouldFilterByDateToInclusive(){
    // Given
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(10L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        LocalDate.of(2027, 1, 1), Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }


}
