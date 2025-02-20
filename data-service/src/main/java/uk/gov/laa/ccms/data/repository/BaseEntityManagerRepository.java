package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;

@RequiredArgsConstructor
public abstract class BaseEntityManagerRepository<T> {

  protected final EntityManager entityManager;
  
  public abstract Class<T> getEntityClazz();

  @Transactional
  public Page<T> findAll(final Specification<T> specification, final Pageable pageable) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    // Build the main query
    CriteriaQuery<T> mainQuery = criteriaBuilder.createQuery(getEntityClazz());
    Root<T> mainQueryRoot = mainQuery.from(getEntityClazz());
    applyWhereClause(mainQuery, specification, criteriaBuilder, mainQueryRoot);
    applySortingClause(mainQuery, pageable, criteriaBuilder, mainQueryRoot);

    Query query = entityManager.createQuery(mainQuery);
    query.setFirstResult((int) pageable.getOffset());
    query.setMaxResults(pageable.getPageSize());
    List<T> resultList = query.getResultList();

    // Build the count query
    long totalCount = getCount(specification, criteriaBuilder);

    return new PageImpl<>(resultList, pageable, totalCount);
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
    Root<T> countRoot = countQuery.from(getEntityClazz());
    countQuery.select(criteriaBuilder.count(countRoot.get("id")));
    applyWhereClause(countQuery, specification, criteriaBuilder, countRoot);
    return entityManager.createQuery(countQuery).getSingleResult();
  }


}
