package uk.gov.laa.ccms.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uk.gov.laa.ccms.data.entity.CounselLookupValue;

/**
 * Repository interface for accessing {@link CounselLookupValue} entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface, it supports read-only
 * operations for the {@code CounselLookupValue} entity, identified by a primary key of type {@code
 * String}.
 *
 * @see CounselLookupValue
 * @see ReadOnlyRepository
 */
public interface CounselLookupValueRepository
    extends ReadOnlyRepository<CounselLookupValue, String>,
        JpaSpecificationExecutor<CounselLookupValue> {}
