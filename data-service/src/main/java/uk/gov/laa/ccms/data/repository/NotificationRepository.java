package uk.gov.laa.ccms.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.Notification;

@Repository
public interface NotificationRepository extends ReadOnlyRepository<Notification, Long> {

  Page<Notification> findByAssignedTo(String assignedTo, Pageable pageable);
  Page<Notification> findAll(Pageable pageable);
}
