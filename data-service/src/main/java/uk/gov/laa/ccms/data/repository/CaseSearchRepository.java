package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CaseSearch;

@Repository
public class CaseSearchRepository extends BaseEntityManagerRepository<CaseSearch> {

  public CaseSearchRepository(EntityManager entityManager) {
    super(entityManager);
  }

}
