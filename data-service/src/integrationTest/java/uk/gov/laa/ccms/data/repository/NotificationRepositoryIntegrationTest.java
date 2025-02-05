package uk.gov.laa.ccms.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.laa.ccms.data.OracleIntegrationTestInterface;
import uk.gov.laa.ccms.data.entity.NotificationAction;
import uk.gov.laa.ccms.data.entity.NotificationAttachment;
import uk.gov.laa.ccms.data.entity.NotificationDocument;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.entity.NotificationNote;

@SpringBootTest
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_CLASS, scripts = {
    "/sql/get_notif_info_create_schema.sql",
    "/sql/get_notif_info_relationships_create_schema.sql"
})
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_CLASS, scripts = {
    "/sql/get_notif_info_drop_schema.sql",
    "/sql/get_notif_info_relationships_drop_schema.sql",
})
@DisplayName("Notification Repository Integration Tests")
public class NotificationRepositoryIntegrationTest implements OracleIntegrationTestInterface {

  @Autowired
  private NotificationRepository repository;

  private NotificationInfo notification;

  @BeforeEach
  void setup(){
    notification = NotificationInfo.builder().notificationId(1L)
        .userId("test_user")
        .userLoginId("test_login")
        .providerFirmId(10L)
        .dateAssigned(LocalDate.of(2025, 1, 1))
        .subject("Subject")
        .dueDate(LocalDate.of(2027, 1, 1))
        .assignedTo("JBriggs")
        .status("open")
        .lscCaseRefReference("1001")
        .providerCaseReference("First Case Reference")
        .clientName("Jamie Briggs")
        .feeEarner("Fee")
        .personLastName("Briggs")
        .feeEarnerPartyId(3001L)
        .actionNotificationInd("N")
        .isOpen(true)
        .build();
  }

  @Test
  @DisplayName("Should return notification")
  void shouldReturnNotification(){
    // Given
    long notificationId = 1L;
    // When
    NotificationInfo result = repository.findById(notificationId).orElse(null);
    // Then
    assertEquals(1L, result.getNotificationId());
    assertEquals("test_user", result.getUserId());
    assertEquals("test_login", result.getUserLoginId());
    assertEquals(10L, result.getProviderFirmId());
    assertEquals(LocalDate.of(2025, 1, 1), result.getDateAssigned());
    assertEquals("Subject", result.getSubject());
    assertEquals(LocalDate.of(2027, 1, 1), result.getDueDate());
    assertEquals("JBriggs", result.getAssignedTo());
    assertEquals("open", result.getStatus());
    assertEquals("1001", result.getLscCaseRefReference());
    assertEquals("First Case Reference", result.getProviderCaseReference());
    assertEquals("Jamie Briggs", result.getClientName());
    assertEquals("Fee", result.getFeeEarner());
    assertEquals("Briggs", result.getPersonLastName());
    assertEquals(3001L, result.getFeeEarnerPartyId());
    assertEquals("N", result.getActionNotificationInd());
    assertEquals(true, result.getIsOpen());
  }

  @Test
  @Transactional
  @DisplayName("Should return notification with notes")
  public void shouldReturnNotificationWithNotes(){
    // Given
    long notificationId = 1L;
    // When
    NotificationInfo result = repository.findById(notificationId).orElse(null);
    // Then
    NotificationNote note = result.getNotes().getFirst();
    assertEquals(1, note.getNoteId());
    assertEquals(1, note.getNotificationId());
    assertEquals(LocalDate.of(2025, 1, 1), note.getNoteDate());
    assertEquals("Here is the body of text for this note", note.getNoteText());
    assertEquals("Jamie Briggs", note.getNoteBy());
  }

  @Test
  @Transactional
  @DisplayName("Should return notification with documents")
  public void shouldReturnNotificationWithDocuments(){
    // Given
    long notificationId = 1L;
    // When
    NotificationInfo result = repository.findById(notificationId).orElse(null);
    // Then
    NotificationDocument document = result.getDocuments().getFirst();
    assertEquals(1, document.getDocumentId());
    assertEquals(1, document.getNotificationId());
    assertEquals("Channel", document.getDocumentChannel());
    assertEquals("Type", document.getDocumentType());
    assertEquals("Document description", document.getDocumentDescription());
    assertEquals("Status", document.getDocumentStatus());
    assertEquals("EDRMS ID", document.getEdrmsDocumentid());
  }

  @Test
  @Transactional
  @DisplayName("Should return notification with attachments")
  public void shouldReturnNotificationWithAttachments(){
    // Given
    long notificationId = 1L;
    // When
    NotificationInfo result = repository.findById(notificationId).orElse(null);
    // Then
    NotificationAttachment attachment = result.getAttachments().getFirst();
    assertEquals(1, attachment.getAttachmentId());
    assertEquals(1, attachment.getNotificationId());
    assertEquals("Attachment Title", attachment.getAttachmentTitle());
    assertEquals("Attachment description", attachment.getAttachmentDescription());
  }

  @Test
  @Transactional
  @DisplayName("Should return notification with actions")
  public void shouldReturnNotificationWithActions(){
    long notificationId = 1L;
    // When
    NotificationInfo result = repository.findById(notificationId).orElse(null);
    // Then
    NotificationAction action = result.getActions().getFirst();
    assertEquals(1, action.getNextActionId());
    assertEquals(1, action.getNotificationId());
    assertEquals("Action to complete", action.getNextAction());
  }
}
