package uk.gov.laa.ccms.data.repository;

import static uk.gov.laa.ccms.data.repository.BaseEntityManagerRepository.SqlOperand.EQUALS;
import static uk.gov.laa.ccms.data.repository.BaseEntityManagerRepository.SqlOperand.GTE;
import static uk.gov.laa.ccms.data.repository.BaseEntityManagerRepository.SqlOperand.LIKE;
import static uk.gov.laa.ccms.data.repository.BaseEntityManagerRepository.SqlOperand.LTE;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import uk.gov.laa.ccms.data.entity.NotificationInfo;

/**
 * Repository for searching and retrieving notification records using dynamic filters and
 * pagination.
 *
 * <p>This class interacts directly with the database view `XXCCMS_GET_NOTIF_INFO_V`
 * to fetch records related to notifications, applying dynamic filters and paginated results. It
 * provides an implementation using native SQL queries to support complex filter conditions.</p>
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
   * @param providerId            the ID of the provider firm to filter notifications by
   *                              (mandatory)
   * @param caseReferenceNumber   the case reference number to search for
   * @param providerCaseReference the provider-specific case reference to search for
   * @param assignedToUserId      the user ID of the person assigned to the notification
   * @param clientSurname         the surname of the client associated with the notification
   * @param feeEarnerId           the ID of the fee earner to filter by
   * @param includeClosed         whether to include closed notifications in the results
   * @param notificationType      the type of notification to filter results by
   * @param assignedDateFrom      the start date for filtering notifications by assignment date
   * @param assignedDateTo        the end date for filtering notifications by assignment date
   * @param pageable              the pagination and sorting information
   * @return a paginated list of NotificationInfo entities matching the specified filters
   */
  public Page<NotificationInfo> findAll(final long providerId,
      final String caseReferenceNumber, final String providerCaseReference,
      final String assignedToUserId, final String clientSurname, final Integer feeEarnerId,
      final Boolean includeClosed, final String notificationType,
      final LocalDate assignedDateFrom, final LocalDate assignedDateTo,
      final Pageable pageable) {

    Map<String, Object> queryParams = new HashMap<>();
    String filterSql = getFilterSql(queryParams, providerId, caseReferenceNumber,
        providerCaseReference,
        assignedToUserId, clientSurname, feeEarnerId, includeClosed,
        notificationType, assignedDateFrom, assignedDateTo);
    final String searchNotificationQuery =
        """
                SELECT * FROM XXCCMS.XXCCMS_GET_NOTIF_INFO_V
            """
            +
            filterSql
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
    setQueryParameters(query, queryParams);

    final String notificationCount =
        """
            SELECT COUNT(*) FROM XXCCMS.XXCCMS_GET_NOTIF_INFO_V
            """
            +
            filterSql;

    Query countQuery = entityManager.createNativeQuery(notificationCount);
    countQuery.setHint("org.hibernate.readOnly", true);
    setQueryParameters(countQuery, queryParams);
    long total = ((Number) countQuery.getSingleResult()).longValue();

    List<NotificationInfo> resultList = query.getResultList();
    return new PageImpl<>(resultList, pageable, total);
  }

  private static String getFilterSql(Map<String, Object> queryParams, Long providerId,
      String caseReferenceNumber, String providerCaseReference, String assignedToUser,
      String clientSurname, Integer feeEarnerId, Boolean includeClosed,
      String notificationType, LocalDate assignedDateFrom, LocalDate assignedDateTo) {

    StringJoiner whereClause = new StringJoiner(" AND ");

    addEqualsCondition(whereClause, queryParams, "PROVIDERFIRM_ID", providerId);
    addCondition(whereClause, queryParams,
        "LSC_CASE_REF_REFERENCE", LIKE, caseReferenceNumber, true);
    addCondition(whereClause, queryParams,
        "PROVIDER_CASE_REFERENCE", LIKE, providerCaseReference, true);
    addCondition(whereClause, queryParams,
        "ASSIGNED_TO", LIKE, assignedToUser, true);
    addCondition(whereClause, queryParams,
        "PERSON_LAST_NAME", LIKE, clientSurname, true);
    addEqualsCondition(whereClause, queryParams,
        "FEE_EARNER_PARTY_ID", feeEarnerId);
    // Include all, or only open notifications
    if (Boolean.FALSE.equals(includeClosed)) {
      addEqualsCondition(whereClause, queryParams,
          "IS_OPEN", true);
    }
    addCondition(whereClause, queryParams,
        "ACTION_NOTIFICATION_IND", EQUALS, notificationType, false);
    addCondition(whereClause, queryParams,
        "DATE_ASSIGNED", GTE, assignedDateFrom);
    addCondition(whereClause, queryParams,
        "DATE_ASSIGNED", LTE, assignedDateTo);
    return whereClause.length() > 0 ? "WHERE " + whereClause + " " : "";
  }


}
