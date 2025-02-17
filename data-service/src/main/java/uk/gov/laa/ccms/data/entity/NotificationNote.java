package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a notification note entity from the <b>XXCCMS_NOTIFICATION_NOTES_V</b> database
 *     view.
 *
 * <p>This entity captures details about a notification's note. It provides fields relating to
 *     a note including its text, who created the note, and when it was created.</p>
 *
 * <p>This class is immutable, and its instances can be created using the builder pattern.</p>
 *
 * @author Jamie Briggs
 * @see NotificationInfo
 */
@Entity
@Table(name = "XXCCMS_NOTIFICATION_NOTES_V", schema = "XXCCMS")
@Getter
@Builder
@Immutable
@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationNote {

  @Id
  @Column(name = "NOTE_ID", nullable = false)
  private long noteId;

  @Column(name = "NOTIFICATION_ID", nullable = false)
  private long notificationId;

  @Column(name = "NOTE_DATE", nullable = false)
  private LocalDateTime noteDate;

  @Column(name = "NOTE_TEXT", nullable = false, length = 2000)
  private String noteText;

  @Column(name = "NOTE_BY", length = 360)
  private String noteBy;


}
