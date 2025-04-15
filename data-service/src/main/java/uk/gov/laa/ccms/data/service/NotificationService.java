package uk.gov.laa.ccms.data.service;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.gov.laa.ccms.data.entity.NotificationCount;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.mapper.NotificationMapper;
import uk.gov.laa.ccms.data.mapper.NotificationSummaryMapper;
import uk.gov.laa.ccms.data.mapper.NotificationsMapper;
import uk.gov.laa.ccms.data.model.Notification;
import uk.gov.laa.ccms.data.model.NotificationSummary;
import uk.gov.laa.ccms.data.model.Notifications;
import uk.gov.laa.ccms.data.repository.NotificationCountRepository;
import uk.gov.laa.ccms.data.repository.NotificationRepository;
import uk.gov.laa.ccms.data.repository.NotificationSearchRepository;
import uk.gov.laa.ccms.data.repository.specification.NotificationInfoSpecification;

/**
 * Service class responsible for handling notification-related operations.
 *
 * <p>This class serves to manage the retrieval of notification summaries
 * for users, bridging the interactions between repositories, mappers,
 * and other services required to obtain and synthesize notification data.</p>
 *
 * @author Jamie Briggs
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

  private final NotificationCountRepository notificationCountRepository;
  private final NotificationSearchRepository notificationSearchRepository;
  private final NotificationRepository notificationRepository;
  private final NotificationSummaryMapper notificationSummaryMapper;
  private final NotificationsMapper notificationsMapper;
  private final NotificationMapper notificationMapper;
  private final UserService userService;

  /**
   * Retrieves the summary of notifications for a specific user.
   *
   * @param loginId the unique identifier of the user for whom to retrieve the notification summary
   * @return a NotificationSummary object representing the summary of notifications for the
   *     specified user
   */
  public Optional<NotificationSummary> getUserNotificationSummary(String loginId) {
    // Check if user exists
    if (userService.existsUserById(loginId)) {
      List<NotificationCount> allByIdUserLoginId =
          notificationCountRepository.findAllByIdUserLoginId(loginId);
      return Optional.ofNullable(
          notificationSummaryMapper.toNotificationSummary(allByIdUserLoginId));
    }
    return Optional.empty();
  }

  /**
   * Retrieves a paginated list of notifications.
   *
   * @param caseReferenceNumber the case reference number to filter by (optional).
   * @param providerCaseReference the provider case reference to filter by (optional).
   * @param assignedToUserId the user ID assigned to the notification (optional).
   * @param clientSurname the client's surname to filter by (optional).
   * @param feeEarnerId the ID of the fee earner to filter by (optional).
   * @param includeClosed a flag to include closed notifications in the result set.
   * @param notificationType the type of notification to filter by (optional).
   * @param dateFrom the starting date for filtering notifications by the date assigned (inclusive).
   * @param dateTo the ending date for filtering notifications by the date assigned (inclusive).
   * @param pageable the pageable to describe the requested pagination format.
   * @return a paginated list of notifications.
   */
  public Optional<Notifications> getNotifications(final long providerId,
      String caseReferenceNumber,
      String providerCaseReference, String assignedToUserId, String clientSurname,
      Integer feeEarnerId, boolean includeClosed, String notificationType, LocalDate dateFrom,
      LocalDate dateTo, Pageable pageable) {
    Page<NotificationInfo> byAssignedTo = notificationSearchRepository.findAll(
        NotificationInfoSpecification.filterBy(providerId,
        caseReferenceNumber,
        providerCaseReference,
        assignedToUserId,
        clientSurname,
        feeEarnerId,
        includeClosed,
        notificationType,
        dateFrom,
        dateTo),
        pageable);
    Notifications notifications = notificationsMapper.mapToNotificationsList(byAssignedTo);
    return Optional.ofNullable(notifications);
  }

  /**
   * Retrieves a notification based on its ID and verifies the provider firm's ID.
   *
   * @param notificationId the unique identifier of the notification to retrieve
   * @param userId the identifier of the user assigned to the notification to retrieve
   * @param providerFirmId the identifier of the provider firm to validate access
   * @return an Optional containing the Notification if found and accessible,
   *     otherwise an empty Optional
   * @throws ResponseStatusException if the provider firm ID does not match,
   *     returning a forbidden status
   */
  public Optional<Notification> getNotification(final long notificationId,
      final String userId,
      final long providerFirmId) {
    Optional<NotificationInfo> byId = notificationRepository
        .findByNotificationIdAndUserId(notificationId, userId);

    // Return empty if not found
    if (byId.isEmpty()) {
      return Optional.empty();
    }

    // Return notification if exists, throw forbidden exception if provider firm ID mismatch
    return Optional.ofNullable(byId
        .filter(notification -> notification.getProviderFirmId() == providerFirmId)
        .map(notificationMapper::mapToNotification)
        .orElseThrow(() -> new ResponseStatusException(FORBIDDEN, "Access Denied")));

  }
}
