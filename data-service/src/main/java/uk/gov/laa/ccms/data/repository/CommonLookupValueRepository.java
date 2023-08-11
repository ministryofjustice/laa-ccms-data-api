package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValueId;

/**
 * Repository interface for accessing {@link CommonLookupValue} entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface, providing
 * read-only operations for the {@code CommonLookupValue} entity. The entity is uniquely
 * identified by a composite primary key of type {@code CommonLookupValueId}.</p>
 *
 * @see CommonLookupValue
 * @see CommonLookupValueId
 * @see ReadOnlyRepository
 */
@Repository
public interface CommonLookupValueRepository extends
        ReadOnlyRepository<CommonLookupValue, CommonLookupValueId> {
}