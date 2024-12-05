package uk.gov.laa.ccms.data.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.laa.ccms.data.entity.NotificationCount;
import uk.gov.laa.ccms.data.entity.NotificationCount.NotificationCountId;
import uk.gov.laa.ccms.data.entity.NotificationType;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.mapper.NotificationSummaryMapper;
import uk.gov.laa.ccms.data.mapper.UserMapper;
import uk.gov.laa.ccms.data.model.NotificationSummary;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.model.UserDetails;
import uk.gov.laa.ccms.data.repository.NotificationCountRepository;
import uk.gov.laa.ccms.data.repository.UserRepository;


/**
 * Service class for managing user-related operations.
 *
 * <p>This service provides methods to interact with user-related data and encapsulates
 * the logic required to access the underlying {@link UserRepository}.</p>
 *
 * @see User
 * @see UserRepository
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService extends AbstractEbsDataService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final NotificationCountRepository notificationCountRepository;

  private final NotificationSummaryMapper notificationSummaryMapper;

  /**
   * Get a single User based on its id.
   *
   * @param id = the id of the User.
   * @return Optional UserDetail.
   */

  @Transactional
  public Optional<UserDetail> getUser(String id) {
    return userRepository.findById(id).map(userMapper::toUserDetail);
  }

  /**
   * Get a UserDetails containing a page of BaseUser objects, based on the supplied search
   * criteria.
   *
   * @param providerId - the related providerId for the User.
   * @param pageable   - the pageable settings.
   * @return UserDetails containing a page of BaseUser.
   */
  public UserDetails getUsers(Integer providerId, Pageable pageable) {
    Page<User> users = userRepository.findByFirmsId(providerId, pageable);
    return userMapper.toUserDetails(users);
  }

  /**
   * Retrieves the summary of notifications for a specific user.
   *
   * @param userId the unique identifier of the user for whom to retrieve the notification summary
   * @return a NotificationSummary object representing the summary of notifications for the
   * specified user
   */
  @Transactional
  public Optional<NotificationSummary> getUserNotificationSummary(String userId) {
    // Check if user exists
    if (getUser(userId).isPresent()) {
      List<NotificationCount> allByIdUserLoginId = notificationCountRepository.findAllByIdUserLoginId(
          userId);
      return Optional.ofNullable(
          notificationSummaryMapper.toNotificationSummary(allByIdUserLoginId));
    }
    return Optional.empty();
  }
}
