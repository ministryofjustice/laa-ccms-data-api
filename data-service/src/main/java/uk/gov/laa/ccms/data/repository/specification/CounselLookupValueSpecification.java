package uk.gov.laa.ccms.data.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.CounselLookupValue;

/**
 * Utility class for creating a JPA {@link Specification} to query {@link CounselLookupValue}
 * entities using various filter criteria.
 *
 * <p>The primary method {@code filter} allows dynamic construction of predicates for filtering
 * details based on optional parameters such as name, company, legalAidSuppNumber and category.
 *
 * <p>The resulting specification can be used with Spring Data JPA repositories to fetch filtered
 * results from the database.
 *
 * @see Specification
 * @see CounselLookupValue
 * @author Ashutosh Gautam
 */
public class CounselLookupValueSpecification {

  /**
   * Builds a specification to filter {@link CounselLookupValue} entities based on multiple optional
   * criteria.
   *
   * <p>Constructs predicates based on the provided parameters to match corresponding fields in the
   * entity.
   *
   * @param name name of counsel
   * @param company company value
   * @param legalAidSuppNumber laaCounselReference value
   * @param category category value
   * @return returns the CounselLookupValue specification
   * @author Ashutosh Gautam
   */
  public static Specification<CounselLookupValue> filter(
      String name, String category, String company, String legalAidSuppNumber) {

    return (Root<CounselLookupValue> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Predicate predicate = cb.conjunction();

      if (name != null && !name.isEmpty()) {
        predicate =
            cb.and(predicate, cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
      }

      if (company != null && !company.isEmpty()) {
        predicate =
            cb.and(
                predicate,
                cb.like(cb.upper(root.get("company")), "%" + company.toUpperCase() + "%"));
      }

      if (legalAidSuppNumber != null && !legalAidSuppNumber.isEmpty()) {
        predicate =
            cb.and(
                predicate,
                cb.like(
                    cb.upper(root.get("legalAidSuppNumber")),
                    "%" + legalAidSuppNumber.toUpperCase() + "%"));
      }

      if (category != null && !category.isEmpty()) {
        predicate = cb.and(predicate, cb.like(root.get("category"), "%" + category + "%"));
      }

      return predicate;
    };
  }
}
