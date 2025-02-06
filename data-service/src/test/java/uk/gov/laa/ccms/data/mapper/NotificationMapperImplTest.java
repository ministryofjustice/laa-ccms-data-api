package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccms.data.entity.NotificationAction;
import uk.gov.laa.ccms.data.entity.NotificationAttachment;
import uk.gov.laa.ccms.data.entity.NotificationDocument;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.entity.NotificationNote;
import uk.gov.laa.ccms.data.model.Document;
import uk.gov.laa.ccms.data.model.Note;
import uk.gov.laa.ccms.data.model.Notification;

@SpringBootTest
@DisplayName("Notification mapper implementation test")
class NotificationMapperImplTest {

  NoteMapper noteMapper;
  DocumentMapper documentMapper;
  NotificationMapper mapper;

  @BeforeEach
  void beforeEach(){
    noteMapper = new NoteMapperImpl();
    documentMapper = new DocumentMapperImpl();
    mapper = new NotificationMapperImpl(noteMapper, documentMapper);
  }

  @Test
  @DisplayName("Should map basic details")
  void shouldMapBasicDetails(){
    // Given
    NotificationInfo notificationInfo = NotificationInfo.builder()
        .assignedTo("User Name")
        .userLoginId("User Login Id")
        .clientName("Client Name")
        .feeEarner("Fee Earner")
        .notificationId(1L)
        .providerFirmId(2L)
        .subject("NotificationInfo Subject")
        .dateAssigned(LocalDate.of(2024, 1, 1))
        .dueDate(LocalDate.of(2025, 2, 1))
        .status("Status")
        .isOpen(true)
        .actionNotificationInd("N")
        .lscCaseRefReference("LSC Case Ref")
        .providerCaseReference("Provider Case Ref")
        .feeEarnerPartyId(4L)
        .build();

    // When
    Notification notificationResult = mapper.mapToNotification(notificationInfo);
    // Then
    assertEquals("User Login Id", notificationResult.getUser().getLoginId());
    assertEquals("User Name", notificationResult.getUser().getUsername());
    assertEquals("Client Name", notificationResult.getClientName());
    assertEquals("Fee Earner", notificationResult.getFeeEarner());
    assertEquals("1", notificationResult.getNotificationId());
    assertEquals("2", notificationResult.getProviderFirmId());
    assertEquals("NotificationInfo Subject", notificationResult.getSubject());
    assertEquals(LocalDate.of(2024, 1, 1), notificationResult.getAssignDate());
    assertEquals(LocalDate.of(2025, 2, 1), notificationResult.getDueDate());
    assertEquals("N", notificationResult.getNotificationType());
    assertEquals("Status", notificationResult.getStatus());
    assertEquals(true, notificationResult.getNotificationOpenIndicator());
    assertEquals("Provider Case Ref",notificationResult.getProviderCaseReferenceNumber());
    assertEquals("LSC Case Ref", notificationResult.getCaseReferenceNumber());
  }

  @Test
  @DisplayName("Should map two notes")
  void shouldMapSingleNote(){
    // Given
    NotificationInfo notificationInfo = NotificationInfo.builder()
        .notificationId(2L)
        .notes(Arrays.asList(
            getNote(1L, "Note Text"),
            getNote(2L, "Second Note Text")
            ))
        .build();
    // When
    Notification notificationResult = mapper.mapToNotification(notificationInfo);
    // Then
    assertEquals(2, notificationResult.getNotes().size());
    Note noteResultOne = notificationResult.getNotes().get(0);
    assertEquals("1", noteResultOne.getNotesId());
    assertEquals("User Name", noteResultOne.getUser().getUsername());
    assertEquals(LocalDate.of(2025, 1, 1), noteResultOne.getDate());
    assertEquals("Note Text", noteResultOne.getMessage());
    Note noteResultTwo = notificationResult.getNotes().get(1);
    assertEquals("2", noteResultTwo.getNotesId());
    assertEquals("User Name", noteResultTwo.getUser().getUsername());
    assertEquals(LocalDate.of(2025, 1, 1), noteResultTwo.getDate());
    assertEquals("Second Note Text", noteResultTwo.getMessage());
  }


  @Test
  @DisplayName("Should map two documents")
  void shouldMapDocuments(){
    // Given
    NotificationDocument document = getDocument(1L, "ADV_FRM", "Description");
    NotificationDocument documentTwo = getDocument(2L, "ONL", "Description Two");
    NotificationInfo notificationInfo = NotificationInfo.builder()
        .notificationId(2L)
        .documents(Arrays.asList(document, documentTwo)).build();
    // When
    Notification notificationResult = mapper.mapToNotification(notificationInfo);
    // Then
    assertEquals(2, notificationResult.getUploadedDocuments().size());
    Document resultOne = notificationResult.getUploadedDocuments().get(0);
    assertEquals("1", resultOne.getDocumentId());
    assertEquals("Channel", resultOne.getChannel());
    assertEquals("ADV_FRM", resultOne.getDocumentType());
    assertEquals("Description", resultOne.getText());
    assertEquals("Status", resultOne.getStatus());
    Document resultTwo = notificationResult.getUploadedDocuments().get(1);
    assertEquals("2", resultTwo.getDocumentId());
    assertEquals("Channel", resultTwo.getChannel());
    assertEquals("ONL", resultTwo.getDocumentType());
    assertEquals("Description Two", resultTwo.getText());
    assertEquals("Status", resultTwo.getStatus());
  }

  // TODO Come back to this one
  @Test
  @DisplayName("Should map two attachments")
  void shouldMapAttachments(){
    // Given
    NotificationAttachment attachmentOne = getAttachment(1L, "One");
    NotificationAttachment attachmentTwo = getAttachment(2L, "Two");
    NotificationInfo notificationInfo = NotificationInfo.builder()
        .notificationId(2L)
        .attachments(Arrays.asList(attachmentOne, attachmentTwo)).build();
    // When
    Notification notificationResult = mapper.mapToNotification(notificationInfo);
    // Then
    assertEquals(2, notificationResult.getAttachedDocuments().size());
    Document resultOne = notificationResult.getAttachedDocuments().get(0);
    assertEquals("1", resultOne.getDocumentId());
    assertEquals("One", resultOne.getText());
    Document resultTwo = notificationResult.getAttachedDocuments().get(1);
    assertEquals("2", resultTwo.getDocumentId());
    assertEquals("Two", resultTwo.getText());
  }

  @Test
  @DisplayName("Should map available responses")
  void shouldMapAvailableResponses(){
    // Given
    NotificationInfo notificationInfo = NotificationInfo.builder()
        .notificationId(2L)
        .actions(Arrays.asList(
            NotificationAction.builder().nextAction("Action one").build(),
            NotificationAction.builder().nextAction("Action two").build()
        ))
        .build();
    // When
    Notification notificationResult = mapper.mapToNotification(notificationInfo);
    // Then
    assertEquals(2, notificationResult.getAvailableResponses().size());
    assertEquals("Action one", notificationResult.getAvailableResponses().get(0));
    assertEquals("Action two", notificationResult.getAvailableResponses().get(1));
  }

  private static NotificationNote getNote(final long noteId, final String noteText) {
    return NotificationNote
        .builder()
        .noteId(noteId)
        .notificationId(2L)
        .noteDate(LocalDate.of(2025, 1, 1))
        .noteText(noteText)
        .noteBy("User Name")
        .build();
  }

  private static NotificationDocument getDocument(final long documentId, final String type,
      final String docDescription) {
    return NotificationDocument.builder()
        .documentId(documentId)
        .notificationId(2L)
        .documentChannel("Channel")
        .documentType(type)
        .documentDescription(docDescription)
        .documentStatus("Status")
        .edrmsDocumentid("EDRMS")
        .build();
  }

  private static NotificationAttachment getAttachment(long attachmentId, String One) {
    return NotificationAttachment.builder().attachmentId(attachmentId).attachmentDescription(One)
        .build();
  }
}
