package uk.gov.laa.ccms.data.mapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uk.gov.laa.ccms.data.entity.Notification;
import uk.gov.laa.ccms.data.model.Document;
import uk.gov.laa.ccms.data.model.Note;
import uk.gov.laa.ccms.data.model.Notifications;

@DisplayName("NotificationsMapperImpl Test")
public class NotificationsMapperImplTest {

  NotificationsMapperImpl mapper = new NotificationsMapperImpl();

  @Test
  @DisplayName("Should map page attributes")
  void shouldMapPageAttributes(){
    // Given
    Page<Notification> input = new PageImpl<>(Collections.emptyList(),
        PageRequest.of(1, 100), 150);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    assertEquals(1, result.getNumber());
    assertEquals(100, result.getSize());
    assertEquals(2, result.getTotalPages());
    assertEquals(150, result.getTotalElements());
  }

  @Test
  @DisplayName("Should map single notification excluding Lob objects")
  void shouldMapSingleNotificationExcludingLobObjects(){
    // Given
    Notification notification = Notification.builder()
        .assignedTo("User Name")
        .userLoginId("User Login Id")
        .clientName("Client Name")
        .categoryOfLaw("Category Of Law")
        .feeEarner("Fee Earner")
        .notificationId(1L)
        .providerFirmId(2L)
        .notificationSubject("Notification Subject")
        .dateAssigned(LocalDate.of(2024, 1, 1))
        .dueDate(LocalDate.of(2025, 2, 1))
        .status("Status")
        .evidenceAllowedInd("true")
        .isOpen("true")
        .actionNotificationInd("N")

        // Not needed for view model?
        .clientPartyId(3L)
        .lscCaseRefReference("LSC Case Ref")
        .providerCaseReference("Provider Case Ref")
        .feeEarnerPartyId(4L)
        .assignedToPartyId(5L)
        .build();
    Page<Notification> input = new PageImpl<>(Arrays.asList(notification),
        PageRequest.of(0, 1), 1);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    uk.gov.laa.ccms.data.model.Notification notificationResult = result.getContent().get(0);
    assertEquals("User Login Id", notificationResult.getUser().getLoginId());
    assertEquals("User Name", notificationResult.getUser().getUsername());
    assertEquals("Client Name", notificationResult.getClientName());
    assertEquals("Category Of Law", notificationResult.getCategoryOfLaw());
    assertEquals("Fee Earner", notificationResult.getFeeEarner());
    assertEquals("1", notificationResult.getNotificationId());
    assertEquals("2", notificationResult.getProviderFirmId());
    assertEquals("Notification Subject", notificationResult.getSubject());
    assertEquals(LocalDate.of(2024, 1, 1), notificationResult.getAssignDate());
    assertEquals(LocalDate.of(2025, 2, 1), notificationResult.getDueDate());
    assertEquals("N", notificationResult.getNotificationType());
    assertEquals("Status", notificationResult.getStatus());
    assertEquals(true, notificationResult.getEvidenceAllowed());
    assertEquals(true, notificationResult.getNotificationOpenIndicator());
    assertEquals("Provider Case Ref",notificationResult.getProviderCaseReferenceNumber());
    assertEquals(true, notificationResult.getEvidenceAllowed());
  }


  @Test
  @DisplayName("Should map notes")
  void shouldMapNotes(){
    // Given
    String noteContent =
        """
        <Notes>
          <Note>
            <note_id>78405222</note_id>
            <note_by>LAA</note_by>
            <date>2024-12-12</date>
            <Message>12/12/2024 09:16***PENNY.WALL@SWITALSKIS.COM*** Comments:
        
              LAA Case Reference Number:test
              Message for Provider:
        
            </Message>
          </Note>
          <Note>
            <note_id>78405224</note_id>
            <note_by>LAA</note_by>
            <date>2024-12-12</date>
            <Message>Unable to send message to conducting solicitor - No live case with the
            reference test currently exists. Please either check your records to
            ensure you have quoted the correct case reference or check with your conducting
            solicitor to ensure that this case has been granted.
            </Message>
          </Note>
        </Notes>
        """;
    Notification notification = Notification.builder()
        .notes(noteContent)
        .build();
    Page<Notification> input = new PageImpl<>(Arrays.asList(notification),
        PageRequest.of(0, 1), 1);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    uk.gov.laa.ccms.data.model.Notification notificationResult = result.getContent().get(0);
    Note noteOne = notificationResult.getNotes().get(0);
    Note noteTwo = notificationResult.getNotes().get(1);
    assertEquals("78405222", noteOne.getNotesId());
    assertEquals("LAA", noteOne.getUser().getUsername());
    assertEquals("""
        12/12/2024 09:16***PENNY.WALL@SWITALSKIS.COM*** Comments:
        
              LAA Case Reference Number:test
              Message for Provider:
        
        """.strip(), noteOne.getMessage().strip());
    assertEquals(LocalDate.of(2024, 12, 12), noteTwo.getDate());
    assertEquals("78405224", noteTwo.getNotesId());
    assertEquals("LAA", noteTwo.getUser().getUsername());
    assertEquals("""
        Unable to send message to conducting solicitor - No live case with the
            reference test currently exists. Please either check your records to
            ensure you have quoted the correct case reference or check with your conducting
            solicitor to ensure that this case has been granted.""".strip(), noteTwo.getMessage().strip());
    assertEquals(LocalDate.of(2024, 12, 12), noteTwo.getDate());
  }

  @Test
  @DisplayName("Should map empty notes")
  void shouldMapEmptyNotes(){
    // Given
    String noteContent = "<Notes></Notes>";
    Notification notification = Notification.builder()
        .notes(noteContent)
        .build();
    Page<Notification> input = new PageImpl<>(Arrays.asList(notification),
        PageRequest.of(0, 1), 1);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    assertTrue(result.getContent().get(0).getNotes().isEmpty());
  }


  @Test
  @DisplayName("Should map uploaded documents")
  void shouldMapUploadedDocuments(){
    String uploadedDocumentsString =
        """
            <Uploaded_Documents>
              <Documents>
                <document_id>33373866</document_id>
                <document_type>COURT_ORD</document_type>
                <document_channel>E</document_channel>
                <Text>123</Text>
              </Documents>
              <Documents>
                <document_id>33373867</document_id>
                <document_type>COURT_ORD</document_type>
                <document_channel>E</document_channel>
                <Text>123</Text>
              </Documents>
            </Uploaded_Documents>
            """;
    Notification notification = Notification.builder()
        .uploadedDocuments(uploadedDocumentsString)
        .build();
    Page<Notification> input = new PageImpl<>(Arrays.asList(notification),
        PageRequest.of(0, 1), 1);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    uk.gov.laa.ccms.data.model.Notification notificationResult = result.getContent().get(0);
    Document documentOne = notificationResult.getUploadedDocuments().get(0);
    Document documentTwo = notificationResult.getUploadedDocuments().get(1);
    assertEquals("33373866", documentOne.getDocumentId());
    assertEquals("COURT_ORD", documentOne.getDocumentType());
    assertEquals("E", documentOne.getChannel());
    assertEquals("123", documentOne.getText());
    assertEquals("33373867", documentTwo.getDocumentId());
    assertEquals("COURT_ORD", documentTwo.getDocumentType());
    assertEquals("E", documentTwo.getChannel());
    assertEquals("123", documentTwo.getText());
  }

  @Test
  @DisplayName("Should map empty uploaded documents")
  void shouldMapEmptyUploadedDocuments(){
    // Given
    String uploadedDocumentsContent = "<Uploaded_Documents></Uploaded_Documents>";
    Notification notification = Notification.builder()
        .uploadedDocuments(uploadedDocumentsContent)
        .build();
    Page<Notification> input = new PageImpl<>(Arrays.asList(notification),
        PageRequest.of(0, 1), 1);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    assertTrue(result.getContent().get(0).getUploadedDocuments().isEmpty());
  }

  @Test
  @DisplayName("Should map attached documents")
  void shouldMapAttachedDocuments(){
    String attachedDocuments =
        """
            <Attached_Documents>
              <Documents>
                <document_id>3426023</document_id>
                <ATTACHMENT_TITLE>Outbound Letter</ATTACHMENT_TITLE>
                <Text>Bill assessment outcome - sol</Text>
              </Documents>
            </Attached_Documents>
            """;
    Notification notification = Notification.builder()
        .attachedDocuments(attachedDocuments)
        .build();
    Page<Notification> input = new PageImpl<>(Arrays.asList(notification),
        PageRequest.of(0, 1), 1);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    uk.gov.laa.ccms.data.model.Notification notificationResult = result.getContent().get(0);
    Document documentOne = notificationResult.getAttachedDocuments().get(0);
    assertEquals("3426023", documentOne.getDocumentId());
    assertEquals("Outbound Letter", documentOne.getTitle());
    assertEquals("Bill assessment outcome - sol", documentOne.getText());
  }

  @Test
  @DisplayName("Should map empty attached documents")
  void shouldMapEmptyAttachedDocuments(){
    // Given
    String attachedDocumentsContent = "<Attached_Documents></Attached_Documents>";
    Notification notification = Notification.builder()
        .attachedDocuments(attachedDocumentsContent)
        .build();
    Page<Notification> input = new PageImpl<>(Arrays.asList(notification),
        PageRequest.of(0, 1), 1);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    assertTrue(result.getContent().get(0).getAttachedDocuments().isEmpty());
  }

  @Test
  @DisplayName("Should map available responses")
  void shouldMapAvailableResponses(){
    String attachedDocuments =
        """
            <AvailableResponses>
              <Response>Read</Response>
            </AvailableResponses>
            """;
    Notification notification = Notification.builder()
        .availableResponses(attachedDocuments)
        .build();
    Page<Notification> input = new PageImpl<>(Arrays.asList(notification),
        PageRequest.of(0, 1), 1);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    uk.gov.laa.ccms.data.model.Notification notificationResult = result.getContent().get(0);
    String response = notificationResult.getAvailableResponses().get(0);
    assertEquals("Read", response);
  }

  @Test
  @DisplayName("Should map empty available responses")
  void shouldMapEmptyAvailableResponses(){
    String attachedDocuments =
        "<AvailableResponses></AvailableResponses>";
    Notification notification = Notification.builder()
        .availableResponses(attachedDocuments)
        .build();
    Page<Notification> input = new PageImpl<>(Arrays.asList(notification),
        PageRequest.of(0, 1), 1);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    assertTrue(result.getContent().get(0).getAvailableResponses().isEmpty());
  }
}
