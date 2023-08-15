package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;

/**
 * Repository interface for accessing {@link AmendmentTypeLookupValue} entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface,it supports read-only
 * operations for the {@code AmendmentTypeLookupValue} entity, with the primary key of type
 * {@code String}.</p>
 *
 * @author Your Name (optional)
 * @version 1.0
 * @see AmendmentTypeLookupValue
 * @see ReadOnlyRepository
 */
@Repository
public interface AmendmentTypeLookupValueRepository extends
        ReadOnlyRepository<AmendmentTypeLookupValue, String> {
}