package uk.gov.laa.ccms.data.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.model.CaseReferenceSummary;
import uk.gov.laa.ccms.data.repository.NewCaseReferenceRepository;

/**
 * Service class for generating and retrieving new case references.
 *
 * <p>This service provides a method which allocates and returns the next
 *    available case reference, mapped using the {@link CaseReferenceSummary}</p>
 *
 * @see Service
 * @see CaseReferenceSummary
 * @see NewCaseReferenceRepository
 *
 * @author Jamie Briggs
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NewCaseReferenceService {

  private final NewCaseReferenceRepository newCaseReferenceRepository;

  /**
   * Retrieves the next available case reference to a {@link CaseReferenceSummary} object.
   *
   * @return the next available case reference wrapped in a {@link CaseReferenceSummary} object
   */
  public CaseReferenceSummary getNextAvailableCaseReference() {
    return new CaseReferenceSummary().caseReferenceNumber(newCaseReferenceRepository.getNextCaseReference());
  }
}
