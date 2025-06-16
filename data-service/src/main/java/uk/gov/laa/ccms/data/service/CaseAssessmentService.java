package uk.gov.laa.ccms.data.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.model.AssessmentType;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetail;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetails;
import uk.gov.laa.ccms.data.repository.CaseAssessmentRepository;

@Service
@RequiredArgsConstructor
public class CaseAssessmentService {

  private final CaseAssessmentRepository caseAssessmentRepository;

  public Optional<CaseAssessmentDetails> getCaseAssessmentDetails(String caseReference,
      AssessmentType assessmentType) {

    List<CaseAssessmentDetail> assessmentDetails =
        caseAssessmentRepository.getCaseAssessmentDetails(caseReference, assessmentType);
    if (assessmentDetails.isEmpty()) {
      return Optional.empty();
    }
    CaseAssessmentDetails result = new CaseAssessmentDetails();
    result.setAssessmentDetails(assessmentDetails);
    result.setCaseReference(caseReference);
    return Optional.of(result);
  }
}
