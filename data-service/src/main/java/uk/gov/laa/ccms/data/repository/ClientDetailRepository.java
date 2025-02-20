package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.ClientDetail;

@Repository
public class ClientDetailRepository extends BaseEntityManagerRepository<ClientDetail> {

  public ClientDetailRepository(EntityManager entityManager) {
    super(entityManager);
  }

}
