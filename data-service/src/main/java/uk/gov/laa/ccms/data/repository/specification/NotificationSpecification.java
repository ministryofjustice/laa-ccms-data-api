package uk.gov.laa.ccms.data.repository.specification;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.Notification;

public class NotificationSpecification {

  public static Specification<Notification> withFilters(
      String caseReferenceNumber,
      String providerCaseReference, String assignedToUserId, String clientSurname,
      Integer feeEarnerId, boolean includeClosed, String notificationType, LocalDate dateFrom,
      LocalDate dateTo
  ){
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      // Add predicates for each filter only if they are non-null
      if (caseReferenceNumber != null) {
        predicates.add(criteriaBuilder.like(root.get("lscCaseRefReference"), "%" + caseReferenceNumber + "%"));
      }
      if (providerCaseReference != null) {
        predicates.add(criteriaBuilder.like(root.get("providerCaseReference"), "%" + providerCaseReference + "%"));
      }
      if (assignedToUserId != null) {
        predicates.add(criteriaBuilder.equal(root.get("assignedTo"), assignedToUserId));
      }
      if (clientSurname != null) {
        predicates.add(criteriaBuilder.like(root.get("personLastName"), "%" + clientSurname + "%"));
      }
      if (feeEarnerId != null) {
        predicates.add(criteriaBuilder.equal(root.get("feeEarnerPartyId"), feeEarnerId));
      }
      if (!includeClosed) {
        predicates.add(criteriaBuilder.isTrue(root.get("isOpen")));
      }
      if (notificationType != null) {
        predicates.add(criteriaBuilder.equal(root.get("actionNotificationInd"), notificationType));
      }
      if (dateFrom != null) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateAssigned"), dateFrom));
      }
      if (dateTo != null) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateAssigned"), dateTo));
      }

      // Combine all predicates with AND
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }


}
