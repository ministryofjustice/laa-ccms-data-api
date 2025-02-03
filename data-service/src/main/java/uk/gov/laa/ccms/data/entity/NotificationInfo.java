package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a notification entity from the <b>XXCCMS_GET_NOTIFICATIONS_V</b> database view.
 *
 * <p>This entity captures details about notifications, such as the user it is assigned to,
 *     associated case references, client information, deadlines, and related metadata.
 *     It provides essential fields to track the status, associated client,
 *     supporting documents, and notes.</p>
 *
 * <p>The class is immutable, and its instances can be created using the builder pattern.</p>
 *
 * @author Jamie Briggs
 */
@Entity
@Table(name = "XXCCMS_GET_NOTIF_INFO_V", schema = "XXCCMS")
@Getter
@Builder
@Immutable
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationInfo {

  @Id
  private long notificationId;

  @Column(name = "USER_ID", length = 100)
  private String userId;

  @Column(name = "USER_LOGIN_ID", length = 100)
  private String userLoginId;

  @Column(name = "PROVIDERFIRM_ID", nullable = false)
  private long providerFirmId;

  @Column(name = "DATE_ASSIGNED")
  private LocalDate dateAssigned;

  @Column(name = "SUBJECT", length = 320)
  private String subject;

  @Column(name = "DUE_DATE")
  private LocalDate dueDate;

  @Column(name = "ASSIGNED_TO", length = 360)
  private String assignedTo;

  @Column(name = "STATUS", length = 150)
  private String status;

  @Column(name = "LSC_CASE_REF_REFERENCE", length = 360)
  private String lscCaseRefReference;

  @Column(name = "PROVIDER_CASE_REFERENCE", length = 150)
  private String providerCaseReference;

  @Column(name = "CLIENT_NAME", length = 301)
  private String clientName;

  @Column(name = "FEE_EARNER", length = 360)
  private String feeEarner;

  @Column(name = "PERSON_LAST_NAME", length = 150)
  private String personLastName;

  @Column(name = "FEE_EARNER_PARTY_ID")
  private Long feeEarnerPartyId;

  @Column(name = "ACTION_NOTIFICATION_IND", length = 150)
  private String actionNotificationInd;

  @Column(name = "IS_OPEN")
  @Convert(converter = BooleanStringConverter.class)
  private Boolean isOpen;

  

}
