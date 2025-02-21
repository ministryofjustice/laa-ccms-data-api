package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract base class for implementing repositories using an EntityManager to interact
 * with the database. This class provides a method for allowing search queries using
 * an associated {@link Specification} to filter and {@link Pageable} for paginating results.
 *
 *
 * @param <T> the entity type managed by this repository
 * @author Jamie Briggs
 */
public abstract class BaseEntityManagerRepository<T> {

  protected final EntityManager entityManager;
  private final Class<T> entityClazz;

  /**
   * Constructor for BaseEntityManagerRepository.
   *
   * @param entityManager the EntityManager instance used to interact with the persistence context.
   */
  protected BaseEntityManagerRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
    this.entityClazz = (Class<T>) ((ParameterizedType) getClass()
        .getGenericSuperclass())
        .getActualTypeArguments()[0];

  }

  /**
   * Finds all entities of type {@code T} that match the provided {@link Specification}
   * and are paginated according to the given pageable parameter.
   *
   * <p>This method executes two asynchronous database queries: one for fetching the results
   * that match the specification, and another for retrieving the total count of records
   * matching the same specification. The results are then combined into
   * a {@link Page} object.</p>
   *
   * @param specification the specification to filter the entities. Represents the predicate
   *                      for filtering results, which can include conditions on entity attributes.
   * @param pageable      the pagination and sorting information. Contains information
   *                      about page size, current page, and sorting.
   * @return a {@link Page} object containing the results that match the
   *                      {@link Specification} within the bounds of the pagination, and the
   *                      total count of matching records.
   */
  @Transactional(readOnly = true)
  public Page<T> findAll(final Specification<T> specification, final Pageable pageable) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    // Get all results
    CompletableFuture<List<T>> resultListFuture =
        CompletableFuture.supplyAsync(() -> getResultList(specification, pageable,
            criteriaBuilder));

    // Build the count query
    CompletableFuture<Long> countFuture =
        CompletableFuture.supplyAsync(() -> getCount(specification, criteriaBuilder));

    // Wait for both tasks to complete
    try {
      List<T> resultList = resultListFuture.get(); // Main query results
      long totalCount = countFuture.get();  // Count query results
      return new PageImpl<>(resultList, pageable, totalCount);
    } catch (Exception e) {
      throw new RuntimeException("Error executing queries", e);
    }
  }

  private List<T> getResultList(Specification<T> specification, Pageable pageable,
      CriteriaBuilder criteriaBuilder) {
    CriteriaQuery<T> mainQuery = criteriaBuilder.createQuery(entityClazz);
    Root<T> mainQueryRoot = mainQuery.from(entityClazz);
    applyWhereClause(mainQuery, specification, criteriaBuilder, mainQueryRoot);
    applySortingClause(mainQuery, pageable, criteriaBuilder, mainQueryRoot);

    Query query = entityManager.createQuery(mainQuery);
    query.setFirstResult((int) pageable.getOffset());
    query.setMaxResults(pageable.getPageSize());
    List<T> resultList = query.getResultList();
    return resultList;
  }

  private void applyWhereClause(CriteriaQuery<?> query, Specification<T> specification,
      CriteriaBuilder criteriaBuilder, Root<T> root) {
    if (specification != null) {
      Predicate predicate = specification.toPredicate(root, query, criteriaBuilder);
      if (predicate != null) {
        query.where(predicate);
      }
    }
  }

  private void applySortingClause(CriteriaQuery<T> query, Pageable pageable,
      CriteriaBuilder criteriaBuilder, Root<T> root) {
    if (pageable.getSort().isSorted()) {
      query.orderBy(QueryUtils.toOrders(pageable.getSort(), root, criteriaBuilder));
    }
  }

  private long getCount(Specification<T> specification, CriteriaBuilder criteriaBuilder) {
    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
    Root<T> countRoot = countQuery.from(entityClazz);
    countQuery.select(criteriaBuilder.count(countRoot.get(getIdPropertyName())));
    applyWhereClause(countQuery, specification, criteriaBuilder, countRoot);
    return entityManager.createQuery(countQuery).getSingleResult();
  }

  private String getIdPropertyName() {
    return java.util.Arrays.stream(entityClazz.getDeclaredFields())
        .filter(field -> field.isAnnotationPresent(jakarta.persistence.Id.class))
        .findFirst()
        .map(java.lang.reflect.Field::getName)
        .orElseThrow(() -> new RuntimeException(
            "No @Id annotation found in class: " + entityClazz.getName()));
  }
  
  


}
