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
 * Represents a notification attachment entity from the <b>XXCCMS_NOTIFICATION_ATTMNTS_V</b>
 *     database view.
 *
 * <p>This entity captures details about a notification's attachment. It provides fields
 *     relating to the attachment including it's title, and a description of the attachment.</p>
 *
 * <p>This class is immutable, and its instances can be created using the builder pattern.</p>
 */
@Entity
@Table(name = "XXCCMS_NOTIFICATION_ATTMNTS_V", schema = "XXCCMS")
@Getter
@Builder
@Immutable
@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationAttachment {

  @Id
  @Column(name = "ATTACHMENT_ID", nullable = false)
  private long attachmentId;

  @Column(name = "NOTIFICATION_ID", nullable = false)
  private long notificationId;

  @Column(name = "ATTACHMENT_TITLE", nullable = false, length = 80)
  private String attachmentTitle;

  @Column(name = "ATTACHMENT_DESCRIPTION", length = 255)
  private String attachmentDescription;

}
