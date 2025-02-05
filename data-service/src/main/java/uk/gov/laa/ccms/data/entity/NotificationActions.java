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

@Entity
@Table(name = "XXCCMS_NOTIFICATION_ACTIONS_V", schema = "XXCCMS")
@Getter
@Builder
@Immutable
@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationActions {

  @Id
  @Column(name = "NEXT_ACTION_ID", nullable = false)
  private long nextActionId;

  @Column(name = "NOTIFICATION_ID", nullable = false)
  private long notificationId;

  @Column(name = "NEXT_ACTION", length = 150)
  private String nextAction;

}
