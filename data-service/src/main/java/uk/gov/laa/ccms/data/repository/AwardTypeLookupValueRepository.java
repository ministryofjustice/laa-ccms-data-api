package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.AwardTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValueId;

/**
 * Repository interface for accessing {@link AwardTypeLookupValue} entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface, providing
 * read-only operations for the {@code AwardTypeLookupValue} entity. The entity is uniquely
 * identified by a primary key of type {@code String}.</p>
 *
 * @see CommonLookupValue
 * @see CommonLookupValueId
 * @see ReadOnlyRepository
 */
@Repository
public interface AwardTypeLookupValueRepository extends
        ReadOnlyRepository<AwardTypeLookupValue, String> {
}