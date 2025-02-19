package uk.gov.laa.ccms.data.repository;

import static uk.gov.laa.ccms.data.repository.BaseEntityManagerRepository.SqlOperand.EQUALS;
import static uk.gov.laa.ccms.data.repository.BaseEntityManagerRepository.SqlOperand.LIKE;

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
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.ClientDetail;

/**
 * Repository for searching and retrieving client detail records using dynamic filters
 *     and pagination.
 *
 * <p>This class interacts directly with the database view <b>XXCCMS_GET_CLIENT_DETAILS_V</b>
 * to fetch records relating to client details, appying dynamic filters and paginated results.
 * It provides an implementation using native SQL queries to support complex filter conditions.</p>
 *
 * <p>Extends {@link BaseEntityManagerRepository} which contains helper methods for helping
 * build a SQL query and {@link EntityManager}.</p>
 *
 * @author Jamie Briggs
 * @see ClientDetail
 * @see Pageable
 */
@Repository
public class ClientDetailRepository extends BaseEntityManagerRepository {

  public ClientDetailRepository(EntityManager entityManager) {
    super(entityManager);
  }

  /**
   * Retrieves a paginated list of {@link ClientDetail} objects based on the provided search
   *     criteria and pageable options.
   *
   * @param firstName              the first name of the client to search for; supports fuzzy
   *                                   matching and case insensitivity
   * @param surname                the surname of the client to search for; supports fuzzy
   *                                   matching and case insensitivity
   * @param dateOfBirth            the date of birth of the client to search for; supports
   *                                   exact matching
   * @param gender                 the gender of the client to search for; supports exact
   *                                   matching and case insensitivity
   * @param clientReferenceNumber  the client reference number to search for; supports fuzzy
   *                                   matching and case insensitivity
   * @param homeOfficeReference    the home office reference to search for; supports fuzzy
   *                                   matching and case insensitivity
   * @param nationalInsuranceNumber the national insurance number to search for; supports
   *                                   fuzzy matching and case insensitivity
   * @param pageable               pagination and sorting information to apply to the results
   * @return a {@link Page} of {@link ClientDetail} instances that match the search criteria,
   *     including pagination data
   */
  public Page<ClientDetail> findAll(final String firstName, final String surname,
      final LocalDate dateOfBirth, final String gender, final String clientReferenceNumber,
      final String homeOfficeReference, final String nationalInsuranceNumber,
      final Pageable pageable) {

    Map<String, Object> queryParams = new HashMap<>();
    String filterSql = getFilterSql(queryParams, firstName, surname, dateOfBirth, gender,
        clientReferenceNumber, homeOfficeReference, nationalInsuranceNumber);
    final String searchCaseQuery =
        "SELECT * FROM XXCCMS.XXCCMS_GET_CLIENT_DETAILS_V "
        + filterSql
        + getSortSql(pageable)
        + "OFFSET :offset ROWS FETCH NEXT :size ROWS ONLY";

    Query query = entityManager.createNativeQuery(searchCaseQuery, ClientDetail.class);
    query.setHint("org.hibernate.readOnly", true);
    query.setParameter("offset", pageable.getOffset());
    query.setParameter("size", pageable.getPageSize());
    setQueryParameters(query, queryParams);

    final String countClientDetails =
        "SELECT COUNT(*) FROM XXCCMS.XXCCMS_GET_CLIENT_DETAILS_V "
        + filterSql;

    Query countQuery = entityManager.createNativeQuery(countClientDetails);
    countQuery.setHint("org.hibernate.readOnly", true);
    setQueryParameters(countQuery, queryParams);

    long total = ((Number) countQuery.getSingleResult()).longValue();

    List<ClientDetail> resultList = query.getResultList();

    return new PageImpl<>(resultList, pageable, total);
  }

  private static String getFilterSql(Map<String, Object> queryParams,
      final String firstName, final String surnameAtBirth,
      final LocalDate dateOfBirth, final String gender, final String clientReferenceNumber,
      final String homeOfficeReference, final String nationalInsuranceNumber) {
    StringJoiner whereClause = new StringJoiner(" AND ");

    addCondition(whereClause, queryParams,
        "FIRSTNAME", LIKE, firstName, true);
    addCondition(whereClause, queryParams,
        "SURNAME_AT_BIRTH", LIKE, surnameAtBirth, true);
    addCondition(whereClause, queryParams,
        "DATE_OF_BIRTH", EQUALS, dateOfBirth);
    addCondition(whereClause, queryParams,
        "GENDER", EQUALS, gender, true);
    addCondition(whereClause, queryParams,
        "TO_CHAR(CLIENT_REFERENCE_NUMBER)", LIKE,
        clientReferenceNumber, true);
    addCondition(whereClause, queryParams,
        "HOME_OFFICE_NUMBER", LIKE,
        homeOfficeReference, true);
    addCondition(whereClause, queryParams,
        "NI_NUMBER", LIKE,
        nationalInsuranceNumber, true);
    return whereClause.length() > 0 ? "WHERE " + whereClause + " " : "";
  }


}
