package uk.gov.laa.ccms.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.Notification;


@Repository
public interface NotificationRepository extends ReadOnlyRepository<Notification, Long>,
    JpaSpecificationExecutor<Notification> {


}
