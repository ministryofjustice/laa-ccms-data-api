package uk.gov.laa.ccms.data.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.NotificationsApi;
import uk.gov.laa.ccms.data.model.Notification;
import uk.gov.laa.ccms.data.model.NotificationSummary;
import uk.gov.laa.ccms.data.model.Notifications;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.service.NotificationService;

/**
 * Controller class responsible for handling notification-related requests.
 *
 * <p>This controller serves as an interface to return requested user notification
 * information. It delegates the business logic to the {@link NotificationService}.
 *
 * <p>This class implements the {@link NotificationsApi} interface and provides
 * endpoints for retrieving notification summaries for users.
 */
@RestController
@RequiredArgsConstructor
public class NotificationsController implements NotificationsApi {

  private final NotificationService notificationService;

  @Override
  public ResponseEntity<Notifications> getNotifications(String caseReferenceNumber,
      String providerCaseReference, String assignedToUserId, String clientSurname,
      Integer feeEarnerId, Boolean includeClosed, String notificationType, LocalDate dateFrom,
      LocalDate dateTo, List<String> sort, Integer maxRecords, Pageable pageable) {
    // Notification to filter by
    Notification notification = new Notification()
        .providerCaseReferenceNumber(providerCaseReference)
        .clientName(clientSurname)
        .feeEarner(Optional.ofNullable(feeEarnerId).map(String::valueOf).orElse(""))
        .notificationType(notificationType)
        .user(
            new UserDetail()
                .username(assignedToUserId)
        );
    return ResponseEntity.ok(notificationService.getNotifications(pageable).get());
  }

  @Override
  public ResponseEntity<NotificationSummary> getUserNotificationSummary(String loginId) {
    return notificationService.getUserNotificationSummary(loginId).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}

