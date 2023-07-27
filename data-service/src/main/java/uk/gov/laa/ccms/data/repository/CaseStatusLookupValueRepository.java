package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;

/**
 * Read-only repository for AppCaseStatus entity operations.
 */
@Repository
public interface CaseStatusLookupValueRepository extends ReadOnlyRepository<CaseStatusLookupValue, String> {

}
