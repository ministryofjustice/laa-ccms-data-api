package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.laa.ccms.data.entity.CaseSearch;

/**
 * A repository class for performing search queries on {@link CaseSearch} entities.
 *
 * <p>This class utilizes a native SQL query to fetch filtered and paginated results
 * from the <b>XXCCMS_CASE_SEARCH_V</b> database view. It combines filtering criteria such
 * as provider firm party ID, case references, case status, client surname, fee earner ID, and
 * provider office party ID to dynamically construct the query based on the passed parameters.</p>
 *
 * <p>It relies on {@link EntityManager} to execute native SQL queries and doesn't use standard
 * Spring Data repositories. All queries are read-only and do not modify the database state.</p>
 *
 * @see Page
 * @see CaseSearch
 * @see EntityManager
 *
 * @author Jamie Briggs
 */
@Component
@RequiredArgsConstructor
public class CaseSearchRepository {

  private final EntityManager entityManager;

  /**
   * Retrieves a paginated and filtered list of case search records based on the given parameters.
   *
   * @param providerFirmPartyId the unique identifier of the provider firm (mandatory)
   * @param caseReferenceNumber the case reference number for filtering, can be partially matched
   * @param providerCaseReference the reference number specific to the provider for filtering
   * @param caseStatus the status of the case to filter by
   * @param clientSurname the surname of the client to filter by; case-insensitive partial
   *                      matches are allowed
   * @param feeEarnerId the unique identifier of the associated fee earner to filter by
   * @param officeId the unique identifier of the provider's office to filter by
   * @param pageable the pagination and sorting information
   * @return a paginated list of {@code CaseSearch} entities matching the filtering criteria
   */
  @Transactional(readOnly = true)
  public Page<CaseSearch> findAll(final long providerFirmPartyId, final String caseReferenceNumber,
      final String providerCaseReference, final String caseStatus, final String clientSurname,
      final Long feeEarnerId, final Long officeId, final Pageable pageable) {

    final String searchCaseQuery =
        """
        SELECT * FROM XXCCMS.XXCCMS_CASE_SEARCH_V
        """
        +
        getFilterSql(providerFirmPartyId, caseReferenceNumber, providerCaseReference, caseStatus,
          clientSurname, feeEarnerId, officeId)
        +
        """
        OFFSET :offset ROWS FETCH NEXT :size ROWS ONLY
        """;

    Query query = entityManager.createNativeQuery(searchCaseQuery, CaseSearch.class);
    query.setHint("org.hibernate.readOnly", true);
    query.setParameter("offset", pageable.getOffset());
    query.setParameter("size", pageable.getPageSize());

    final String countCaseQuery =
        """
        SELECT COUNT(*) FROM XXCCMS.XXCCMS_CASE_SEARCH_V
        """
        + getFilterSql(providerFirmPartyId, caseReferenceNumber, providerCaseReference,
        caseStatus, clientSurname, feeEarnerId, officeId);
    Query countQuery = entityManager.createNativeQuery(countCaseQuery);

    countQuery.setHint("org.hibernate.readOnly", true);
    long total = ((Number) countQuery.getSingleResult()).longValue();

    List<CaseSearch> resultList = query.getResultList();

    return new PageImpl<>(resultList, pageable, total);
  }

  private static String getFilterSql(long providerFirmPartyId, String caseReferenceNumber,
      String providerCaseReference, String caseStatus, String clientSurname, Long feeEarnerId,
      Long officeId) {
    StringJoiner sj = new StringJoiner(" AND ");
    // Provider firm party id
    sj.add("WHERE PROVIDER_FIRM_PARTY_ID = " + providerFirmPartyId);
    // Case reference number
    if (!Objects.isNull(caseReferenceNumber) && !caseReferenceNumber.isBlank()) {
      sj.add("LSC_CASE_REFERENCE LIKE '%" + caseReferenceNumber + "%'");
    }
    // Provider case reference
    if (!Objects.isNull(providerCaseReference) && !providerCaseReference.isBlank()) {
      sj.add("UPPER(PROVIDER_CASE_REFERENCE) LIKE '%" + providerCaseReference.toUpperCase() + "%'");
    }
    // Case status
    if (!Objects.isNull(caseStatus) && !caseStatus.isBlank()) {
      sj.add("ACTUAL_CASE_STATUS = '" + caseStatus + "'");
    }
    // Surname
    if (!Objects.isNull(clientSurname) && !clientSurname.isBlank()) {
      sj.add("UPPER(PERSON_LAST_NAME) LIKE '%" + clientSurname.toUpperCase() + "%'");
    }
    // Fee earner party ID
    if (!Objects.isNull(feeEarnerId)) {
      sj.add("FEE_EARNER_PARTY_ID = " + feeEarnerId);
    }
    // Provider office party ID
    if (!Objects.isNull(officeId)) {
      sj.add("PROVIDER_OFFICE_PARTY_ID = " + officeId);
    }
    return sj + " ";
  }

}
