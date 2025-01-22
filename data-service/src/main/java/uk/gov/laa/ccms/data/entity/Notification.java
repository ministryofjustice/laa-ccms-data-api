package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "XXCCMS_GET_NOTIFICATIONS_V", schema = "XXCCMS")
@Getter
@Builder
@Immutable
@AllArgsConstructor
@RequiredArgsConstructor
public class Notification {

  @Id
  private long notificationId;

  @Column(name = "ASSIGNED_TO", length = 360)
  private String assignedTo;

  @Column(name = "USER_LOGIN_ID", length = 100)
  private String userLoginId;

  @Column(name = "PROVIDERFIRM_ID", nullable = false)
  private long providerFirmId;

  @Column(name = "CLIENT_PARTY_ID")
  private Long clientPartyId;

  @Column(name = "LSC_CASE_REF_REFERENCE", length = 360)
  private String lscCaseRefReference;

  @Column(name = "PROVIDER_CASE_REFERENCE", length = 150)
  private String providerCaseReference;

  @Column(name = "CLIENT_NAME", length = 301)
  private String clientName;

  @Column(name = "CATEGORY_OF_LAW", length = 150)
  private String categoryOfLaw;

  @Column(name = "FEE_EARNER", length = 360)
  private String feeEarner;

  @Column(name = "FEE_EARNER_PARTY_ID")
  private Long feeEarnerPartyId;

  @Column(name = "NOTIFICATION_SUBJECT", length = 320)
  private String notificationSubject;

  @Column(name = "DATE_ASSIGNED")
  private LocalDate dateAssigned;

  @Column(name = "DUE_DATE")
  private LocalDate dueDate;

  @Column(name = "ACTION_NOTIFICATION_IND", length = 150)
  private String actionNotificationInd;

  @Column(name = "STATUS", length = 150)
  private String status;

  @Column(name = "EVIDENCE_ALLOWED_IND", length = 5)
  private String evidenceAllowedInd;

  @Column(name = "IS_OPEN")
  private Boolean isOpen;

  @Column(name = "ASSIGNED_TO_PARTY_ID", precision = 15, scale = 0)
  private Long assignedToPartyId;

  @Column(name = "PERSON_FIRST_NAME", length = 150)
  private String personFirstName;

  @Column(name = "PERSON_LAST_NAME", length = 150)
  private String personLastName;

  @Lob
  @Column(name = "NOTES")
  private String notes;

  @Lob
  @Column(name = "UPLOADED_DOCUMENTS")
  private String uploadedDocuments;

  @Lob
  @Column(name = "ATTACHED_DOCUMENTS")
  private String attachedDocuments;

  @Lob
  @Column(name = "AVAILABLE_RESPONSES")
  private String availableResponses;
}
