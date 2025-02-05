package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;

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
  private LocalDate noteDate;

  @Column(name = "NOTE_TEXT", nullable = false, length = 2000)
  private String noteText;

  @Column(name = "NOTE_BY", length = 360)
  private String noteBy;


}
