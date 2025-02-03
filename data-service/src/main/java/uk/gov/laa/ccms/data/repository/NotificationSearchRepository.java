package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import uk.gov.laa.ccms.data.entity.NotificationInfo;

@Component
@RequiredArgsConstructor
public final class NotificationSearchRepository {

  private final EntityManager entityManager;

  public Page<NotificationInfo> findAll(Long providerId,
      String caseReferenceNumber, String providerCaseReference, String assignedToUserId,
      String clientSurname, Integer feeEarnerId, Boolean includeClosed,
      String notificationType, LocalDate dateFrom, LocalDate dateTo, Pageable pageable){

    final String searchNotificationQuery =
        """
        SELECT * FROM XXCCMS.XXCCMS_GET_NOTIF_INFO_V
        """
        +
            getFilterSql(providerId, caseReferenceNumber, providerCaseReference,
                assignedToUserId, clientSurname, feeEarnerId, includeClosed,
                notificationType, dateFrom, dateTo, pageable)
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
                notificationType, dateFrom, dateTo, pageable);

    Query countQuery = entityManager.createNativeQuery(notificationCount);

    countQuery.setHint("org.hibernate.readOnly", true);
    long total = ((Number) countQuery.getSingleResult()).longValue();

    List<NotificationInfo> resultList = query.getResultList();
    return new PageImpl<>(resultList, pageable, total);
  }

  private static String getFilterSql(Long providerId,
      String caseReferenceNumber, String providerCaseReference, String assignedToUser,
      String clientSurname, Integer feeEarnerId, Boolean includeClosed,
      String notificationType, LocalDate dateFrom, LocalDate dateTo, Pageable pageable) {

    StringJoiner sj = new StringJoiner(" AND ");
    // Provider firm party id
    sj.add("WHERE PROVIDERFIRM_ID = " + providerId);
    // Case reference number
    if (stringNotEmpty(caseReferenceNumber)) {
      sj.add("LSC_CASE_REF_REFERENCE LIKE '%" + caseReferenceNumber + "%'");
    }
    // Provider case reference
    if (stringNotEmpty(providerCaseReference)) {
      sj.add("UPPER(PROVIDER_CASE_REFERENCE) LIKE '%" + providerCaseReference.toUpperCase() + "%'");
    }
    // Assigned to user ID
    if (stringNotEmpty(assignedToUser)) {
      sj.add("UPPER(ASSIGNED_TO) LIKE '%" + assignedToUser.toUpperCase() + "%'");
    }
    // Client Surname
    if (stringNotEmpty(clientSurname)) {
      sj.add("UPPER(PERSON_LAST_NAME) LIKE '%" + clientSurname.toUpperCase() + "%'");
    }
    // Fee Earner ID
    if (!Objects.isNull(feeEarnerId)) {
      sj.add("UPPER(FEE_EARNER_PARTY_ID) = " + feeEarnerId);
    }
    // Include closed (If true, include all)
    if (Boolean.FALSE.equals(includeClosed)) {
      sj.add("IS_OPEN = true");
    }
    if(stringNotEmpty(notificationType)){
      sj.add("ACTION_NOTIFICATION_IND = '" + notificationType + "'");
    }
    if(Objects.nonNull(dateFrom)){
      sj.add("DATE_ASSIGNED >= '" + dateFrom + "'");
    }
    if(Objects.nonNull(dateTo)){
      sj.add("DATE_ASSIGNED <= '" + dateTo + "'");
    }
    return sj + " ";
  }


  private static boolean stringNotEmpty(String value) {
    return value != null && !value.isEmpty();
  }

}
