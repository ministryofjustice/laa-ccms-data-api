package uk.gov.laa.ccms.data.repository;

import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValueId;

/**
 * Repository interface for accessing {@link uk.gov.laa.ccms.data.entity.CountryLookupValue}
 * entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface, it supports
 * read-only operations for the {@code CountryLookupValue} entity, identified by a
 * primary key of type {@code String}.</p>
 *
 * @see uk.gov.laa.ccms.data.entity.CountryLookupValue
 * @see ReadOnlyRepository
 */
public interface CountryLookupValueRepository extends
        ReadOnlyRepository<CountryLookupValue, CountryLookupValueId> {
}
