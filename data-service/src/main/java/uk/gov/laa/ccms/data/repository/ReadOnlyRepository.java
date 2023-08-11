package uk.gov.laa.ccms.data.repository;

import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


/**
 * ReadOnlyRepository interface provides a read-only operation for a repository.
 *
 * @param <T>  the domain type the repository manages
 * @param <U> the type of the id of the entity the repository manages
 */
@NoRepositoryBean
public interface ReadOnlyRepository<T, U> extends Repository<T, U>, QueryByExampleExecutor<T> {
  Optional<T> findById(U id);
}
