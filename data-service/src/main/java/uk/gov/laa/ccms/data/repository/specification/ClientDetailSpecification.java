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

public final class ClientDetailSpecification {

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
