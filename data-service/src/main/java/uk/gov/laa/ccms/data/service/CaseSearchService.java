package uk.gov.laa.ccms.data.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.CaseSearch;
import uk.gov.laa.ccms.data.mapper.CaseSearchMapper;
import uk.gov.laa.ccms.data.model.CaseDetails;
import uk.gov.laa.ccms.data.repository.CaseSearchRepository;
import uk.gov.laa.ccms.data.repository.specification.CaseSearchSpecification;
import uk.gov.laa.ccms.data.repository.spring.JPACaseSearchRepository;

/**
 * Service for performing search operations on case entities.
 *
 * @author Jamie Briggs
 */
@Service
@RequiredArgsConstructor
public class CaseSearchService {

  private final JPACaseSearchRepository caseSearchRepository;
  private final CaseSearchRepository oldcaseSearchRepository;
  private final CaseSearchMapper caseSearchMapper;


  /**
   * Retrieves case details based on the provided search criteria and pagination information.
   *
   * @param providerFirmPartyId the provider firm party ID to search for
   * @param caseReferenceNumber the unique reference number of the case to search for
   * @param providerCaseReference the case reference provided by the external provider
   * @param caseStatus the status of the case (e.g., active, closed)
   * @param clientSurname the surname of the client related to the case
   * @param feeEarnerId the ID of the fee earner associated with the case
   * @param officeId the ID of the office handling the case
   * @param pageable the pagination information for retrieving a paged list of cases
   * @return an {@link Optional} containing the matching {@link CaseDetails} or
   *     empty if no results are found
   */
  public Optional<CaseDetails> getCases(long providerFirmPartyId, String caseReferenceNumber,
      String providerCaseReference, String caseStatus, String clientSurname, Long feeEarnerId,
      Long officeId, Pageable pageable) {
    Page<CaseSearch> cases = caseSearchRepository.findAll(
        CaseSearchSpecification.filterBy(providerFirmPartyId, caseReferenceNumber,
            providerCaseReference, caseStatus, clientSurname, feeEarnerId, officeId),
        pageable);
    CaseDetails caseDetails = caseSearchMapper.toCaseDetails(cases);
    return Optional.ofNullable(caseDetails);
  }

}
