package uk.gov.laa.ccms.data.repository.specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.CaseSearch;

public class CaseSearchSpecification {

  private CaseSearchSpecification() {
  }

  public static Specification<CaseSearch> withFilters(String caseReferenceNumber,
      String providerCaseReference, String caseStatus, String clientSurname, Long feeEarnerId,
      Long officeId
  ) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
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
      if(Objects.nonNull(feeEarnerId)){
        predicates.add(criteriaBuilder.equal(root.get("feeEarnerPartyId"), feeEarnerId));
      }
      if(Objects.nonNull(officeId)){
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
