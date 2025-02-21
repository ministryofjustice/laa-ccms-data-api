package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CaseSearch;

/**
 * Repository for performing operations on the {@link CaseSearch} entity. This repository
 * is implemented using an {@link EntityManager} for database interaction. It extends
 * the {@link BaseEntityManagerRepository}, which provides custom methods
 * for database interaction.
 *
 * <p>The {@link CaseSearchRepository} is designed to handle {@link CaseSearch} entities, which
 * represents case data from the <b>XXCCMS_CASE_SEARCH_V</b> view in the database.</p>
 *
 * @see CaseSearch
 * @see EntityManager
 * @see BaseEntityManagerRepository
 * @see org.springframework.data.jpa.domain.Specification
 * @see org.springframework.data.domain.Pageable
 * @author Jamie Briggs
 */
@Repository
public class CaseSearchRepository extends BaseEntityManagerRepository<CaseSearch> {

  /**
   * Constructs a new instance of {@link CaseSearchRepository}, which is responsible for
   * performing database operations on {@link CaseSearch} entities using the provided
   * {@link EntityManager}.
   *
   * @param entityManager the {@link EntityManager} instance used to interact with the database.
   */
  public CaseSearchRepository(EntityManager entityManager) {
    super(entityManager);
  }

}
