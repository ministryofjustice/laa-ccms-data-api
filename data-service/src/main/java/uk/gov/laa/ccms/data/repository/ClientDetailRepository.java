package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.ClientDetail;

/**
 * Repository for performing operations on the {@link ClientDetail} entity. This repository
 * is implemented using an {@link EntityManager} for database interaction. It extends
 * the {@link BaseEntityManagerRepository}, which provides custom methods
 * for database interaction.
 *
 * <p>The {@link ClientDetailRepository} is designed to handle {@link ClientDetail} entities, which
 * represents client data from the <b>XXCCMS_GET_CLIENT_DETAILS_V</b> view in the database.</p>
 *
 * @see ClientDetail
 * @see EntityManager
 * @see BaseEntityManagerRepository
 * @see org.springframework.data.jpa.domain.Specification
 * @see org.springframework.data.domain.Pageable
 * @author Jamie Briggs
 */
@Repository
public class ClientDetailRepository extends BaseEntityManagerRepository<ClientDetail> {

  /**
   * Constructs a new instance of {@link ClientDetailRepository}, which is responsible for
   * performing database operations on {@link ClientDetail} entities using the provided
   * {@link EntityManager}.
   *
   * @param entityManager the {@link EntityManager} instance used to interact with the database.
   */
  public ClientDetailRepository(EntityManager entityManager) {
    super(entityManager);
  }

}
