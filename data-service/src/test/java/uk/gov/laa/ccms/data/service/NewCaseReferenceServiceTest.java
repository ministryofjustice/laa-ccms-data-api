package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.model.CaseReferenceSummary;
import uk.gov.laa.ccms.data.repository.NewCaseReferenceRepository;

@ExtendWith({MockitoExtension.class})
class NewCaseReferenceServiceTest {

  @Mock
  private NewCaseReferenceRepository newCaseReferenceRepository;

  @InjectMocks
  private NewCaseReferenceService newCaseReferenceService;

  @Test
  @DisplayName("Should return CaseReferenceSummary object")
  void shouldReturnCaseReferenceSummaryObject(){
    // Given
    when(newCaseReferenceRepository.getNextCaseReference()).thenReturn("123");
    // When
    CaseReferenceSummary nextAvailableCaseReference
        = newCaseReferenceService.getNextAvailableCaseReference();
    // Then
    assertEquals("123", nextAvailableCaseReference.getCaseReferenceNumber());
  }

}