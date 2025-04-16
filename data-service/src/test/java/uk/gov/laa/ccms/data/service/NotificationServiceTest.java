package uk.gov.laa.ccms.data.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;
import uk.gov.laa.ccms.data.entity.NotificationCount;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.mapper.NotificationMapper;
import uk.gov.laa.ccms.data.mapper.NotificationSummaryMapper;
import uk.gov.laa.ccms.data.mapper.NotificationsMapper;
import uk.gov.laa.ccms.data.model.Notification;
import uk.gov.laa.ccms.data.model.NotificationSummary;
import uk.gov.laa.ccms.data.model.Notifications;
import uk.gov.laa.ccms.data.repository.NotificationCountRepository;
import uk.gov.laa.ccms.data.repository.NotificationRepository;
import uk.gov.laa.ccms.data.repository.NotificationSearchRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Notification service test")
class NotificationServiceTest {

  private NotificationService notificationService;
  @Mock
  private NotificationCountRepository notificationCountRepository;
  @Mock
  private NotificationSummaryMapper notificationSummaryMapper;
  @Mock
  private NotificationSearchRepository notificationSearchRepository;
  @Mock
  private NotificationRepository notificationRepository;
  @Mock
  private NotificationsMapper notificationsMapper;
  @Mock
  private NotificationMapper notificationMapper;
  @Mock
  private UserService userService;


  @BeforeEach
  void setup() {
    notificationService = new NotificationService(notificationCountRepository,
        notificationSearchRepository, notificationRepository,
        notificationSummaryMapper, notificationsMapper, notificationMapper, userService);
  }

  @Nested
  @DisplayName("getUserNotificationSummary(): Tests")
  class GetUserNotificationSummaryTests {

    @Test
    @DisplayName("Returns notification summary")
    void getUserNotificationSummary_returnsNotificationSummary() {
      // Given
      String userId = "123456";
      when(userService.existsUserById(userId)).thenReturn(true);
      List<NotificationCount> notificationCounts = List.of(new NotificationCount());
      when(notificationCountRepository.findAllByIdUserLoginId(userId)).thenReturn(
          notificationCounts);
      NotificationSummary expected = new NotificationSummary();
      when(notificationSummaryMapper.toNotificationSummary(notificationCounts)).thenReturn(
          expected);
      // When
      Optional<NotificationSummary> userNotificationSummary =
          notificationService.getUserNotificationSummary(
              userId);
      // Then
      assertThat(userNotificationSummary).isPresent().hasValue(expected);

    }

    @Test
    @DisplayName("User not found")
    void getUserNotificationSummary_userNotFound() {
      // Given
      String userId = "123456";
      // When
      Optional<NotificationSummary> userNotificationSummary =
          notificationService.getUserNotificationSummary(
              userId);
      // Then
      assertThat(userNotificationSummary).isNotPresent();
    }
  }

  @Nested
  @DisplayName("getNotifications(): Tests")
  class GetNotificationsTests {

    @Test
    @DisplayName("Returns data")
    void getNotifications_returnsData() {
      // Given
      PageImpl<NotificationInfo> repositoryResult = new PageImpl<>(
          Collections.singletonList(new NotificationInfo()));
      when(notificationSearchRepository.findAll(any(), any(Pageable.class)))
          .thenReturn(
              repositoryResult);
      Notifications expected = new Notifications().size(1);
      when(notificationsMapper.mapToNotificationsList(repositoryResult)).thenReturn(
          expected
      );
      // When
      Optional<Notifications> result = notificationService.getNotifications(
          10L,
          "Case Ref",
          "Prov case ref",
          "Assigned user id",
          "surname",
          123,
          true,
          "type",
          LocalDate.of(2000, 1, 1),
          LocalDate.of(2024, 1, 1), Pageable.ofSize(10).withPage(0));
      // Then
      assertThat(result).isPresent().hasValue(expected);
    }

    @Test
    @DisplayName("No data found")
    void getNotifications_noDataFound() {
      // Given
      // When
      Optional<Notifications> result = notificationService.getNotifications(10L,
          "Case Ref",
          "Prov case ref",
          "Assigned user id",
          "surname",
          123,
          true,
          "type",
          LocalDate.of(2000, 1, 1),
          LocalDate.of(2024, 1, 1), Pageable.ofSize(10).withPage(0));
      // Then
      assertThat(result).isNotPresent();
    }
  }

  @Nested
  @DisplayName("getNotification(): Tests")
  class GetNotificationTests{

    @Test
    @DisplayName("Should return notification")
    void shouldReturnNotification(){
      // Given
      long notificationId = 1L;
      String userId = "123456";
      NotificationInfo notificationInfo = NotificationInfo.builder().providerFirmId(1L).build();
      Notification expected = new Notification().notificationId("1").providerFirmId("1");
      when(notificationRepository.findByNotificationIdAndUserId(notificationId, userId))
          .thenReturn(Optional.of(notificationInfo));
      when(notificationMapper.mapToNotification(notificationInfo)).thenReturn(expected);
      // When
      Optional<Notification> result = notificationService.getNotification(notificationId, userId, 1L);
      // Then
      assertThat(result).isPresent().hasValue(expected);
      verify(notificationRepository, times(1)).findByNotificationIdAndUserId(notificationId, userId);
      verify(notificationMapper, times(1)).mapToNotification(notificationInfo);
    }

    @Test
    @DisplayName("Should return empty")
    void shouldReturnEmpty(){
      // Given
      long notificationId = 1L;
      String userId = "123456";
      // When
      Optional<Notification> result = notificationService.getNotification(notificationId, userId, 1L);
      // Then
      assertThat(result).isEmpty();
      verify(notificationRepository, times(1)).findByNotificationIdAndUserId(notificationId, userId);
      verify(notificationMapper, times(0)).mapToNotification(any());
    }

    @Test
    @DisplayName("Should throw exception")
    void shouldThrowException() {
      // Given
      long notificationId = 1L;
      String userId = "123456";
      NotificationInfo notificationInfo = NotificationInfo.builder().providerFirmId(1L).build();
      when(notificationRepository.findByNotificationIdAndUserId(notificationId, userId))
          .thenReturn(Optional.of(notificationInfo));
      // When / Then
      assertThrows(ResponseStatusException.class,
          () -> notificationService.getNotification(notificationId, userId, 500));
    }
  }
}