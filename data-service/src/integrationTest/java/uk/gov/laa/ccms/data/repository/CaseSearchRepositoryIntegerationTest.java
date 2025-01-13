package uk.gov.laa.ccms.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import uk.gov.laa.ccms.data.repository.specification.CaseSearchSpecification;

@DataJpaTest
@ActiveProfiles("h2-test")
@DisplayName("Case Search Repository Integration Test")
class CaseSearchRepositoryIntegerationTest {

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
        .providerFirmPartyId(100L)
        .casePartyId(1001L)
        .appOrCertSrId(2001L)
        .lscCaseReference("3001")
        .cisCaseReference("4001")
        .clientPartyId(5001L)
        .personFirstName("First")
        .personLastName("Last")
        .providerCaseReference("6001")
        .providerOfficePartyId(8001L)
        .feeEarnerPartyId(9001L)
        .feeEarner("Fee One")
        .categoryOfLaw("Category One")
        .categoryOfLawDescription("Category One Description")
        .actualCaseStatus("ONE")
        .displayCaseStatus("Display Status One")
        .build();
    s2 = CaseSearch.builder()
        .providerFirmPartyId(100L)
        .casePartyId(1002L)
        .appOrCertSrId(2002L)
        .lscCaseReference("3002")
        .cisCaseReference("4002")
        .clientPartyId(5002L)
        .personFirstName("First Two")
        .personLastName("Last Two")
        .providerCaseReference("6002")
        .providerOfficePartyId(8002L)
        .feeEarnerPartyId(9002L)
        .feeEarner("Fee Two")
        .categoryOfLaw("Category Two")
        .categoryOfLawDescription("Category Two Description")
        .actualCaseStatus("TWO")
        .displayCaseStatus("Display Status Two")
        .build();
    // Persist both searchs
    entityManager.persist(s1);
    entityManager.persist(s2);
  }

  @Test
  @DisplayName("Should get all case search using provider ID")
  void shouldGetAllCaseSearchRowsUsingProviderId(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        null,
        null,
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(s1));
    assertTrue(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should find no cases when provider ID does not match")
  void shouldFindNoCaseSearchRowsWhenProviderIdDoesNotMatch(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(200L,
        "3001",
        null,
        null,
        null,
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(0, result.getTotalElements());
    assertFalse(result.getContent().contains(s1));
    assertFalse(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter by case reference number")
  void shouldFilterByCaseReferenceNumber(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        "3001",
        null,
        null,
        null,
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(s1));
    assertFalse(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter like case reference number")
  void shouldFilterLikeCaseReferenceNumber(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        "300",
        null,
        null,
        null,
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(s1));
    assertTrue(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter by provider case reference number")
  void shouldFilterByProviderCaseReferenceNumber(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null ,
        "4001",
        null,
        null,
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(s1));
    assertFalse(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter like provider case reference number")
  void shouldFilterLikeProviderCaseReferenceNumber(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        "400",
        null,
        null,
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(s1));
    assertTrue(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter exactly status")
  void shouldFilterExactlyStatus(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        "ONE",
        null,
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(s1));
    assertFalse(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter exactly status two")
  void shouldFilterExactlyStatusTwo(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        "TWO",
        null,
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertFalse(result.getContent().contains(s1));
    assertTrue(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter exactly status none")
  void shouldFilterExactlyStatusNone(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        "ON",
        null,
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(0, result.getTotalElements());
    assertFalse(result.getContent().contains(s1));
    assertFalse(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter by client surname")
  void shouldFilterByClientSurname(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        null,
        "Last Two",
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertFalse(result.getContent().contains(s1));
    assertTrue(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter like client surname")
  void shouldFilterLikeClientSurname(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        null,
        "Last",
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(2, result.getTotalElements());
    assertTrue(result.getContent().contains(s1));
    assertTrue(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter like client surname ignore case")
  void shouldFilterLikeClientSurnameIgnoreCase(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        null,
        "LaSt twO",
        null,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertFalse(result.getContent().contains(s1));
    assertTrue(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter equal fee earner ID")
  void shouldFilterEqualFeeEarnerId(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        null,
        null,
        9001L,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(s1));
    assertFalse(result.getContent().contains(s2));
  }


  @Test
  @DisplayName("Should filter equal fee earner ID return none")
  void shouldFilterEqualFeeEarnerIdReturnNone(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        null,
        null,
        900L,
        null);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(0, result.getTotalElements());
    assertFalse(result.getContent().contains(s1));
    assertFalse(result.getContent().contains(s2));
  }

  @Test
  @DisplayName("Should filter equal fee earner ID")
  void shouldFilterEqualOfficeId(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        null,
        null,
        null,
        8001L);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(1, result.getTotalElements());
    assertTrue(result.getContent().contains(s1));
    assertFalse(result.getContent().contains(s2));
  }


  @Test
  @DisplayName("Should filter equal fee earner ID return none")
  void shouldFilterEqualOfficeIdReturnNone(){
    // Given
    Specification<CaseSearch> spec = CaseSearchSpecification.withFilters(100L,
        null,
        null,
        null,
        null,
        null,
        800L);
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(spec,
        Pageable.unpaged());
    // Then
    assertEquals(0, result.getTotalElements());
    assertFalse(result.getContent().contains(s1));
    assertFalse(result.getContent().contains(s2));
  }

  @AfterEach
  void afterEach(){
    entityManager.remove(s1);
    entityManager.remove(s2);
  }
}
