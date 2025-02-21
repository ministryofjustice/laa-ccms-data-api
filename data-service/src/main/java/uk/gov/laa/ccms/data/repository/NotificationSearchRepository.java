package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import uk.gov.laa.ccms.data.entity.NotificationInfo;

/**
 * Repository for performing operations on the {@link NotificationInfo} entity. This repository
 * is implemented using an {@link EntityManager} for database interaction. It extends
 * the {@link BaseEntityManagerRepository}, which provides custom methods
 * for database interaction.
 *
 * <p>The {@link NotificationSearchRepository} is designed to handle {@link NotificationInfo}
 * entities, which represents notification data from the <b>XXCCMS_GET_NOTIF_INFO_V</b> view
 * in the database.</p>
 *
 * @see NotificationInfo
 * @see EntityManager
 * @see BaseEntityManagerRepository
 * @see org.springframework.data.jpa.domain.Specification
 * @see org.springframework.data.domain.Pageable
 * @author Jamie Briggs
 */
@Component
public class NotificationSearchRepository extends BaseEntityManagerRepository<NotificationInfo> {

  /**
   * Constructs a new instance of {@link NotificationSearchRepository}, which is responsible for
   * performing database operations on {@link NotificationInfo} entities using the provided
   * {@link EntityManager}.
   *
   * @param entityManager the {@link EntityManager} instance used to interact with the database.
   */
  public NotificationSearchRepository(EntityManager entityManager) {
    super(entityManager);
  }

}
