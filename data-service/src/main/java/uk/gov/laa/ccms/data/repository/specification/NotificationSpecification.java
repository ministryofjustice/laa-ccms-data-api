package uk.gov.laa.ccms.data.repository.specification;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.Notification;

/**
 * A utility class for creating specifications to filter and query notifications.
 *
 * <p>The {@link NotificationSpecification} class provides mechanisms to construct dynamic
 * query criteria using the JPA Specification API. It allows filtering of
 * {@link Notification} entities based on various attributes such as case reference number,
 * provider case reference, assigned user, client surname, and more.</p>
 *
 * <p>The filters include:</p>
 * <ul>
 *    <li>Case reference number (partial matching support).</li>
 *    <li>Provider case reference (partial matching support).</li>
 *    <li>Assigned user ID.</li>
 *    <li>Client surname (partial matching support).</li>
 *    <li>Fee earner ID.</li>
 *    <li>Should include closed notifications.</li>
 *    <li>Notification type.</li>
 *    <li>Date range (start and end dates).</li>
 * </ul>
 *
 * <p>This allows querying for notifications based on multiple combinations of these filters,
 * with all specified conditions combined.</p>
 *
 * @see Notification
 * @see Specification
 * @see uk.gov.laa.ccms.data.repository.NotificationRepository
 * @author Jamie Briggs
 */
public class NotificationSpecification {

  /**
   * Private constructor to prevent instantiation of the NotificationSpecification class.
   */
  private NotificationSpecification() {}

  /**
   * Builds a {@link Specification} for filtering {@link Notification} entities based
   * on various criteria. The method dynamically constructs filter
   * conditions for the provided filter parameters.
   *
   * @param caseReferenceNumber the case reference number to filter by (optional).
   * @param providerCaseReference the provider case reference to filter by (optional).
   * @param assignedToUserId the user ID assigned to the notification (optional).
   * @param clientSurname the client's surname to filter by (optional).
   * @param feeEarnerId the ID of the fee earner to filter by (optional).
   * @param includeClosed a flag to include closed notifications in the result set.
   * @param notificationType the type of notification to filter by (optional).
   * @param dateFrom the starting date for filtering notifications by the date assigned (inclusive).
   * @param dateTo the ending date for filtering notifications by the date assigned (inclusive).
   * @return a {@link Specification} object encapsulating the
   *     filtering logic for {@link Notification} entities.
   */
  public static Specification<Notification> withFilters(
      String caseReferenceNumber,
      String providerCaseReference, String assignedToUserId, String clientSurname,
      Integer feeEarnerId, boolean includeClosed, String notificationType, LocalDate dateFrom,
      LocalDate dateTo
  ) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      // Add predicates for each filter only if they are non-null
      if (caseReferenceNumber != null) {
        predicates.add(criteriaBuilder.like(root.get("lscCaseRefReference"),
            "%" + caseReferenceNumber + "%"));
      }
      if (providerCaseReference != null) {
        predicates.add(criteriaBuilder.like(root.get("providerCaseReference"),
            "%" + providerCaseReference + "%"));
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
        predicates.add(criteriaBuilder.equal(root.get("isOpen"), "true"));
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
