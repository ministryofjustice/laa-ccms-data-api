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
  Optional<NotificationInfo> findByNotificationIdAndUserId(Long notificationId, Long userId);
}
