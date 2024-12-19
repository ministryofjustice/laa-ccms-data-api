package uk.gov.laa.ccms.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import uk.gov.laa.ccms.data.entity.Notification;
import uk.gov.laa.ccms.data.repository.specification.NotificationSpecification;

@DataJpaTest
@ActiveProfiles("h2-test")
@DisplayName("Notification Repository Integration Test")
public class NotificationRepositoryIntegrationTest {
  
  @Autowired
  private NotificationRepository notificationRepository;
  
  @PersistenceContext
  private EntityManager entityManager;

  private Notification n1;
  private Notification n2;

  @BeforeEach
  void setUp() {
    // Insert test data into the in-memory database
    n1 = Notification.builder().notificationId(1L)
        .lscCaseRefReference("1001")
        .providerCaseReference("2001")
        .assignedTo("JBriggs")
        .personFirstName("Jamie")
        .personLastName("Briggs")
        .feeEarnerPartyId(3001L)
        .isOpen(true)
        .actionNotificationInd("N")
        .dateAssigned(LocalDate.of(2025, 1, 1))
        .build();
    n2 = Notification.builder().notificationId(2L)
        .lscCaseRefReference("1002")
        .providerCaseReference("2002")
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
  @DisplayName("Should get all Notifications")
  void shouldGetAllNotifications(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
    assertEquals(true, result.getContent().contains(n2));
  }
  
  @Test
  @DisplayName("Should filter by case reference number")
  void shouldFilterByCaseReferenceNumber(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by similar case reference number")
  void shouldFilterBySimilarCaseReferenceNumber(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
    assertEquals(true, result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by provider case reference number")
  void shouldFilterByProviderCaseReferenceNumber(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
        null,
        "2001",
        null,
        null,
        null,
        true,
        null,
        null,
        null);
    // When
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by similar provider case reference number")
  void shouldFilterBySimilarProviderCaseReferenceNumber(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
        null,
        "200",
        null,
        null,
        null,
        true,
        null,
        null,
        null);
    // When
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
    assertEquals(true, result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by assigned to user ID")
  void shouldFilterByAssignedToUserID(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by user surname")
  void shouldFilterByUserSurname(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by like user surname")
  void shouldFilterByLikeUserSurname(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
    assertEquals(true, result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by fee earner ID")
  void shouldFilterByFeeEarnerID(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by notification type")
  void shouldFilterByNotificationType(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
        null,
        null,
        null,
        null,
        null,
        true, "N",
        null,
        null);
    // When
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by date from")
  void shouldFilterByDateFrom(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by date from inclusive")
  void shouldFilterByDateFromInclusive(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
    assertEquals(true, result.getContent().contains(n2));
  }

  @Test
  @DisplayName("Should filter by date to")
  void shouldFilterByDateTo(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
  }

  @Test
  @DisplayName("Should filter by date to inclusive")
  void shouldFilterByDateToInclusive(){
    // Given
    Specification<Notification> spec = NotificationSpecification.withFilters(
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
    Page<Notification> result = notificationRepository.findAll(spec, Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertEquals(true, result.getContent().contains(n1));
    assertEquals(true, result.getContent().contains(n2));
  }


}
