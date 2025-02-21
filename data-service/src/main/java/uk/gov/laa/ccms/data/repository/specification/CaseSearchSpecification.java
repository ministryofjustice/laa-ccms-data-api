package uk.gov.laa.ccms.data.repository.specification;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.CaseSearch;

/**
 * Utility class for creating a JPA {@link Specification} to query {@link CaseSearch} entities
 * using various filter criteria.
 *
 * <p>The primary method {@code filterBy} allows dynamic construction of predicates
 * for filtering cases based on optional parameters such as case references,
 * provider details, client information, and case statuses.</p>
 *
 * <p>The resulting specification can be used with Spring Data JPA repositories
 * to fetch filtered results from the database.</p>
 *
 * @see Specification
 * @see CaseSearch
 * @author Jamie Briggs
 */
public final class CaseSearchSpecification {

  /**
   * Builds a specification to filter {@link CaseSearch} entities based on
   * multiple optional criteria.
   *
   * <p>Constructs predicates based on the provided parameters to match corresponding
   * fields in the entity.</p>
   *
   * @param providerFirmPartyId the identifier for the provider firm party (mandatory).
   * @param caseReferenceNumber the case reference number (matches exact value).
   * @param providerCaseReference the provider case reference (matches partially).
   * @param caseStatus the case status (matches exact value).
   * @param clientSurname the client surname (matches partially and case-insensitive).
   * @param feeEarnerId the fee earner identifier (matches exact value).
   * @param officeId the provider office identifier (matches exact value).
   * @return a {@link Specification} of type {@link CaseSearch} that can be used to filter results.
   */
  public static Specification<CaseSearch> filterBy(final long providerFirmPartyId,
      final String caseReferenceNumber,
      final String providerCaseReference, final String caseStatus, final String clientSurname,
      final Long feeEarnerId, final Long officeId) {
    return (Root<CaseSearch> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Predicate predicate = cb.conjunction();

      predicate = cb.and(predicate, cb.equal(root.get("providerFirmPartyId"), providerFirmPartyId));
      if (isNotEmpty(caseReferenceNumber)) {
        predicate = cb.and(predicate, cb.equal(root.get("lscCaseReference"),
            caseReferenceNumber));
      }
      if (isNotEmpty(providerCaseReference)) {
        predicate = cb.and(predicate, cb.like(root.get("providerCaseReference"),
            "%" + providerCaseReference + "%"));
      }
      if (isNotEmpty(caseStatus)) {
        predicate = cb.and(predicate, cb.equal(root.get("actualCaseStatus"), caseStatus));
      }
      if (isNotEmpty(clientSurname)) {
        predicate = cb.and(predicate,
            cb.like(cb.lower(root.get("personLastName")),
                "%" + clientSurname.toLowerCase() + "%"));
      }
      if (Objects.nonNull(feeEarnerId)) {
        predicate = cb.and(predicate, cb.equal(root.get("feeEarnerPartyId"), feeEarnerId));
      }
      if (Objects.nonNull(officeId)) {
        predicate = cb.and(predicate, cb.equal(root.get("providerOfficePartyId"), officeId));
      }

      return predicate;
    };

  }
}
