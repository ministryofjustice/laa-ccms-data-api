package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.LevelOfService;
import uk.gov.laa.ccms.data.entity.LevelOfServiceId;

/**
 * This is a Spring repository for LevelOfService entity operations. It extends
 * ReadOnlyRepository interface for providing basic read-only operations on LevelOfService entities.
 * The primary key for LevelOfService entity is LevelOfServiceId.
 *
 * @Repository allows for exception translation into Spring's DataAccessException hierarchy.
 */
@Repository
public interface LevelOfServiceRepository extends
    ReadOnlyRepository<LevelOfService, LevelOfServiceId> {
}
