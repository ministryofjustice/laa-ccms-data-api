package uk.gov.laa.ccms.data.repository;

import uk.gov.laa.ccms.data.entity.User;
import org.springframework.stereotype.Repository;

/**
 * This is a Spring repository for User entity operations. It extends ReadOnlyRepository
 * interface for providing basic read-only operations on User entities. The primary key for User entity is String.
 * It is annotated with @Repository, which makes it a part of the Spring framework's persistence layer.
 *
 * @Repository allows for exception translation into Spring's DataAccessException hierarchy.
 */
@Repository
public interface UserRepository extends ReadOnlyRepository<User, String> {

}
