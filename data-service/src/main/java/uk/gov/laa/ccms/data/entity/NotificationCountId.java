package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a composite primary key for the NotificationCount entity in the form of a
 * NotificationCountId class. This key uniquely identifies a notification count record based on a
 * combination of user login ID and notification type.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCountId implements Serializable {

  /**
   * A unique identifier representing a user's login session. This field is mapped to the
   * "USER_LOGIN_ID" column in the database.
   */
  @Column(name = "USER_LOGIN_ID")
  private String userLoginId;

  /**
   * Represents the type of notification associated with a user login session. This field is mapped
   * to the "NOTIFICATION_TYPE" column in the database and uses the NotificationTypeConverter to
   * translate between the NotificationType enumeration and its corresponding String representation
   * for database storage.
   */
  @Column(name = "NOTIFICATION_TYPE")
  @Convert(converter = NotificationTypeConverter.class)
  private NotificationType notificationType;
}
