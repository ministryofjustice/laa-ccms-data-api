package uk.gov.laa.ccms.data.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.laa.ccms.data.entity.Notification;
import uk.gov.laa.ccms.data.entity.NotificationCount;
import uk.gov.laa.ccms.data.mapper.NotificationSummaryMapper;
import uk.gov.laa.ccms.data.mapper.NotificationsMapper;
import uk.gov.laa.ccms.data.model.NotificationSummary;
import uk.gov.laa.ccms.data.model.Notifications;
import uk.gov.laa.ccms.data.repository.NotificationCountRepository;
import uk.gov.laa.ccms.data.repository.NotificationRepository;
import uk.gov.laa.ccms.data.repository.specification.NotificationSpecification;

/**
 * Service class responsible for handling notification-related operations.
 *
 * <p>This class serves to manage the retrieval of notification summaries
 * for users, bridging the interactions between repositories, mappers,
 * and other services required to obtain and synthesize notification data.
 *
 * @author Jamie Briggs
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

  private final NotificationCountRepository notificationCountRepository;
  private final NotificationSummaryMapper notificationSummaryMapper;
  private final NotificationRepository notificationRepository;
  private final NotificationsMapper notificationsMapper;
  private final UserService userService;

  /**
   * Retrieves the summary of notifications for a specific user.
   *
   * @param userId the unique identifier of the user for whom to retrieve the notification summary
   * @return a NotificationSummary object representing the summary of notifications for the
   *     specified user
   */
  @Transactional
  public Optional<NotificationSummary> getUserNotificationSummary(String userId) {
    // Check if user exists
    if (userService.getUser(userId).isPresent()) {
      List<NotificationCount> allByIdUserLoginId =
          notificationCountRepository.findAllByIdUserLoginId(userId);
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
  public Optional<Notifications> getNotifications(String caseReferenceNumber,
      String providerCaseReference, String assignedToUserId, String clientSurname,
      Integer feeEarnerId, boolean includeClosed, String notificationType, LocalDate dateFrom,
      LocalDate dateTo, Pageable pageable) {
    Page<Notification> byAssignedTo = notificationRepository.findAll(
        NotificationSpecification.withFilters(caseReferenceNumber,
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
}
