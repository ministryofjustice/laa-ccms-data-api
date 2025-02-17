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
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.ClientDetail;

@Repository
public class ClientDetailRepository extends BaseEntityManagerRepository {


  public ClientDetailRepository(EntityManager entityManager) {
    super(entityManager);
  }

  public Page<ClientDetail> findAll(final String firstName, final String surname,
      final LocalDate dateOfBirth, final String gender, final String clientReferenceNumber,
      final String homeOfficeReference, final String nationalInsuranceNumber,
      final Pageable pageable){

    final String searchCaseQuery =
        """
             SELECT * FROM XXCCMS.XXCCMS_GET_CLIENT_DETAILS_V
            """
        + getFilterSql(firstName, surname, dateOfBirth, gender, clientReferenceNumber,
            homeOfficeReference, nationalInsuranceNumber)
        +
            getSortSql(pageable) +
        """
            OFFSET :offset ROWS FETCH NEXT :size ROWS ONLY
            """;

    Query query = entityManager.createNativeQuery(searchCaseQuery, ClientDetail.class);
    query.setHint("org.hibernate.readOnly", true);
    query.setParameter("offset", pageable.getOffset());
    query.setParameter("size", pageable.getPageSize());

    final String countClientDetails =
        """
        SELECT COUNT(*) FROM XXCCMS.XXCCMS_GET_CLIENT_DETAILS_V
        """
        + getFilterSql(firstName, surname, dateOfBirth, gender, clientReferenceNumber,
            homeOfficeReference, nationalInsuranceNumber);

    Query countQuery = entityManager.createNativeQuery(countClientDetails);

    countQuery.setHint("org.hibernate.readOnly", true);
    long total = ((Number) countQuery.getSingleResult()).longValue();

    List<ClientDetail> resultList = query.getResultList();

    return new PageImpl<>(resultList, pageable, total);
  }

  private static String getFilterSql(final String firstName, final String surnameAtBirth,
      final LocalDate dateOfBirth, final String gender, final String clientReferenceNumber,
      final String homeOfficeReference, final String nationalInsuranceNumber){
    StringJoiner sj = new StringJoiner(" AND ");
    // First name (Fuzzy match, case-insensitive)
    if(stringNotEmpty(firstName)){
      sj.add("UPPER(FIRSTNAME) LIKE '%" + firstName.toUpperCase() + "%'");
    }
    // Surname (Fuzzy match, case-insensitive)
    if(stringNotEmpty(surnameAtBirth)){
      sj.add("UPPER(SURNAME_AT_BIRTH) LIKE '%" + surnameAtBirth.toUpperCase() + "%'");
    }
    // Date of birth (Exact match)
    if(!Objects.isNull(dateOfBirth)){
      sj.add("DATE_OF_BIRTH = TO_DATE('" + dateOfBirth + "', 'YYYY-MM-DD')");
    }
    // Gender (Exact match but case-insensitive)
    if(stringNotEmpty(gender)){
      sj.add("UPPER(GENDER) = '" + gender.toUpperCase() + "'");
    }
    // Client reference number (Fuzzy match, case-insensitive)
    if(stringNotEmpty(clientReferenceNumber)){
      sj.add("TO_CHAR(CLIENT_REFERENCE_NUMBER) LIKE '%"
          + clientReferenceNumber.toUpperCase() + "%'");
    }
    // Home office number (Fuzzy match, case-insensitive)
    if(stringNotEmpty(homeOfficeReference)){
      sj.add("UPPER(HOME_OFFICE_NUMBER) LIKE '%"
          + homeOfficeReference.toUpperCase() + "%'");
    }
    // National insurance number
    if(stringNotEmpty(nationalInsuranceNumber)){
      sj.add("UPPER(NI_NUMBER) LIKE '%"
          + nationalInsuranceNumber.toUpperCase() + "%'");
    }
    return sj.length() > 0 ? "WHERE " + sj : "";
  }


}
