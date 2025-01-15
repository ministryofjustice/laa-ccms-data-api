package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.laa.ccms.data.entity.CaseSearch;
import uk.gov.laa.ccms.data.model.BaseClient;
import uk.gov.laa.ccms.data.model.CaseDetails;
import uk.gov.laa.ccms.data.model.CaseSummary;

@DisplayName( "Case search mapper implementation test")
class CaseSearchMapperImplTest {

  CaseSearchMapper mapper = new CaseSearchMapperImpl();

  @Test
  @DisplayName("Should map empty case search")
  void shouldMapEmptyCaseSearch(){
    // Given
    CaseSearch search = new CaseSearch();
    // When
    CaseSummary result = mapper.toCaseSummary(search);
    // Then
    assertNotNull(result);
  }

  @Test
  @DisplayName("Should map case search details")
  void shouldMapCaseSearchDetails(){
    // Given
    CaseSearch search = getCaseOne();
    // When
    CaseSummary result = mapper.toCaseSummary(search);
    // Then
    assertEquals("3", result.getCaseReferenceNumber());
    assertEquals("provCaseRef", result.getProviderCaseReferenceNumber());
    assertEquals("Fee Earner Name", result.getFeeEarnerName());
    assertEquals("Category Description", result.getCategoryOfLaw());
    assertEquals("Display Status", result.getCaseStatusDisplay());
    BaseClient resultClient = result.getClient();
    assertEquals("First", resultClient.getFirstName());
    assertEquals("5", resultClient.getClientReferenceNumber());
    assertEquals("Last", resultClient.getSurname());
  }

  @Test
  @DisplayName("Should map case search pageable object")
  void shouldMapCaseSearchPageableObject(){
    // Given
    CaseSearch search = getCaseOne();
    CaseSearch searchTwo = getCaseTwo();
    Page<CaseSearch> pageable = new PageImpl<>(java.util.List.of(search, searchTwo));
    // When
    CaseDetails details = mapper.toCaseDetails(pageable);
    CaseSummary expectedOne = mapper.toCaseSummary(search);
    CaseSummary expectedTwo = mapper.toCaseSummary(searchTwo);
    // Then
    assertEquals(2, details.getTotalElements());
    assertEquals(expectedOne, details.getContent().get(0));
    assertEquals(expectedTwo, details.getContent().get(1));
  }

  private static CaseSearch getCaseOne() {
    return CaseSearch.builder()
        .casePartyId(1L)
        .appOrCertSrId(2L)
        .lscCaseReference("3")
        .cisCaseReference("4")
        .clientPartyId(5L)
        .personFirstName("First")
        .personLastName("Last")
        .providerCaseReference("provCaseRef")
        .providerFirmPartyId(7L)
        .feeEarnerPartyId(8L)
        .feeEarner("Fee Earner Name")
        .categoryOfLaw("CAT")
        .categoryOfLawDescription("Category Description")
        .actualCaseStatus("Actual Status")
        .displayCaseStatus("Display Status")
        .build();
  }

  private static CaseSearch getCaseTwo() {
    return CaseSearch.builder()
        .casePartyId(10L)
        .appOrCertSrId(20L)
        .lscCaseReference("30")
        .cisCaseReference("40")
        .clientPartyId(50L)
        .personFirstName("First Two")
        .personLastName("Last Two")
        .providerCaseReference("provCaseRef Two")
        .providerFirmPartyId(70L)
        .feeEarnerPartyId(80L)
        .feeEarner("Fee Earner Name Two")
        .categoryOfLaw("CAT2")
        .categoryOfLawDescription("Category Description Two")
        .actualCaseStatus("Actual Status Two")
        .displayCaseStatus("Display Status Two")
        .build();
  }

}
