package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import uk.gov.laa.ccms.data.entity.NotificationInfo;

/**
 * Repository for searching and retrieving notification records
 *     using dynamic filters and pagination.
 *
 * <p>This class interacts directly with the database view `XXCCMS_GET_NOTIF_INFO_V`
 * to fetch records related to notifications, applying dynamic filters and paginated results.
 * It provides an implementation using native SQL queries to support complex filter conditions.</p>
 *
 * <p>Extends {@link BaseEntityManagerRepository} which contains helper methods
 * for helping build a SQL query and {@link EntityManager}.</p>
 *
 * @author Jamie Briggs
 * @see NotificationInfo
 * @see Pageable
 */
@Component
public final class NotificationSearchRepository extends BaseEntityManagerRepository {

  public NotificationSearchRepository(EntityManager entityManager) {
    super(entityManager);
  }

  /**
   * Retrieves a paginated list of NotificationInfo entities from the database, applying the
   * specified filters to narrow down the results.
   *
   * @param providerId             the ID of the provider firm to filter notifications
   *                                   by (mandatory)
   * @param caseReferenceNumber    the case reference number to search for
   * @param providerCaseReference  the provider-specific case reference to search for
   * @param assignedToUserId       the user ID of the person assigned to the notification
   * @param clientSurname          the surname of the client associated with the notification
   * @param feeEarnerId            the ID of the fee earner to filter by
   * @param includeClosed          whether to include closed notifications in the results
   * @param notificationType       the type of notification to filter results by
   * @param assignedDateFrom               the start date for filtering notifications by
   *                                       assignment date
   * @param assignedDateTo                 the end date for filtering notifications by
   *                                       assignment date
   * @param pageable               the pagination and sorting information
   * @return a paginated list of NotificationInfo entities matching the specified filters
   */
  public Page<NotificationInfo> findAll(final long providerId,
      final String caseReferenceNumber, final String providerCaseReference,
      final String assignedToUserId, final String clientSurname, final Integer feeEarnerId,
      final Boolean includeClosed, final String notificationType,
      final LocalDate assignedDateFrom, final LocalDate assignedDateTo,
      final Pageable pageable) {

    final String searchNotificationQuery =
        """
                SELECT * FROM XXCCMS.XXCCMS_GET_NOTIF_INFO_V
            """
            +
            getFilterSql(providerId, caseReferenceNumber, providerCaseReference,
                assignedToUserId, clientSurname, feeEarnerId, includeClosed,
                notificationType, assignedDateFrom, assignedDateTo)
            +
            getSortSql(pageable)
            +
        """
            OFFSET :offset ROWS FETCH NEXT :size ROWS ONLY    
        """;

    Query query = entityManager.createNativeQuery(searchNotificationQuery, NotificationInfo.class);
    query.setHint("org.hibernate.readOnly", true);
    query.setParameter("offset", pageable.getOffset());
    query.setParameter("size", pageable.getPageSize());

    final String notificationCount =
        """
        SELECT COUNT(*) FROM XXCCMS.XXCCMS_GET_NOTIF_INFO_V
        """
            +
            getFilterSql(providerId, caseReferenceNumber, providerCaseReference,
                assignedToUserId, clientSurname, feeEarnerId, includeClosed,
                notificationType, assignedDateFrom, assignedDateTo);

    Query countQuery = entityManager.createNativeQuery(notificationCount);

    countQuery.setHint("org.hibernate.readOnly", true);
    long total = ((Number) countQuery.getSingleResult()).longValue();

    List<NotificationInfo> resultList = query.getResultList();
    return new PageImpl<>(resultList, pageable, total);
  }

  private static String getFilterSql(Long providerId,
      String caseReferenceNumber, String providerCaseReference, String assignedToUser,
      String clientSurname, Integer feeEarnerId, Boolean includeClosed,
      String notificationType, LocalDate assignedDateFrom, LocalDate assignedDateTo) {

    StringJoiner sj = new StringJoiner(" AND ");
    // Provider firm party id

    
    sj.add("WHERE PROVIDERFIRM_ID = " + providerId);
    // Case reference number
    if (stringNotEmpty(caseReferenceNumber)) {
      sj.add("LSC_CASE_REF_REFERENCE LIKE '%" + sanitizeForSql(caseReferenceNumber) + "%'");
    }
    // Provider case reference
    if (stringNotEmpty(providerCaseReference)) {
      sj.add("UPPER(PROVIDER_CASE_REFERENCE) LIKE '%" + sanitizeForSql(
          providerCaseReference.toUpperCase()) + "%'");
    }
    // Assigned to user ID
    if (stringNotEmpty(assignedToUser)) {
      sj.add("UPPER(ASSIGNED_TO) LIKE '%" + sanitizeForSql(assignedToUser.toUpperCase()) + "%'");
    }
    // Client Surname
    if (stringNotEmpty(clientSurname)) {
      sj.add(
          "UPPER(PERSON_LAST_NAME) LIKE '%" + sanitizeForSql(clientSurname.toUpperCase()) + "%'");
    }
    // Fee Earner ID
    if (!Objects.isNull(feeEarnerId)) {
      sj.add("UPPER(FEE_EARNER_PARTY_ID) = " + feeEarnerId);
    }
    // Include closed (If true, include all)
    if (Boolean.FALSE.equals(includeClosed)) {
      sj.add("IS_OPEN = 'true'");
    }
    if (stringNotEmpty(notificationType)) {
      sj.add("ACTION_NOTIFICATION_IND = '" + sanitizeForSql(notificationType) + "'");
    }
    if (Objects.nonNull(assignedDateFrom)) {
      sj.add("DATE_ASSIGNED >= TO_DATE('" + assignedDateFrom + "', 'YYYY-MM-DD')");
    }
    if (Objects.nonNull(assignedDateTo)) {
      sj.add("DATE_ASSIGNED <= TO_DATE('" + assignedDateTo + "', 'YYYY-MM-DD')");
    }
    return sj + " ";
  }


}
