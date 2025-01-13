package uk.gov.laa.ccms.data.repository.specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.CaseSearch;

/**
 * A utility class for creating specifications to filter and query {@link CaseSearch} entities.
 *
 * <p>The {@code CaseSearchSpecification} class provides mechanisms to construct dynamic query
 * criteria using the JPA Specification API. It allows filtering of {@link CaseSearch} entities
 * based on various attributes such as case reference number, provider case reference, case
 * status, client surname, fee earner ID, and office ID.</p>
 *
 * <p>The filters include:</p>
 * <ul>
 *  <li>Provider firm party ID (exact match, mandatory).</li>
 *  <li>Case reference number (partial matching support).</li>
 *  <li>Provider case reference (partial matching support).</li>
 *  <li>Case status (exact match).</li>
 *  <li>Client surname (partial matching, case insensitive).</li>
 *  <li>Fee earner ID (exact match).</li>
 *  <li>Office ID (exact match).</li>
 * </ul>
 *
 * <p>This allows querying for cases based on multiple combinations of these filters,
 * with all specified conditions combined using an AND operator.</p>
 *
 * @see CaseSearch
 * @see Specification
 * @see uk.gov.laa.ccms.data.repository.CaseSearchRepository
 * @author Jamie Briggs
 */
public class CaseSearchSpecification {

  /**
   * Private constructor to prevent instantiation of the CaseSearchSpecification class.
   */
  private CaseSearchSpecification() {
  }

  /**
   * Creates a {@link Specification} for filtering {@link CaseSearch} objects based on various
   * criteria such as case reference number, provider case reference, case status,
   * client surname, fee earner ID, and office ID. Multiple filters are combined using
   * an AND logic.
   *
   * @param providerFirmPartyId the provider firm party ID to filter by (exact match).
   * @param caseReferenceNumber the case reference number to filter by
   *                            (optional, partial match).
   * @param providerCaseReference the provider case
   *                              reference to filter by (optional, partial match).
   * @param caseStatus the case status to filter by (optional, exact match).
   * @param clientSurname the client
   *                      surname to filter by (optional, partial match, case-insensitive).
   * @param feeEarnerId the fee earner ID to filter by (optional, exact match).
   * @param officeId the office ID to filter by (optional, exact match).
   * @return a {@link Specification} object encapsulating the filtering logic for
   *     {@link CaseSearch} entities.
   */
  public static Specification<CaseSearch> withFilters(final long providerFirmPartyId,
      final String caseReferenceNumber,
      final String providerCaseReference,
      final String caseStatus,
      final String clientSurname,
      final Long feeEarnerId,
      final Long officeId
  ) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      predicates.add(criteriaBuilder.equal(root.get("providerFirmPartyId"), providerFirmPartyId));
      if (stringNotEmpty(caseReferenceNumber)) {
        predicates.add(
            criteriaBuilder.like(root.get("lscCaseReference"), "%" + caseReferenceNumber + "%"));
      }
      if (stringNotEmpty(providerCaseReference)) {
        predicates.add(
            criteriaBuilder.like(root.get("cisCaseReference"), "%" + providerCaseReference + "%"));
      }
      if (stringNotEmpty(caseStatus)) {
        predicates.add(criteriaBuilder.equal(root.get("actualCaseStatus"), caseStatus));
      }
      if (stringNotEmpty(clientSurname)) {
        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("personLastName")),
            "%" + clientSurname.toLowerCase() + "%"));
      }
      if (Objects.nonNull(feeEarnerId)) {
        predicates.add(criteriaBuilder.equal(root.get("feeEarnerPartyId"), feeEarnerId));
      }
      if (Objects.nonNull(officeId)) {
        predicates.add(criteriaBuilder.equal(root.get("providerOfficePartyId"), officeId));
      }
      // Combine all predicates with AND
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }

  private static boolean stringNotEmpty(String caseReferenceNumber) {
    return caseReferenceNumber != null && !caseReferenceNumber.isEmpty();
  }


}
