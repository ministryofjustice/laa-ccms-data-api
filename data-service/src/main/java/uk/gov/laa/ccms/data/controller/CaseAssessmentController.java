package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.CaseAssessmentsApi;
import uk.gov.laa.ccms.data.model.AssessmentType;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetails;
import uk.gov.laa.ccms.data.service.CaseAssessmentService;

@RestController
@RequiredArgsConstructor
public class CaseAssessmentController implements CaseAssessmentsApi {

  private final CaseAssessmentService caseAssessmentService;

  @Override
  public ResponseEntity<CaseAssessmentDetails> getCaseAssessment(String caseReferenceNumber,
      AssessmentType assessmentType) {
    CaseAssessmentDetails caseAssessmentDetails = caseAssessmentService
        .getCaseAssessmentDetails(caseReferenceNumber, assessmentType);
    return ResponseEntity.ok(caseAssessmentDetails);
  }
}
