package uk.gov.laa.ccms.data.mapper;

import static java.util.Objects.isNull;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.mapstruct.Mapper;
import uk.gov.laa.ccms.data.entity.NotificationCount;
import uk.gov.laa.ccms.data.entity.NotificationType;
import uk.gov.laa.ccms.data.model.NotificationSummary;

/**
 * NotificationSummaryMapper is an interface for mapping notification count data to a notification summary.
 * Utilizes Java streams to process lists of NotificationCount and derive a summary.
 * This interface uses the MapStruct framework for object mapping and is defined as a Spring component.
 *
 * Note: It computes the sum of notifications, actions, and overdue actions from a list of NotificationCount.
 *
 * @see NotificationSummary
 * @see NotificationCount
 * @see NotificationCount.NotificationType
 */
@Mapper(componentModel = "spring")
public interface NotificationSummaryMapper {

  /**
   * Converts a list of NotificationCount objects into a NotificationSummary object
   * by calculating the sum of different types of notifications.
   *
   * @param notificationCount a list containing instances of NotificationCount, each with a type and count of notifications
   * @return a NotificationSummary object with the total count of notifications, standard actions, and overdue actions derived from the provided list
   */
  default NotificationSummary toNotificationSummary(
      @NotNull List<NotificationCount> notificationCount) {

    int notificationSum = 0;
    int actionSum = 0;
    int overdueSum = 0;
    if (!isNull(notificationCount)) {
      // There shouldn't really be an instance where there are multiple of the same notification type,
      //  however if there are due to inconsistencies in the DB, use Java streams to get the sum.
      notificationSum = notificationCount.stream()
          .filter(x -> NotificationType.NOTIFICATIONS.equals(x.getId().getNotificationType()))
          .mapToInt(NotificationCount::getNotificationCount).sum();
      actionSum = notificationCount.stream()
          .filter(x -> NotificationType.ACTION.equals(x.getId().getNotificationType()))
          .mapToInt(NotificationCount::getNotificationCount).sum();
      overdueSum = notificationCount.stream()
          .filter(x -> NotificationType.OVERDUE.equals(x.getId().getNotificationType()))
          .mapToInt(NotificationCount::getNotificationCount).sum();
    }
    return new NotificationSummary().notifications(notificationSum).standardActions(actionSum)
        .overdueActions(overdueSum);
  }
}
