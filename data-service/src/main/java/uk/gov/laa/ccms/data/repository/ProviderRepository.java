package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.Provider;


/**
 * This is a Spring repository for Provider entity operations. It extends ReadOnlyRepository
 * interface for providing basic read-only operations on User entities. The primary key for
 * Provider entity is String.It is annotated with @Repository, which makes it a part of the Spring
 * framework's persistence layer.
 *
 * @Repository allows for exception translation into Spring's DataAccessException hierarchy.
 */
@Repository
public interface ProviderRepository extends ReadOnlyRepository<Provider, Integer> {

}