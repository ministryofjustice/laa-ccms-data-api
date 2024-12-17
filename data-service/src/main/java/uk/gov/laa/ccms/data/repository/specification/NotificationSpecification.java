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
      Integer feeEarnerId, Boolean includeClosed, String notificationType, LocalDate dateFrom,
      LocalDate dateTo
  ){
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      // Add predicates for each filter only if they are non-null
      if (caseReferenceNumber != null) {
        predicates.add(criteriaBuilder.equal(root.get("caseReferenceNumber"), caseReferenceNumber));
      }
      if (providerCaseReference != null) {
        predicates.add(criteriaBuilder.equal(root.get("providerCaseReference"), providerCaseReference));
      }
      if (assignedToUserId != null) {
        predicates.add(criteriaBuilder.equal(root.get("assignedTo"), assignedToUserId));
      }
      if (clientSurname != null) {
        predicates.add(criteriaBuilder.like(root.get("clientName"), "%" + clientSurname + "%"));
      }
      if (feeEarnerId != null) {
        predicates.add(criteriaBuilder.equal(root.get("feeEarnerId"), feeEarnerId));
      }
      if (includeClosed != null) {
        if (!includeClosed) {
          predicates.add(criteriaBuilder.isFalse(root.get("isClosed")));
        }
      }
      if (notificationType != null) {
        predicates.add(criteriaBuilder.equal(root.get("notificationType"), notificationType));
      }
      if (dateFrom != null) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), dateFrom));
      }
      if (dateTo != null) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"), dateTo));
      }

      // Combine all predicates with AND
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }


}
