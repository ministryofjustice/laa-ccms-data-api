package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValueId;


/**
 * This is a Spring repository for OutcomeResultLookupValue entity operations. It extends
 * ReadOnlyRepository interface for providing basic read-only operations on OutcomeResultLookupValue
 * entities.
 */
@Repository
public interface OutcomeResultLookupValueRepository extends
    ReadOnlyRepository<OutcomeResultLookupValue, OutcomeResultLookupValueId> {

}
