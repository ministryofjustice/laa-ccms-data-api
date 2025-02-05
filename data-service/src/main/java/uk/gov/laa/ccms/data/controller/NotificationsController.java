package uk.gov.laa.ccms.data.controller;

import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.NotificationsApi;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.model.Notification;
import uk.gov.laa.ccms.data.model.NotificationSummary;
import uk.gov.laa.ccms.data.model.Notifications;
import uk.gov.laa.ccms.data.repository.NotificationRepository;
import uk.gov.laa.ccms.data.service.NotificationService;

/**
 * Controller class responsible for handling notification-related requests.
 *
 * <p>This controller serves as an interface to return requested user notification
 * information. It delegates the business logic to the {@link NotificationService}.</p>
 *
 * <p>This class implements the {@link NotificationsApi} interface and provides
 * endpoints for retrieving notification summaries for users.</p>
 *
 * @see NotificationsApi
 * @see NotificationService
 * @author Jamie Briggs
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class NotificationsController implements NotificationsApi {

  private final NotificationService notificationService;
  private final NotificationRepository tempRepo;


  @Override
  public ResponseEntity<Notification> getNotification(Long notificationId) {
    Optional<NotificationInfo> tempNotification = tempRepo.findById(notificationId);

    log.info(tempNotification.get().getSubject());
    return ResponseEntity.ok(new Notification().subject(tempNotification.get().getSubject()));
  }

  /**
   * Retrieves a list of notifications based on various search criteria.
   *
   * @param providerId the provider ID to filter notifications
   * @param caseReferenceNumber the case reference number to filter notifications
   * @param providerCaseReference the provider-specific case reference to filter notifications
   * @param assignedToUserId the user ID to filter notifications assigned to a specific user
   * @param clientSurname the client's surname to filter notifications for a specific client
   * @param feeEarnerId the ID of the fee earner to filter notifications associated with them
   * @param includeClosed a flag to indicate whether to include closed notifications in the results
   * @param notificationType the type of notifications to filter by
   * @param dateFrom the starting date to filter notifications by a specific date range
   * @param dateTo the ending date to filter notifications by a specific date range
   * @param pageable the pagination and sorting information for the result set
   * @return a {@code ResponseEntity} containing the retrieved list of notifications if found,
   *         or a {@code ResponseEntity} with HTTP status 404 if no notifications are found
   */
  @Override
  public ResponseEntity<Notifications> getNotifications(Long providerId,
      String caseReferenceNumber, String providerCaseReference, String assignedToUserId,
      String clientSurname, Integer feeEarnerId, Boolean includeClosed,
      String notificationType, LocalDate dateFrom, LocalDate dateTo, Pageable pageable) {
    Optional<Notifications> notifications = notificationService.getNotifications(
        providerId,
        caseReferenceNumber,
        providerCaseReference,
        assignedToUserId,
        clientSurname,
        feeEarnerId,
        Boolean.TRUE.equals(includeClosed),
        notificationType,
        dateFrom,
        dateTo,
        pageable);
    return notifications.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  /**
   * Retrieves a summary of user notifications for the specified login ID.
   *
   * @param loginId the login ID of the user for whom the notification summary is to be retrieved
   * @return a {@code ResponseEntity} containing the {@code NotificationSummary} if found,
   *         or a {@code ResponseEntity} with HTTP status 404 if no summary is available
   */
  @Override
  public ResponseEntity<NotificationSummary> getUserNotificationSummary(String loginId) {
    return notificationService.getUserNotificationSummary(loginId).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}

