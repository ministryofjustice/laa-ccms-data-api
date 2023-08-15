package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;

/**
 * Repository interface for accessing {@link CaseStatusLookupValue} entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface, it supports
 * read-only operations for the {@code CaseStatusLookupValue} entity, identified by a
 * primary key of type {@code String}.</p>
 *
 * @see CaseStatusLookupValue
 * @see ReadOnlyRepository
 */
@Repository
public interface CaseStatusLookupValueRepository extends
        ReadOnlyRepository<CaseStatusLookupValue, String> {

}
