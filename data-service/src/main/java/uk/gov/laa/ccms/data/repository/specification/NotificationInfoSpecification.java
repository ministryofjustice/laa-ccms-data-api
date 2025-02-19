package uk.gov.laa.ccms.data.repository.specification;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.NotificationInfo;

public final class NotificationInfoSpecification {

  public static Specification<NotificationInfo> filterBy(final long providerId,
      final String caseReferenceNumber, final String providerCaseReference,
      final String assignedToUserId, final String clientSurname, final Integer feeEarnerId,
      final Boolean includeClosed, final String notificationType,
      final LocalDate assignedDateFrom, final LocalDate assignedDateTo) {
    return (Root<NotificationInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Predicate predicate = cb.conjunction();

      predicate = cb.and(predicate, cb.equal(root.get("providerFirmId"), providerId));
      if (isNotEmpty(caseReferenceNumber)) {
        predicate = cb.and(predicate,
            cb.like(cb.upper(root.get("lscCaseRefReference")),
                "%" + caseReferenceNumber.toUpperCase() + "%"));
      }
      if (isNotEmpty(providerCaseReference)) {
        predicate = cb.and(predicate,
            cb.like(cb.upper(root.get("providerCaseReference")),
                "%" + providerCaseReference.toUpperCase() + "%"));
      }
      if (isNotEmpty(assignedToUserId)) {
        predicate = cb.and(predicate,
            cb.like(cb.upper(root.get("assignedTo")),
                "%" + assignedToUserId.toUpperCase() + "%"));
      }
      if (isNotEmpty(clientSurname)) {
        predicate = cb.and(predicate,
            cb.like(cb.upper(root.get("personLastName")),
                "%" + clientSurname.toUpperCase() + "%"));
      }
      if (Objects.nonNull(feeEarnerId)) {
        predicate = cb.and(predicate, cb.equal(root.get("feeEarnerPartyId"), feeEarnerId));
      }
      if (Boolean.FALSE.equals(includeClosed)) {
        predicate = cb.and(predicate, cb.equal(root.get("isOpen"), true));
      }
      if (isNotEmpty(notificationType)) {
        predicate = cb.and(predicate,
            cb.equal(cb.upper(root.get("actionNotificationInd")),
                notificationType.toUpperCase()));
      }
      if (Objects.nonNull(assignedDateFrom)) {
        predicate = cb.and(predicate,
            cb.greaterThanOrEqualTo(root.get("dateAssigned"), assignedDateFrom));
      }
      if (Objects.nonNull(assignedDateTo)) {
        predicate = cb.and(predicate,
            cb.lessThanOrEqualTo(root.get("dateAssigned"), assignedDateTo));
      }

      return predicate;
    };
  }
}
