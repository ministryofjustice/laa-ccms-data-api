package uk.gov.laa.ccms.data.repository.specification;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.NotificationInfo;

/**
 * A utility class for creating specifications to filter and query notifications.
 *
 * <p>The {@link NotificationSpecification} class provides mechanisms to construct dynamic
 * query criteria using the JPA Specification API. It allows filtering of {@link NotificationInfo}
 * entities based on various attributes such as case reference number, provider case reference,
 * assigned user, client surname, and more.</p>
 *
 * <p>The filters include:</p>
 * <ul>
 *    <li>Case reference number (partial matching support).</li>
 *    <li>Provider case reference (partial matching support).</li>
 *    <li>Assigned user ID.</li>
 *    <li>Client surname (partial matching support).</li>
 *    <li>Fee earner ID.</li>
 *    <li>Should include closed notifications.</li>
 *    <li>NotificationInfo type.</li>
 *    <li>Date range (start and end dates).</li>
 * </ul>
 *
 * <p>This allows querying for notifications based on multiple combinations of these filters,
 * with all specified conditions combined.</p>
 *
 * @author Jamie Briggs
 * @see NotificationInfo
 * @see Specification
 * @see uk.gov.laa.ccms.data.repository.NotificationRepository
 */
public class NotificationSpecification {

  /**
   * Private constructor to prevent instantiation of the NotificationSpecification class.
   */
  private NotificationSpecification() {
  }

  /**
   * Builds a {@link Specification} for filtering {@link NotificationInfo} entities based on various
   * criteria. The method dynamically constructs filter conditions for the provided filter
   * parameters. Multiple filters are combined using an AND logic.
   *
   * @param providerId            the provider ID (exact match).
   * @param caseReferenceNumber   the case reference number to filter by (optional, partial match).
   * @param providerCaseReference the provider case reference to filter by (optional, partial
   *                              match, case-insensitive).
   * @param assignedToUserId      the user ID assigned to the notification (optional, exact match).
   * @param clientSurname         the client's surname to filter by (optional, partial match,
   *                              case-insensitive).
   * @param feeEarnerId           the ID of the fee earner to filter by (optional, exact match).
   * @param includeClosed         a flag to include closed notifications in the result set
   *                              (optional).
   * @param notificationType      the type of notification to filter by (optional, exact match).
   * @param dateFrom              the starting date for filtering notifications by the date assigned
   *                              (inclusive).
   * @param dateTo                the ending date for filtering notifications by the date assigned
   *                              (inclusive).
   * @return a {@link Specification} object encapsulating the filtering logic for
   * {@link NotificationInfo} entities.
   */
  public static Specification<NotificationInfo> withFilters(
      long providerId,
      String caseReferenceNumber,
      String providerCaseReference, String assignedToUserId, String clientSurname,
      Integer feeEarnerId, boolean includeClosed, String notificationType, LocalDate dateFrom,
      LocalDate dateTo
  ) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      predicates.add(criteriaBuilder.equal(root.get("providerFirmId"), providerId));

      // Add predicates for each filter only if they are non-null
      if (stringNotEmpty(caseReferenceNumber)) {
        predicates.add(criteriaBuilder.like(root.get("lscCaseRefReference"),
            "%" + caseReferenceNumber + "%"));
      }
      if (stringNotEmpty(providerCaseReference)) {
        predicates.add(
            criteriaBuilder.like(criteriaBuilder.upper(root.get("providerCaseReference")),
                "%" + providerCaseReference.toUpperCase() + "%"));
      }
      if (Objects.nonNull(assignedToUserId)) {
        predicates.add(criteriaBuilder.equal(root.get("assignedTo"), assignedToUserId));
      }
      if (stringNotEmpty(clientSurname)) {
        predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("personLastName")),
            "%" + clientSurname.toUpperCase() + "%"));
      }
      if (Objects.nonNull(feeEarnerId)) {
        predicates.add(criteriaBuilder.equal(root.get("feeEarnerPartyId"), feeEarnerId));
      }
      if (Boolean.FALSE.equals(includeClosed)) {
        predicates.add(criteriaBuilder.equal(root.get("isOpen"), "true"));
      }
      if (Objects.nonNull(notificationType)) {
        predicates.add(criteriaBuilder.equal(root.get("actionNotificationInd"), notificationType));
      }
      if (Objects.nonNull(dateFrom)) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateAssigned"), dateFrom));
      }
      if (Objects.nonNull(dateTo)) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateAssigned"), dateTo));
      }

      // Combine all predicates with AND
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };

  }

  private static boolean stringNotEmpty(String caseReferenceNumber) {
    return caseReferenceNumber != null && !caseReferenceNumber.isEmpty();
  }


}
