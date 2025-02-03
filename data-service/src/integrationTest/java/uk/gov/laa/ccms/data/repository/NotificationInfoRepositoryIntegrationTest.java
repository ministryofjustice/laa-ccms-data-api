package uk.gov.laa.ccms.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.repository.specification.NotificationSpecification;

@DataJpaTest
@ActiveProfiles("h2-test")
@DisplayName("NotificationInfo Repository Integration Test")
class NotificationInfoRepositoryIntegrationTest {
  
  @Autowired
  private NotificationRepository notificationRepository;
  
  @PersistenceContext
  private EntityManager entityManager;

  private NotificationInfo n1;
  private NotificationInfo n2;

  @BeforeEach
  void setUp() {
    // Insert test data into the in-memory database
    n1 = NotificationInfo.builder().notificationId(1L)
        .providerFirmId(10L)
        .lscCaseRefReference("1001")
        .providerCaseReference("First Case Reference")
        .assignedTo("JBriggs")
        .personFirstName("Jamie")
        .personLastName("Briggs")
        .feeEarnerPartyId(3001L)
        .isOpen(true)
        .actionNotificationInd("N")
        .dateAssigned(LocalDate.of(2025, 1, 1))
        .build();
    n2 = NotificationInfo.builder().notificationId(2L)
        .providerFirmId(10L)
        .lscCaseRefReference("1002")
        .providerCaseReference("Second Case Reference")
        .assignedTo("SMonday")
        .personFirstName("Ski")
        .personLastName("Bri-Monday")
        .feeEarnerPartyId(3002L)
        .isOpen(false)
        .actionNotificationInd("O")
        .dateAssigned(LocalDate.of(2026, 1, 1))
        .build();
    // Use entityManager as NotificationRepository extends ReadOnlyRepository.
    entityManager.persist(n1);
    entityManager.persist(n2);
  }

  @Test
  @DisplayName("Should not get any Notifications")
  void shouldNotGetAnyNotifications(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(100L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertTrue(result.getContent().isEmpty());
  }

  @Test
  @DisplayName("Should get all Notifications by provider ID")
  void shouldGetAllNotifications(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }
  
  @Test
  @DisplayName("Should filter by case reference number")
  void shouldFilterByCaseReferenceNumber(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        "1001",
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by similar case reference number")
  void shouldFilterBySimilarCaseReferenceNumber(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        "100",
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by provider case reference number")
  void shouldFilterByProviderCaseReferenceNumber(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        "First",
        null,
        null,
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by provider case reference number case insensitive")
  void shouldFilterByProviderCaseReferenceNumberCaseInsensitive(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        "FIRST case REF",
        null,
        null,
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by similar provider case reference number")
  void shouldFilterBySimilarProviderCaseReferenceNumber(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        "Case Reference",
        null,
        null,
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by assigned to user ID")
  void shouldFilterByAssignedToUserID(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        "JBriggs",
        null,
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by user surname")
  void shouldFilterByUserSurname(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        null,
        "Briggs",
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by like user surname")
  void shouldFilterByLikeUserSurname(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        null,
        "Bri",
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by like user surname case insensitive")
  void shouldFilterByLikeUserSurnameCaseInsensitive(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        null,
        "bri-MONDAY",
        null,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertFalse(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by fee earner ID")
  void shouldFilterByFeeEarnerID(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        null,
        null,
        3001,
        true,
        null,
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by notification type")
  void shouldFilterByNotificationType(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        null,
        null,
        null,
        true, "N",
        null,
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by date from")
  void shouldFilterByDateFrom(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        LocalDate.of(2025, 2, 1),
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by date from inclusive")
  void shouldFilterByDateFromInclusive(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        LocalDate.of(2024, 1, 1),
        null);
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by date to")
  void shouldFilterByDateTo(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        LocalDate.of(2025, 12, 1));
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by date to inclusive")
  void shouldFilterByDateToInclusive(){
    // Given
    Specification<NotificationInfo> spec = NotificationSpecification.withFilters(10L,
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        LocalDate.of(2026, 1, 1));
    // When
    Page<NotificationInfo> result = notificationRepository.findAll(spec, Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(n1));
    assertTrue(result.getContent().contains(n2));
  }


}
