package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CategoryOfLawLookupValue;

/**
 * Repository interface for accessing {@link CategoryOfLawLookupValue} entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface, it supports
 * read-only operations for the {@code CategoryOfLawLookupValue} entity, identified by a
 * primary key of type {@code String}.</p>
 *
 * @see CategoryOfLawLookupValue
 * @see ReadOnlyRepository
 */
@Repository
public interface CategoryOfLawLookupValueRepository extends
        ReadOnlyRepository<CategoryOfLawLookupValue, String> {

}
