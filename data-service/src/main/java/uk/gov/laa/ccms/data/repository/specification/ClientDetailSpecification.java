package uk.gov.laa.ccms.data.repository.specification;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.ClientDetail;

/**
 * Utility class for creating a JPA {@link Specification} to query {@link ClientDetail} entities
 * using various filter criteria.
 *
 * <p>The primary method {@code filterBy} allows dynamic construction of predicates
 * for filtering client details based on optional parameters such as first name, the clients
 * surname at birth, and date of birth.</p>
 *
 * <p>The resulting specification can be used with Spring Data JPA repositories
 * to fetch filtered results from the database.</p>
 *
 * @see Specification
 * @see ClientDetail
 * @author Jamie Briggs
 */
public final class ClientDetailSpecification {

  /**
   * Builds a specification to filter {@link ClientDetail} entities based on
   * multiple optional criteria.
   *
   * <p>Constructs predicates based on the provided parameters to match corresponding
   * fields in the entity.</p>
   *
   *
   * @param firstName the first name of the client to (matches partially, case-insensitive)
   * @param surnameAtBirth the surname at birth of the client to (matches partially,
   *                       case-insensitive)
   * @param dateOfBirth the date of birth of the client to filter by
   * @param gender the gender of the client to (case-insensitive)
   * @param clientReferenceNumber the client reference number to (matches partially,
   *                       case-insensitive)
   * @param homeOfficeReference the home office reference to (matches partially, case-insensitive)
   * @param nationalInsuranceNumber the national insurance number to (matches partially,
   *                       case-insensitive)
   *
   * @return a {@link Specification} of type {@link ClientDetail} that can be used to filter 
   *     results.
   */
  public static Specification<ClientDetail> filterBy(final String firstName,
      final String surnameAtBirth,
      final LocalDate dateOfBirth, final String gender, final String clientReferenceNumber,
      final String homeOfficeReference, final String nationalInsuranceNumber) {
    return (Root<ClientDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Predicate predicate = cb.conjunction();

      if (isNotEmpty(firstName)) {
        predicate = cb.and(predicate,
            cb.like(cb.upper(root.get("firstName")), "%" + firstName.toUpperCase() + "%"));
      }
      if (isNotEmpty(surnameAtBirth)) {
        predicate = cb.and(predicate,
            cb.like(cb.upper(root.get("surnameAtBirth")),
                "%" + surnameAtBirth.toUpperCase() + "%"));
      }
      if (Objects.nonNull(dateOfBirth)) {
        predicate = cb.and(predicate, cb.equal(root.get("dateOfBirth"), dateOfBirth));
      }
      if (isNotEmpty(gender)) {
        predicate = cb.and(predicate,
            cb.equal(cb.upper(root.get("gender")), gender.toUpperCase()));
      }
      if (isNotEmpty(clientReferenceNumber)) {
        predicate = cb.and(predicate,
            cb.like(cb.upper(cb.toString(root.get("clientReferenceNumber"))),
                "%" + clientReferenceNumber.toUpperCase() + "%"));
      }
      if (isNotEmpty(homeOfficeReference)) {
        predicate = cb.and(predicate,
            cb.like(cb.upper(root.get("homeOfficeNumber")),
                "%" + homeOfficeReference.toUpperCase() + "%"));
      }
      if (isNotEmpty(nationalInsuranceNumber)) {
        predicate = cb.and(predicate,
            cb.like(cb.upper(root.get("nationalInsuranceNumber")),
                "%" + nationalInsuranceNumber.toUpperCase() + "%"));
      }

      return predicate;
    };
  }
}
