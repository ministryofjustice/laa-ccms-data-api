package uk.gov.laa.ccms.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.laa.ccms.data.entity.CaseSearch;

@DataJpaTest
@ActiveProfiles("h2-test")
@DisplayName("Case Search Repository Integration Test")
public class CaseSearchRepositoryIntegerationTest {

  @Autowired
  private CaseSearchRepository caseSearchRepository;

  @PersistenceContext
  private EntityManager entityManager;

  private CaseSearch s1;
  private CaseSearch s2;

  @BeforeEach
  void setUp() {
    // Insert some test case search rows
    s1 = CaseSearch.builder()
        .casePartyId(1001L)
        .appOrCertSrId(2001L)
        .lscCaseReference("3001")
        .cisCaseReference("4001")
        .clientPartyId(5001L)
        .personFirstName("First")
        .personLastName("Last")
        .providerCaseReference("6001")
        .providerFirmPartyId(7001L)
        .providerOfficePartyId(8001L)
        .feeEarnerPartyId(9001L)
        .feeEarner("Fee One")
        .categoryOfLaw("Category One")
        .categoryOfLawDescription("Category One Description")
        .actualCaseStatus("Actual Status One")
        .displayCaseStatus("Display Status One")
        .build();
    s2 = CaseSearch.builder()
        .casePartyId(1002L)
        .appOrCertSrId(2002L)
        .lscCaseReference("3002")
        .cisCaseReference("4002")
        .clientPartyId(5002L)
        .personFirstName("First Two")
        .personLastName("Last Two")
        .providerCaseReference("6002")
        .providerFirmPartyId(7002L)
        .providerOfficePartyId(8002L)
        .feeEarnerPartyId(9002L)
        .feeEarner("Fee Two")
        .categoryOfLaw("Category Two")
        .categoryOfLawDescription("Category Two Description")
        .actualCaseStatus("Actual Status Two")
        .displayCaseStatus("Display Status Two")
        .build();
    // Persist both searchs
    entityManager.persist(s1);
    entityManager.persist(s2);
  }

  @Test
  @DisplayName("Should get all case search")
  void shouldGetAllCaseSearchRows(){
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(getSpecification(),
        Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertEquals(true, result.getContent().contains(s1));
    assertEquals(true, result.getContent().contains(s2));
  }


  Specification<CaseSearch> getSpecification(){
    return (root, query, cb) -> cb.and();
  }

  @AfterEach
  void afterEach(){
    entityManager.remove(s1);
    entityManager.remove(s2);
  }
}
