package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.MatterType;

/**
 * This is a Spring repository for MatterType entity operations. It extends
 * ReadOnlyRepository interface for providing basic read-only operations on MatterType entities. The
 * primary key for MatterType entity is String. It is annotated with @Repository, which makes it a
 * part of the Spring framework's persistence layer.
 *
 * @Repository allows for exception translation into Spring's DataAccessException hierarchy.
 */
@Repository
public interface MatterTypeRepository extends ReadOnlyRepository<MatterType, String> {
}
