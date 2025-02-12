package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.ClientDetail;

@Repository
@RequiredArgsConstructor
public class ClientDetailRepository {

  private final EntityManager entityManager;

  public Page<ClientDetail> findAll(final String firstName, final String surname,
      final LocalDate dateOfBirth, final String gender, final String caseReferenceNumber,
      final String homeOfficeReference, final String nationalInsuranceNumber,
      final Pageable pageable){

    final String searchCaseQuery =
        """
            SELECT * FROM XXCCMS.XXCCMS_GET_CLIENT_DETAILS_V
            OFFSET :offset ROWS FETCH NEXT :size ROWS ONLY
            """;

    Query query = entityManager.createNativeQuery(searchCaseQuery, ClientDetail.class);
    query.setHint("org.hibernate.readOnly", true);
    query.setParameter("offset", pageable.getOffset());
    query.setParameter("size", pageable.getPageSize());

    final String countClientDetails =
        """
        SELECT COUNT(*) FROM XXCCMS.XXCCMS_GET_CLIENT_DETAILS_V
        """;

    Query countQuery = entityManager.createNativeQuery(countClientDetails);

    countQuery.setHint("org.hibernate.readOnly", true);
    long total = ((Number) countQuery.getSingleResult()).longValue();

    List<ClientDetail> resultList = query.getResultList();

    return new PageImpl<>(resultList, pageable, total);
  }
}
