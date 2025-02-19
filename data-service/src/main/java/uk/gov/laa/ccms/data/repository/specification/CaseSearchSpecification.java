package uk.gov.laa.ccms.data.repository.specification;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.CaseSearch;

public final class CaseSearchSpecification {

  public static Specification<CaseSearch> filterBy(final long providerFirmPartyId,
      final String caseReferenceNumber,
      final String providerCaseReference, final String caseStatus, final String clientSurname,
      final Long feeEarnerId, final Long officeId) {
    return (Root<CaseSearch> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Predicate predicate = cb.conjunction();

      predicate = cb.and(predicate, cb.equal(root.get("providerFirmPartyId"), providerFirmPartyId));
      if(isNotEmpty(caseReferenceNumber)){
        predicate = cb.and(predicate, cb.equal(root.get("lscCaseReference"),
            caseReferenceNumber));
      }
      if(isNotEmpty(providerCaseReference)){
        predicate = cb.and(predicate, cb.like(root.get("providerCaseReference"),
            "%" + providerCaseReference + "%"));
      }
      if(isNotEmpty(caseStatus)){
        predicate = cb.and(predicate, cb.equal(root.get("actualCaseStatus"), caseStatus));
      }
      if (isNotEmpty(clientSurname)) {
        predicate = cb.and(predicate,
            cb.like(cb.lower(root.get("personLastName")),
                "%" + clientSurname.toLowerCase() + "%"));
      }
      if(Objects.nonNull(feeEarnerId)){
        predicate = cb.and(predicate, cb.equal(root.get("feeEarnerPartyId"), feeEarnerId));
      }
      if(Objects.nonNull(officeId)){
        predicate = cb.and(predicate, cb.equal(root.get("providerOfficePartyId"), officeId));
      }

      return predicate;
    };

  }
}
