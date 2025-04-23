package uk.gov.laa.ccms.data.mapper;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.model.Notifications;

@DisplayName("NotificationsMapperImpl Test")
class NotificationsMapperImplTest {

  NotificationsMapperImpl mapper = new NotificationsMapperImpl();

  @Test
  @DisplayName("Should map page attributes")
  void shouldMapPageAttributes(){
    // Given
    Page<NotificationInfo> input = new PageImpl<>(Collections.emptyList(),
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
  @DisplayName("Should map single notification")
  void shouldMapSingleNotification(){
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
        .evidenceAllowedIndicator(true)
        .build();
    Page<NotificationInfo> input = new PageImpl<>(Arrays.asList(notificationInfo),
        PageRequest.of(0, 1), 1);
    // When
    Notifications result = mapper.mapToNotificationsList(input);
    // Then
    uk.gov.laa.ccms.data.model.NotificationInfo notificationResult = result.getContent().getFirst();
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

}
