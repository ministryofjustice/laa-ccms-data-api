package uk.gov.laa.ccms.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CaseSearch;

@Repository
public interface CaseSearchRepository extends ReadOnlyRepository<CaseSearch, Long>,
    JpaSpecificationExecutor<CaseSearch> {

}
