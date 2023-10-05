package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.StageEndLookupValue;
import uk.gov.laa.ccms.data.entity.StageEndLookupValueId;


/**
 * This is a Spring repository for StageEndLookupValue entity operations. It extends
 * ReadOnlyRepository interface for providing basic read-only operations on StageEndLookupValue
 * entities.
 */
@Repository
public interface StageEndLookupValueRepository extends
    ReadOnlyRepository<StageEndLookupValue, StageEndLookupValueId> {

}
