package uk.gov.laa.ccms.data.entity;

import lombok.Getter;

/**
 * Defines the type of notification. This enumeration is used to categorize
 * different types of notifications within the system.
 *
 * <p>Each type has a corresponding database value which is a string representation
 * of the notification type stored in the database.
 *
 * <p>The available types are:
 * <ul>
 *  <li>NOTIFICATIONS: Represents general notifications. In
 *  the DB referred to as 'Notification'.</li>
 *  <li>ACTION: Represents action-required notifications. In the DB referred to as 'Action'.</li>
 *  <li>OVERDUE: Represents overdue notifications. In the DB referred to as 'Overdue'.</li>
 * </ul>
 * The enum provides a mechanism to easily handle notification types
 * programmatically by using the associated string value,
 * allowing seamless conversion between the programmatic representation and
 * the database representation.
 */
@Getter
public enum NotificationType {
  NOTIFICATIONS("Notification"),
  ACTION("Action"),
  OVERDUE("Overdue");

  private final String dbValue;

  NotificationType(String dbValue) {
    this.dbValue = dbValue;
  }
}
