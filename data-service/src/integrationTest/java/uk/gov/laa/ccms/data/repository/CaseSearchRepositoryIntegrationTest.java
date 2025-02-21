package uk.gov.laa.ccms.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.OracleIntegrationTestInterface;
import uk.gov.laa.ccms.data.entity.CaseSearch;
import uk.gov.laa.ccms.data.repository.specification.CaseSearchSpecification;

@SpringBootTest
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_CLASS, scripts = {
    "/sql/case_search_create_schema.sql"
})
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_CLASS, scripts = {
    "/sql/case_search_drop_schema.sql"
})
@DisplayName("Case Search Repository Integration Test")
class CaseSearchRepositoryIntegrationTest implements OracleIntegrationTestInterface {

  @Autowired
  private CaseSearchRepository caseSearchRepository;

  @Test
  @DisplayName("Should get all case search using provider ID")
  void shouldGetAllCaseSearchRowsUsingProviderId() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            null,
            null,
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3001");
    assertEquals(result.getContent().get(1).getLscCaseReference(), "3002");
  }

  @Test
  @DisplayName("Should contain correct pagination information")
  void shouldContainCorrectPaginationInformation() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            null,
            null,
            null,
            null),
        Pageable.ofSize(1).withPage(0));
    // Then
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3001");
    assertEquals(2, result.getTotalElements());
    assertEquals(2, result.getTotalPages());
    assertEquals(1, result.getNumberOfElements());
    assertEquals(0, result.getNumber());
  }

  @Test
  @DisplayName("Should find no cases when provider ID does not match")
  void shouldFindNoCaseSearchRowsWhenProviderIdDoesNotMatch() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(200L,
            "3001",
            null,
            null,
            null,
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(0, result.getTotalElements());
  }

  @Test
  @DisplayName("Should filter by case reference number")
  void shouldFilterByCaseReferenceNumber() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            "3001",
            null,
            null,
            null,
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3001");
  }

  @Test
  @DisplayName("Should not filter like case reference number")
  void shouldNotFilterLikeCaseReferenceNumber() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            "300",
            null,
            null,
            null,
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(0, result.getTotalElements());
  }

  @Test
  @DisplayName("Should filter by provider case reference number")
  void shouldFilterByProviderCaseReferenceNumber() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            "6001",
            null,
            null,
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3001");
    
  }

  @Test
  @DisplayName("Should filter like provider case reference number")
  void shouldFilterLikeProviderCaseReferenceNumber() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            "600",
            null,
            null,
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3001");
    assertEquals(result.getContent().get(1).getLscCaseReference(), "3002");
  }

  @Test
  @DisplayName("Should filter exactly status")
  void shouldFilterExactlyStatus() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            "ONE",
            null,
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3001");
  }

  @Test
  @DisplayName("Should filter exactly status two")
  void shouldFilterExactlyStatusTwo() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            "TWO",
            null,
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3002");
  }

  @Test
  @DisplayName("Should filter exactly status none")
  void shouldFilterExactlyStatusNone() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            "ON",
            null,
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(0, result.getTotalElements());
  }

  @Test
  @DisplayName("Should filter by client surname")
  void shouldFilterByClientSurname() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            null,
            "Last Two",
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3002");
  }

  @Test
  @DisplayName("Should filter like client surname")
  void shouldFilterLikeClientSurname() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            null,
            "Last",
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(2, result.getTotalElements());
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3001");
    assertEquals(result.getContent().get(1).getLscCaseReference(), "3002");
  }

  @Test
  @DisplayName("Should filter like client surname ignore case")
  void shouldFilterLikeClientSurnameIgnoreCase() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            null,
            "LaSt twO",
            null,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3002");
  }

  @Test
  @DisplayName("Should filter equal fee earner ID")
  void shouldFilterEqualFeeEarnerId() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            null,
            null,
            9001L,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3001");
    
  }


  @Test
  @DisplayName("Should filter equal fee earner ID return none")
  void shouldFilterEqualFeeEarnerIdReturnNone() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            null,
            null,
            900L,
            null),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(0, result.getTotalElements());
    
    
  }


  @Test
  @DisplayName("Should filter equal fee earner ID")
  void shouldFilterEqualOfficeId() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            null,
            null,
            null,
            8001L),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(result.getContent().getFirst().getLscCaseReference(), "3001");
    
  }


  @Test
  @DisplayName("Should filter equal fee earner ID return none")
  void shouldFilterEqualOfficeIdReturnNone() {
    // Given
    // When
    Page<CaseSearch> result = caseSearchRepository.findAll(CaseSearchSpecification.filterBy(100L,
            null,
            null,
            null,
            null,
            null,
            800L),
        Pageable.ofSize(10).withPage(0));
    // Then
    assertEquals(0, result.getTotalElements());
    
    
  }

}
