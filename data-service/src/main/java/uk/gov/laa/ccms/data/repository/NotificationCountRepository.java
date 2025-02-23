package uk.gov.laa.ccms.data.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.NotificationCount;
import uk.gov.laa.ccms.data.entity.NotificationCountId;

/**
 * Repository interface for accessing {@link NotificationCount} entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface, it supports read-only
 * operations for the {@link NotificationCount} entity, with the primary key of type
 * {@link String}.</p>
 *
 * @author Jamie Briggs
 * @see NotificationCount
 * @see ReadOnlyRepository
 */
@Repository
public interface NotificationCountRepository
    extends ReadOnlyRepository<NotificationCount, NotificationCountId> {

  List<NotificationCount> findAllByIdUserLoginId(String userLoginId);
}
