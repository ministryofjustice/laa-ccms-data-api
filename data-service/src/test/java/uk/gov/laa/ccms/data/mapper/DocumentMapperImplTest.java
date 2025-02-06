package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.entity.NotificationAttachment;
import uk.gov.laa.ccms.data.entity.NotificationDocument;
import uk.gov.laa.ccms.data.model.Document;

@DisplayName("Document mapper implementation test")
class DocumentMapperImplTest {

  DocumentMapper mapper = new DocumentMapperImpl();

  @Test
  @DisplayName("Should map notification document to document")
  void shouldMapNotificationDocumentToDocument(){
    // Given
    NotificationDocument document = NotificationDocument.builder()
        .documentId(1L)
        .notificationId(2L)
        .documentChannel("Channel")
        .documentType("ADV_FRM")
        .documentDescription("Description")
        .documentStatus("Status")
        .edrmsDocumentid("EDRMS")
        .build();
    // When
    Document result = mapper.mapToNotification(document);
    // Then
    assertEquals("1", result.getDocumentId());
    assertEquals("Channel", result.getChannel());
    assertEquals("ADV_FRM", result.getDocumentType());
    assertEquals("Description", result.getText());
    assertEquals("Status", result.getStatus());
  }

  @Test
  @DisplayName("Should map notification attachment to document")
  void shouldMapNotificationAttachmentToDocument(){
    // Given
    NotificationAttachment attachment = NotificationAttachment.builder()
        .attachmentId(1L)
        .notificationId(2L)
        .attachmentTitle("Title")
        .attachmentDescription("Description")
        .build();
    // When
    Document result = mapper.mapToNotification(attachment);
    // Then
    assertEquals("1", result.getDocumentId());
    assertEquals("Title", result.getTitle());
    assertEquals("Description", result.getText());
  }

}
