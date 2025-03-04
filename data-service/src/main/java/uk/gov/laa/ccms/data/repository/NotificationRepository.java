package uk.gov.laa.ccms.data.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.NotificationInfo;

/**
 * Repository interface for managing read-only access to {@link NotificationInfo} entities.
 *
 * <p>Extends the {@link ReadOnlyRepository} interface which enforces read-only access
 * to entities and includes default query capabilities.</p>
 *
 * @author Jamie Briggs
 * @see NotificationInfo
 * @see ReadOnlyRepository
 */
@Repository
public interface NotificationRepository extends ReadOnlyRepository<NotificationInfo, Long> {

  /**
   * Finds and returns a {@link NotificationInfo} object matching both the notification ID and
   *     user ID.
   *
   * @param notificationId notification identifier
   * @param userId the user assigned to the notification
   * @return an {@link Optional} containing a {@link NotificationInfo} if a notification is found
   *     using the filters, or an empty {@link Optional} if a notification was not found.
   */
  Optional<NotificationInfo> findByNotificationIdAndUserId(Long notificationId, String userId);
}
