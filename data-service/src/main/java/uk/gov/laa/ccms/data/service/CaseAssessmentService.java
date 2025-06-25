package uk.gov.laa.ccms.data.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.model.AssessmentType;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetail;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetails;
import uk.gov.laa.ccms.data.repository.CaseAssessmentRepository;

/**
 * Service class responsible for retrieving case assessment details.
 * This class interacts with the {@link CaseAssessmentRepository} to fetch assessment
 * data related to a specific case reference and assessment type.
 *
 * @see CaseAssessmentRepository
 * @author Jamie Briggs
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CaseAssessmentService {

  private final CaseAssessmentRepository caseAssessmentRepository;

  /**
   * Retrieves the case assessment details for a specific case reference and assessment type.
   * The method interacts with the {@link CaseAssessmentRepository} to fetch the relevant details
   * and constructs a {@link CaseAssessmentDetails} object if data exists. If no data exists,
   * an empty {@link Optional} is returned.
   *
   * @param caseReference The unique reference for the case to retrieve assessment details.
   * @param assessmentType The type of assessment to fetch details for (e.g., MEANS, MERITS).
   * @return An {@link Optional} containing {@link CaseAssessmentDetails} if details exist;
   *         otherwise, an empty {@link Optional}.
   */
  public Optional<CaseAssessmentDetails> getCaseAssessmentDetails(String caseReference,
      AssessmentType assessmentType) {

    List<CaseAssessmentDetail> details =
        caseAssessmentRepository.getCaseAssessmentDetails(caseReference, assessmentType);

    if (details.isEmpty()) {
      return Optional.empty();
    }
    CaseAssessmentDetails result = new CaseAssessmentDetails();
    result.setAssessmentDetails(details);
    result.setCaseReference(caseReference);
    return Optional.of(result);
  }
}
