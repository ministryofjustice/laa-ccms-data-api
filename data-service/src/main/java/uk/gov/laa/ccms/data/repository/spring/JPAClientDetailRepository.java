package uk.gov.laa.ccms.data.repository.spring;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CaseSearch;
import uk.gov.laa.ccms.data.entity.ClientDetail;
import uk.gov.laa.ccms.data.repository.ReadOnlyRepository;

@Repository
public interface JPAClientDetailRepository extends ReadOnlyRepository<ClientDetail, Long>,
    JpaSpecificationExecutor<ClientDetail> {

}
