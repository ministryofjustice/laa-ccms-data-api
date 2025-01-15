package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.Notification;
import uk.gov.laa.ccms.data.entity.NotificationCount;
import uk.gov.laa.ccms.data.mapper.NotificationSummaryMapper;
import uk.gov.laa.ccms.data.mapper.NotificationsMapper;
import uk.gov.laa.ccms.data.model.NotificationSummary;
import uk.gov.laa.ccms.data.model.Notifications;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.repository.NotificationCountRepository;
import uk.gov.laa.ccms.data.repository.NotificationRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Notification service test")
class NotificationServiceTest {

  private NotificationService notificationService;
  @Mock
  private NotificationCountRepository notificationCountRepository;
  @Mock
  private NotificationSummaryMapper notificationSummaryMapper;
  @Mock
  private NotificationRepository notificationRepository;
  @Mock
  private NotificationsMapper notificationsMapper;
  @Mock
  private UserService userService;


  @BeforeEach
  void setup() {
    notificationService = new NotificationService(notificationCountRepository,
        notificationSummaryMapper, notificationRepository, notificationsMapper, userService);
  }

  @Test
  @DisplayName("getUserNotificationSummary(): Returns notification summary")
  void getUserNotificationSummary_returnsNotificationSummary() {
    // Given
    String userId = "123456";
    when(userService.getUser(userId)).thenReturn(Optional.of(new UserDetail()));
    List<NotificationCount> notificationCounts = List.of(new NotificationCount());
    when(notificationCountRepository.findAllByIdUserLoginId(userId)).thenReturn(notificationCounts);
    NotificationSummary expected = new NotificationSummary();
    when(notificationSummaryMapper.toNotificationSummary(notificationCounts)).thenReturn(expected);
    // When
    Optional<NotificationSummary> userNotificationSummary = notificationService.getUserNotificationSummary(
        userId);
    // Then
    assertEquals(expected, userNotificationSummary.get());
  }

  @Test
  @DisplayName("getUserNotificationSummary(): User not found")
  void getUserNotificationSummary_userNotFound() {
    // Given
    String userId = "123456";
    // When
    Optional<NotificationSummary> userNotificationSummary = notificationService.getUserNotificationSummary(
        userId);
    // Then
    assertFalse(userNotificationSummary.isPresent());
  }

  @Test
  @DisplayName("getNotifications(): Returns data")
  void getNotifications_returnsData() {
    // Given
    PageImpl<Notification> repositoryResult = new PageImpl<>(Collections.singletonList(new Notification()));
    when(notificationRepository.findAll(any(Specification.class), any(Pageable.class)))
        .thenReturn(
            repositoryResult);
    Notifications expected = new Notifications().size(1);
    when(notificationsMapper.mapToNotificationsList(repositoryResult)).thenReturn(
        expected
    );
    // When
    Optional<Notifications> result = notificationService.getNotifications(
        "Case Ref",
        "Prov case ref",
        "Assigned user id",
        "surname",
        123,
        true,
        "type",
        LocalDate.of(2000, 1, 1),
        LocalDate.of(2024, 1 ,1), Pageable.ofSize(10).withPage(0));
    // Then
    assertTrue(result.isPresent());
    assertEquals(expected, result.get());
  }

  @Test
  @DisplayName("getNotifications(): No data found")
  void getNotifications_noDataFound() {
    // Given
    // When
    Optional<Notifications> result = notificationService.getNotifications(
        "Case Ref",
        "Prov case ref",
        "Assigned user id",
        "surname",
        123,
        true,
        "type",
        LocalDate.of(2000, 1, 1),
        LocalDate.of(2024, 1 ,1), Pageable.ofSize(10).withPage(0));
    // Then
    assertFalse(result.isPresent());
  }
}