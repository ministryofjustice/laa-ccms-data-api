package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uk.gov.laa.ccms.data.entity.CaseSearch;
import uk.gov.laa.ccms.data.mapper.CaseSearchMapperImpl;
import uk.gov.laa.ccms.data.model.CaseDetails;
import uk.gov.laa.ccms.data.repository.CaseSearchRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Case search service test")
class CaseSearchServiceTest {

  @Mock
  private CaseSearchRepository caseSearchRepository;

  private CaseSearchService caseSearchService;

  @BeforeEach
  void setup(){
    caseSearchService = new CaseSearchService(caseSearchRepository, new CaseSearchMapperImpl());
  }

  @Test
  @DisplayName("Should return CaseSummary object")
  void shouldReturnCaseSummaryObject(){
    // Given
    new CaseSearch();
    when(caseSearchRepository.findAll(anyLong(),
        any(),
        any(),
        any(),
        any(),
        any(),
        any(),
        any(Pageable.class))).thenReturn(new PageImpl<>(List.of(
        CaseSearch.builder().lscCaseReference("123").build())));
    // When
    Optional<CaseDetails> cases = caseSearchService.getCases(1L, "123",
        "345",
        "ACT",
        "Surname",
        1L,
        2L,
        PageRequest.of(1, 10));
    // Then
    assertTrue(cases.isPresent());
    assertEquals("123", cases.get().getContent().getFirst().getCaseReferenceNumber());
  }

}
