package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.PriorAuthorityType;


/**
 * This is a Spring repository for PriorAuthorityType entity operations.
 * It extends ReadOnlyRepository interface for providing basic read-only operations on
 * PriorAuthorityType entities. The primary key for PriorAuthorityType entity is String.
 * It is annotated with @Repository, which makes it a part of the Spring framework's
 * persistence layer.
 *
 * @Repository allows for exception translation into Spring's DataAccessException hierarchy.
 */
@Repository
public interface PriorAuthorityRepository extends ReadOnlyRepository<PriorAuthorityType, String> {

}
