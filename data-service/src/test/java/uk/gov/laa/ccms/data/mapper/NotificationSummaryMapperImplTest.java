package uk.gov.laa.ccms.data.mapper;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.entity.NotificationCount;
import uk.gov.laa.ccms.data.entity.NotificationCount.NotificationCountId;
import uk.gov.laa.ccms.data.entity.NotificationType;
import uk.gov.laa.ccms.data.model.NotificationSummary;

@ExtendWith(MockitoExtension.class)
class NotificationSummaryMapperImplTest {

  NotificationSummaryMapperImpl mapper = new NotificationSummaryMapperImpl();

  @Test
  @DisplayName("Should return notification summary with zeros when empty list passed")
  void shouldReturnNotificationSummaryWithZerosWhenEmptyListPassed() {
    // Given
    List<NotificationCount> counts = emptyList();
    // When
    NotificationSummary result = mapper.toNotificationSummary(counts);
    // Then
    assertEquals(new NotificationSummary().notifications(0).standardActions(0).overdueActions(0),
        result, "Should only contain zeros");
  }

  @Test
  @DisplayName("Should return notification summary with zeros when null passed")
  void shouldReturnNotificationSummaryWithZerosWhenNullPassed() {
    // Given
    // When
    NotificationSummary result = mapper.toNotificationSummary(null);
    // Then
    assertEquals(new NotificationSummary().notifications(0).standardActions(0).overdueActions(0),
        result, "Should only contain zeros");
  }

  @Test
  @DisplayName("Should return notification summary with list of zeros passed")
  void shouldReturnNotificationSummaryWithListOfZerosPassed() {
    // Given
    List<NotificationCount> counts = Arrays.asList(
        NotificationCount.builder().id(withNotificationCountId(NotificationType.NOTIFICATIONS)).notificationCount(0).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.ACTION)).notificationCount(0).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.OVERDUE)).notificationCount(0).build()
    );
    // When
    NotificationSummary result = mapper.toNotificationSummary(counts);
    // Then
    assertEquals(new NotificationSummary().notifications(0).standardActions(0).overdueActions(0),
        result, "Should only contain zeros");
  }

  @Test
  @DisplayName("Should return notification summary with 3 notifications")
  void shouldReturnNotificationSummaryWith3Notifications() {
    // Given
    List<NotificationCount> counts = Arrays.asList(
        NotificationCount.builder().id(withNotificationCountId(NotificationType.NOTIFICATIONS)).notificationCount(3).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.ACTION)).notificationCount(0).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.OVERDUE)).notificationCount(0).build()
    );
    // When
    NotificationSummary result = mapper.toNotificationSummary(counts);
    // Then
    assertEquals(new NotificationSummary().notifications(3).standardActions(0).overdueActions(0),
        result, "Notification summary should only have 3 notifications");
  }

  @Test
  @DisplayName("Should return notification summary with 5 notifications due to multiple NotificationCount objects")
  void shouldReturnNotificationSummaryWith5NotificationsDueToMultipleNotificationCountObjects() {
    // Given
    List<NotificationCount> counts = Arrays.asList(
        NotificationCount.builder().id(withNotificationCountId(NotificationType.NOTIFICATIONS)).notificationCount(2).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.NOTIFICATIONS)).notificationCount(3).build()
    );
    // When
    NotificationSummary result = mapper.toNotificationSummary(counts);
    // Then
    assertEquals(new NotificationSummary().notifications(5).standardActions(0).overdueActions(0),
        result, "Notification summary should only have 5 notifications");
  }

  @Test
  @DisplayName("Should return notification summary with 5 actions")
  void shouldReturnNotificationSummaryWith5Actions() {
    // Given
    List<NotificationCount> counts = Arrays.asList(
        NotificationCount.builder().id(withNotificationCountId(NotificationType.NOTIFICATIONS)).notificationCount(0).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.ACTION)).notificationCount(5).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.OVERDUE)).notificationCount(0).build()
    );
    // When
    NotificationSummary result = mapper.toNotificationSummary(counts);
    // Then
    assertEquals(new NotificationSummary().notifications(0).standardActions(5).overdueActions(0),
        result, "Notification summary should only have 5 actions");
  }

  @Test
  @DisplayName("Should return notification summary with 6 actions due to multiple NotificationCount objects")
  void shouldReturnNotificationSummaryWith6ActionsDueToMultipleNotificationCountObjects() {
    // Given
    List<NotificationCount> counts = Arrays.asList(
        NotificationCount.builder().id(withNotificationCountId(NotificationType.ACTION)).notificationCount(2).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.ACTION)).notificationCount(4).build()
    );
    // When
    NotificationSummary result = mapper.toNotificationSummary(counts);
    // Then
    assertEquals(new NotificationSummary().notifications(0).standardActions(6).overdueActions(0),
        result, "Notification summary should only have 6 actions");
  }


  @Test
  @DisplayName("Should return notification summary with 7 overdue")
  void shouldReturnNotificationSummaryWith7Overdue() {
    // Given
    List<NotificationCount> counts = Arrays.asList(
        NotificationCount.builder().id(withNotificationCountId(NotificationType.NOTIFICATIONS)).notificationCount(0).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.ACTION)).notificationCount(0).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.OVERDUE)).notificationCount(7).build()
    );
    // When
    NotificationSummary result = mapper.toNotificationSummary(counts);
    // Then
    assertEquals(new NotificationSummary().notifications(0).standardActions(0).overdueActions(7),
        result, "Notification summary should only have 7 actions");
  }

  @Test
  @DisplayName("Should return notification summary with 9 overdue due to multiple NotificationCount objects")
  void shouldReturnNotificationSummaryWith9OverdueDueToMultipleNotificationCountObjects() {
    // Given
    List<NotificationCount> counts = Arrays.asList(
        NotificationCount.builder().id(withNotificationCountId(NotificationType.OVERDUE)).notificationCount(5).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.OVERDUE)).notificationCount(4).build()
    );
    // When
    NotificationSummary result = mapper.toNotificationSummary(counts);
    // Then
    assertEquals(new NotificationSummary().notifications(0).standardActions(0).overdueActions(9),
        result, "Notification summary should only have 9 overdue");
  }

  @Test
  @DisplayName("Should return notification summary with multiple notification types")
  void shouldReturnNotificationSummaryWithMultipleNotificationTypes() {
    // Given
    List<NotificationCount> counts = Arrays.asList(
        NotificationCount.builder().id(withNotificationCountId(NotificationType.NOTIFICATIONS)).notificationCount(2).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.ACTION)).notificationCount(4).build(),
        NotificationCount.builder().id(withNotificationCountId(NotificationType.OVERDUE)).notificationCount(6).build()
    );
    // When
    NotificationSummary result = mapper.toNotificationSummary(counts);
    // Then
    assertEquals(new NotificationSummary().notifications(2).standardActions(4).overdueActions(6),
        result, "Notification summary should only have 7 actions");
  }



  private static NotificationCountId withNotificationCountId(NotificationType notificationType) {
    return new NotificationCountId("User", notificationType);
  }


}