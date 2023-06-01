package uk.gov.laa.ccms.data.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

/**
 * ReadOnlyRepository interface provides a read-only operation for a repository.
 *
 * @param <T>  the domain type the repository manages
 * @param <ID> the type of the id of the entity the repository manages
 */
@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID>, QueryByExampleExecutor<T> {

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<T> findById(ID id);
}
