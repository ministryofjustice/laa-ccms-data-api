package uk.gov.laa.ccms.data.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.Notification;

/**
 * Repository interface for accessing {@link Notification} entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface, it supports
 * read-only operations for the {@link Notification} entity, with the priary key of type
 * {@link Long}.</p>
 *
 * @see Notification
 * @see ReadOnlyRepository
 * @author Jamie Briggs
 */
@Repository
public interface NotificationRepository extends ReadOnlyRepository<Notification, Long>,
    JpaSpecificationExecutor<Notification> {

  Optional<Notification> findByNotificationId(Long notificationId);
  /*
  *//**
   * Retrieves a paginated list of notifications assigned to a specific user.
   *
   * @param assignedTo the identifier of the user to whom notifications are assigned
   * @param pageable the pagination information including page number and size
   * @return a paginated list of notifications assigned to the specified user
   *//*
  Page<Notification> findByAssignedTo(String assignedTo, Pageable pageable);


  *//**
   * Retrieves a paginated list of all notifications.
   *
   * @param pageable the pagination information including page number, size, and sorting
   * @return a paginated list of notifications
   *//*
  //Page<Notification> findAll(Pageable pageable);*/
}
