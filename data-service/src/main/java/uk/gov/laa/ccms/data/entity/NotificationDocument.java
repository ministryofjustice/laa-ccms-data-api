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
@Table(name = "XXCCMS_NOTIFICATION_DOCS_V", schema = "XXCCMS")
@Getter
@Builder
@Immutable
@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationDocument {

  @Id
  @Column(name = "DOCUMENT_ID", nullable = false)
  private long documentId;

  @Column(name = "NOTIFICATION_ID", nullable = false)
  private long notificationId;

  @Column(name = "DOCUMENT_CHANNEL", nullable = false)
  private String documentChannel;

  @Column(name = "DOCUMENT_TYPE", length = 30)
  private String documentType;

  @Column(name = "DOCUMENT_DESCRIPTION", length = 255)
  private String documentDescription;

  @Column(name = "DOCUMENT_STATUS", length = 80, nullable = false)
  private String documentStatus;

  @Column(name = "EDRMS_DOCUMENT_ID", length = 50)
  private String edrmsDocumentid;


}
