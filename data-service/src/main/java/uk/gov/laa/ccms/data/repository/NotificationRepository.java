package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.NotificationInfo;

@Repository
public interface NotificationRepository extends ReadOnlyRepository<NotificationInfo, Long> {

}
