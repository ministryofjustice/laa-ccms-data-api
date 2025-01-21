package uk.gov.laa.ccms.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.Notification;


/**
 * Repository interface for accessing {@link Notification} entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface,
 * it supports read-only operations for the {@link Notification} entity.
 * This repository also extends {@link JpaSpecificationExecutor}, which
 * allows the use of {@link org.springframework.data.jpa.domain.Specification}
 * to filter easier.</p>
 *
 * @see Notification
 * @see ReadOnlyRepository
 * @see org.springframework.data.jpa.domain.Specification
 * @author Jamie Briggs
 */
@Repository
public interface NotificationRepository extends ReadOnlyRepository<Notification, String>,
    JpaSpecificationExecutor<Notification> {

}
