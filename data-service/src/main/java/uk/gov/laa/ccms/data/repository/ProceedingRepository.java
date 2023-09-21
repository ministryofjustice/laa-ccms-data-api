package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.Proceeding;


/**
 * This is a Spring repository for Proceeding entity operations. It extends ReadOnlyRepository
 * interface for providing basic read-only operations on Proceeding entities. The primary key for
 * Proceeding entity is String.It is annotated with @Repository, which makes it a part of the Spring
 * framework's persistence layer.
 *
 * @Repository allows for exception translation into Spring's DataAccessException hierarchy.
 */
@Repository
public interface ProceedingRepository extends ReadOnlyRepository<Proceeding, String> {

}
