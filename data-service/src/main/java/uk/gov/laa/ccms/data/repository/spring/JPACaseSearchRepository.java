package uk.gov.laa.ccms.data.repository.spring;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CaseSearch;
import uk.gov.laa.ccms.data.repository.ReadOnlyRepository;

@Repository
public interface JPACaseSearchRepository extends ReadOnlyRepository<CaseSearch, Long>,
    JpaSpecificationExecutor<CaseSearch> {

}
