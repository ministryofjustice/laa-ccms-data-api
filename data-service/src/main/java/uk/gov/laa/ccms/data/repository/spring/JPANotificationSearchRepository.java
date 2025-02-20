package uk.gov.laa.ccms.data.repository.spring;

import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.repository.ReadOnlyRepository;

@Repository
public interface JPANotificationSearchRepository extends ReadOnlyRepository<NotificationInfo, Long>,
    JpaSpecificationExecutor<NotificationInfo> {

}
