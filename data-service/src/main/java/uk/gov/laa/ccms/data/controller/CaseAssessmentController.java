package uk.gov.laa.ccms.data.controller;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.CaseAssessmentsApi;
import uk.gov.laa.ccms.data.model.AssessmentType;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetails;
import uk.gov.laa.ccms.data.service.CaseAssessmentService;

/**
 * Controller class responsible for handling case assessement detail operations.
 *
 * <p>This controller serves as an interface to return requested case assessement detail
 * information. It delegates the business logic to {@link CaseAssessmentService}.</p>
 *
 * <p>This class implements the {@link CaseAssessmentsApi} interface and provides endpoints
 * for retrieving case assessment information.</p>
 *
 * @see CaseAssessmentsApi
 * @see CaseAssessmentService
 * @author Jamie Briggs
 */
@RestController
@RequiredArgsConstructor
public class CaseAssessmentController implements CaseAssessmentsApi {

  private final CaseAssessmentService caseAssessmentService;

  /**
   * Retrieves the details of a case assessment based on the provided case reference number
   * and assessment type.
   *
   * @param caseReferenceNumber the unique reference number for the case
   * @param assessmentType the type of assessment (e.g., MEANS, MERITS) to retrieve details for
   * @return a {@link ResponseEntity} containing the {@link CaseAssessmentDetails} if found,
   *         or a 404 Not Found response if no matching case assessment is available
   */
  @Override
  public ResponseEntity<CaseAssessmentDetails> getCaseAssessment(String caseReferenceNumber,
      AssessmentType assessmentType) {
    Optional<CaseAssessmentDetails> caseAssessmentDetails = caseAssessmentService
        .getCaseAssessmentDetails(caseReferenceNumber, assessmentType);
    return caseAssessmentDetails.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
