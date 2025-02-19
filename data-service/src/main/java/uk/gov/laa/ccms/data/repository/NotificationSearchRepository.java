package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import uk.gov.laa.ccms.data.entity.ClientDetail;
import uk.gov.laa.ccms.data.entity.NotificationInfo;


@Component
public class NotificationSearchRepository extends BaseEntityManagerRepository<NotificationInfo> {

  public NotificationSearchRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public Class<NotificationInfo> getEntityClazz() {
    return NotificationInfo.class;
  }
}
