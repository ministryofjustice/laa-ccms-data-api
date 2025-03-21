package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a notification action entity from the <b>XXCCMS_NOTIFICATION_ACTIONS_V</b> database
 *     view.
 *
 * <p>This entity captures details about a notification's action. It provides a single field for
 *     describing the action which needs to be taken.</p>
 *
 * <p>This class is immutable, and its instances can be created using the builder pattern.</p>
 *
 * @author Jamie Briggs
 * @see NotificationInfo
 */
@Entity
@Table(name = "XXCCMS_NOTIFICATION_ACTIONS_V", schema = "XXCCMS")
@Getter
@Builder
@Immutable
@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationAction {

  @Id
  @Column(name = "NEXT_ACTION_ID", nullable = false)
  private long nextActionId;

  @Column(name = "NOTIFICATION_ID", nullable = false)
  private long notificationId;

  @Column(name = "NEXT_ACTION", length = 150)
  private String nextAction;

}
