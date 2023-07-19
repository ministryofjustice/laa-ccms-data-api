package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.FeeEarner;

/**
 * Read-only repository for FeeEarner entity operations.
 */
@Repository
public interface FeeEarnerRepository extends ReadOnlyRepository<FeeEarner, Integer> {

}
