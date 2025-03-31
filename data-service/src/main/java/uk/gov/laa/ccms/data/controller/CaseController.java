package uk.gov.laa.ccms.data.controller;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.CasesApi;
import uk.gov.laa.ccms.data.model.CaseDetail;
import uk.gov.laa.ccms.data.model.CaseDetails;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.service.CaseSearchService;
import uk.gov.laa.ccms.data.service.CaseService;
import uk.gov.laa.ccms.data.service.ClientServiceException;

/**
 * Controller class responsible for handling case related operations.
 *
 * <p>This controller serves as an interface to return requested case information. It
 * delegates the business logic to the {@link CaseSearchService} and {@link CaseService}.</p>
 *
 * <p>This class implemented the {@CasesApi} interface and provides endpoints for retrieving
 * case information.</p>
 *
 * @see CaseDetails
 * @see CaseService
 * @see CaseSearchService
 * @author Jamie Briggs
 */
@RestController
@RequiredArgsConstructor
public class CaseController implements CasesApi {

  private final CaseService caseService;
  private final CaseSearchService caseSearchService;

  /**
   * Retrieves a paginated list of case details based on the provided search criteria.
   *
   * @param providerFirmPartyId the unique identifier of the provider firm party.
   * @param caseReferenceNumber the unique identifier of the case.
   * @param providerCaseReference the reference provided by the external entity for the case.
   * @param caseStatus the status of the case (e.g., pending, closed).
   * @param clientSurname the surname of the client involved in the case.
   * @param feeEarnerId the unique identifier for the corresponding fee earner.
   * @param officeId the unique identifier of the office associated with the case.
   * @param pageable the pagination details including page number and page size.
   * @return a {@code ResponseEntity} containing the {@code CaseDetails} if found,
   *         or a {@code ResponseEntity} with a not found status if no cases match the criteria.
   */
  @Override
  public ResponseEntity<CaseDetails> getCases(Long providerFirmPartyId, String caseReferenceNumber,
      String providerCaseReference, String caseStatus, String clientSurname, Long feeEarnerId,
      Long officeId, Pageable pageable) {
    Optional<CaseDetails> cases =
        caseSearchService.getCases(providerFirmPartyId, caseReferenceNumber,
          providerCaseReference, caseStatus, clientSurname, feeEarnerId,
          officeId, pageable);
    return cases.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  /**
   * Retrieves the details of a specific case based on the provided criteria.
   *
   * @param caseReferenceNumber the unique identifier of the case.
   * @param providerId the unique identifier of the provider.
   * @param userName the username of the user accessing this case. Dictates what available
   *                 functions are returned alongside the case.
   * @return a {@code ResponseEntity} containing the {@code CaseDetail} if found,
   *         or a {@code ResponseEntity} with a not found status if no case matches the criteria.
   */
  @SneakyThrows
  @Override
  public ResponseEntity<CaseDetail> getCase(String caseReferenceNumber, Long providerId,
      String userName) {
    Optional<CaseDetail> caseDetail = caseService.getCaseDetails(caseReferenceNumber, providerId,
        userName);
    return caseDetail.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  /**
   * Retrieves the transaction status for a specific case based on the given transaction
   *     request identifier.
   *
   * @param transactionRequestId the unique identifier of the transaction request.
   * @return a {@code ResponseEntity} containing the {@code TransactionStatus} if found,
   *         a {@code ResponseEntity} with a not found status if the transaction is not found,
   *         or a {@code ResponseEntity} with an internal server error status if an exception
   *         occurs.
   */
  @Override
  public ResponseEntity<TransactionStatus> getCaseTransactionStatus(String transactionRequestId) {
    try {
      return caseService.getTransactionStatus(transactionRequestId).map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
    } catch (ClientServiceException e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
