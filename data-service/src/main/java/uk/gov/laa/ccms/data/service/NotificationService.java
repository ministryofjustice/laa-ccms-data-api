package uk.gov.laa.ccms.data.service;

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

  public Optional<Notifications> getNotifications(Pageable pageable){
    Page<Notification> byAssignedTo = notificationRepository.findByAssignedTo(
        "PENNY.WALL@SWITALSKIS.COM", pageable);
    //Page<Notification> byAll = notificationRepository.findAll(Pageable.ofSize(100));
    Notifications notifications = notificationsMapper.mapToNotificationsList(byAssignedTo);
    return Optional.ofNullable(notifications);
  }
}
