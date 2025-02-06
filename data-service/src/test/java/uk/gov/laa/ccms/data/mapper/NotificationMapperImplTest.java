package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.model.Notification;

@DisplayName("Notification mapper implementation test")
class NotificationMapperImplTest {

  NotificationMapper mapper = new NotificationMapperImpl();

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
  @DisplayName("Should map notes")
  void shouldMapNotes(){
    Assertions.fail("Test not implemented yet");
  }

  @Test
  @DisplayName("Should map documents")
  void shouldMapDocuments(){
    Assertions.fail("Test not implemented yet");
  }

  @Test
  @DisplayName("Should map attachments")
  void shouldMapAttachments(){
    Assertions.fail("Test not implemented yet");
  }

  @Test
  @DisplayName("Should map available responses")
  void shouldMapAvailableResponses(){
    Assertions.fail("Test not implemented yet");
  }

}
