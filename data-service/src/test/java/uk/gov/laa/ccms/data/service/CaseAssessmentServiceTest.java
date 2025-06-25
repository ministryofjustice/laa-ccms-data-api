package uk.gov.laa.ccms.data.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.model.AssessmentType;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetail;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetails;
import uk.gov.laa.ccms.data.repository.CaseAssessmentRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Case assessment service test")
class CaseAssessmentServiceTest {

  @Mock
  private CaseAssessmentRepository caseAssessmentRepository;

  private CaseAssessmentService caseAssessmentService;

  @BeforeEach
  void beforeEach() {
    caseAssessmentService = new CaseAssessmentService(caseAssessmentRepository);
  }

  @Test
  @DisplayName("Should return assessment details object")
  void shouldReturnAssessmentDetailsObject() {
    // Given
    CaseAssessmentDetail caseAssessmentDetail = new CaseAssessmentDetail().entityName("Question one")
        .attributeValue("Answer");
    when(caseAssessmentRepository.getCaseAssessmentDetails("123", AssessmentType.MEANS))
        .thenReturn(List.of(
            caseAssessmentDetail));
    // When
    Optional<CaseAssessmentDetails> result
        = caseAssessmentService.getCaseAssessmentDetails("123", AssessmentType.MEANS);
    // Then
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get().getCaseReference()).isEqualTo("123");
    assertThat(result.get().getAssessmentDetails().get(0)).isEqualTo(caseAssessmentDetail);
  }

  @Test
  @DisplayName("Should return empty optional when no case assessment details")
  void shouldReturnEmptyOptionalWhenNoCaseAssessmentDetails() {
    // Given
    when(caseAssessmentRepository.getCaseAssessmentDetails("123", AssessmentType.MEANS))
        .thenReturn(Collections.emptyList());
    // When
    Optional<CaseAssessmentDetails> result
        = caseAssessmentService.getCaseAssessmentDetails("123", AssessmentType.MEANS);
    // Then
    assertThat(result.isPresent()).isFalse();
  }
}