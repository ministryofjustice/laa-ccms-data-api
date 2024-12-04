package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import uk.gov.laa.ccms.data.entity.NotificationCount.NotificationCountId;

/**
 * Represents an immutable notification count entity corresponding to the
 * "XXCCMS_NOTIFICATION_COUNT_V" database view.
 * <p>
 * The entity tracks the total number of notifications for a specific user login
 * and notification type.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "XXCCMS_NOTIFICATION_COUNT_V")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotificationCount {

  public static final String NOTIFICATION_TYPE_NOTIFICATIONS = "Notifications";
  public static final String NOTIFICATION_TYPE_ACTIONS = "Actions";
  public static final String NOTIFICATION_TYPE_OVERDUE = "Overdue";

  /**
   * The composite key for the NotificationCount entity.
   * This key is used to uniquely identify a notification count entry
   * based on the user login ID and notification type.
   */
  @EmbeddedId
  private NotificationCountId id;

  /**
   * Represents the total number of notifications associated with a specific
   * user login and notification type. This field is stored in the
   * "NOTIFICATION_COUNT" column of the corresponding database view.
   */
  @Column(name = "NOTIFICATION_COUNT")
  private Integer notificationCount;

  /**
   * Represents a composite primary key for the NotificationCount entity in the form of a
   * NotificationCountId class. This key uniquely identifies a notification count record
   * based on a combination of user login ID and notification type.
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class NotificationCountId implements Serializable {

    /**
     * A unique identifier representing a user's login session.
     * This field is mapped to the "USER_LOGIN_ID" column in the database.
     */
    @Column(name = "USER_LOGIN_ID")
    private String userLoginId;

    /**
     * Represents the type of notification associated with a user login session.
     * This field is mapped to the "NOTIFICATION_TYPE" column in the database and
     * uses the NotificationTypeConverter to translate between the NotificationType
     * enumeration and its corresponding String representation for database storage.
     */
    @Column(name = "NOTIFICATION_TYPE")
    @Convert(converter = NotificationTypeConverter.class)
    private NotificationType notificationType;
  }


}
