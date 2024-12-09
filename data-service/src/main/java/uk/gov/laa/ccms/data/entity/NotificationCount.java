package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents an immutable notification count entity corresponding to the
 * "XXCCMS_NOTIFICATION_COUNT_V" database view.
 *
 * <p>The entity tracks the total number of notifications for a specific user login
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


}
