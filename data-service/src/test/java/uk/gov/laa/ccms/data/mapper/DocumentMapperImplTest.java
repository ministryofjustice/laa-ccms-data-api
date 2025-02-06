package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.entity.NotificationDocument;
import uk.gov.laa.ccms.data.model.Document;

@DisplayName("Document mapper implementation test")
class DocumentMapperImplTest {

  DocumentMapper mapper = new DocumentMapperImpl();

  @Test
  @DisplayName("Should map to document")
  void shouldMapToDocument(){
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
}
