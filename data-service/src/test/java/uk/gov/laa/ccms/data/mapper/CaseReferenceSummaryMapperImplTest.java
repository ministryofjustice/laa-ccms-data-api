package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.model.CaseReferenceSummary;

@ExtendWith(MockitoExtension.class)
class CaseReferenceSummaryMapperImplTest {

  CaseReferenceSummaryMapper mapper = new CaseReferenceSummaryMapperImpl();

  @Test
  @DisplayName("Should map to CaseReference object")
  void shouldMapToCaseReferenceObject(){
    // Given
    String referenceNumber = "1234";
    // When
    CaseReferenceSummary caseReferenceSummary = mapper.map(referenceNumber);
    // Then
    assertEquals(referenceNumber, caseReferenceSummary.getCaseReferenceNumber());
  }
}