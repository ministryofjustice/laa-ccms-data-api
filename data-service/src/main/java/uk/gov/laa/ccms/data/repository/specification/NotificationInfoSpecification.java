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

/**
 * Utility class for creating a JPA {@link Specification} to query {@NotificationInfo} entities
 * using various filter criteria.
 * 
 * <p>The primary method @{code filterBy} allows dynamic construction of predicates for filtering 
 * notifications based on optional parameters such as case reference number, provider case 
 * reference and assigned user ID.</p>
 * 
 * <p>The resulting specification can be used with Spring Data JPA repositories to fetch
 * filtered results from the database.</p>
 */
public final class NotificationInfoSpecification {

  /**
   * Constructs a JPA {@link Specification} for filtering {@link NotificationInfo} records based on
   * various optional parameters. The resulting specification can be used to dynamically generate
   * predicates for querying notifications.
   *
   * @param providerId a mandatory identifier of the provider firm to filter notifications by
   * @param caseReferenceNumber an optional case reference number to filter by (matches partially,
   *                           case-insensitive)
   * @param providerCaseReference an optional provider case reference to filter by (matches
   *                           partially, case-insensitive)
   * @param assignedToUserId an optional user ID to filter assigned notifications (matches
   *                           partially, case-insensitive)
   * @param clientSurname an optional client surname to filter notifications (matches partially,
   *                           case-insensitive)
   * @param feeEarnerId an optional fee earner identifier to filter notifications
   * @param includeClosed an optional flag to determine whether to include closed notifications;
   *                           if false, only open notifications are included
   * @param notificationType an optional notification type identifier to filter by (exact match,
   *                           case-insensitive)
   * @param assignedDateFrom an optional start date to filter notifications assigned on or after
   *                           this date
   * @param assignedDateTo an optional end date to filter notifications assigned on or before
   *                           this date
   * @return a {@link Specification} of {@link NotificationInfo} containing the constructed
   *                            filtering predicates
   */
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
