package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.ProceedingsApi;
import uk.gov.laa.ccms.data.model.ProceedingDetail;
import uk.gov.laa.ccms.data.model.ProceedingDetails;
import uk.gov.laa.ccms.data.service.ProceedingService;

/**
 * Controller responsible for proceeding-related operations.
 *
 * <p>This controller serves as an interface to manage proceeding data and actions. It delegates
 * the business logic to the {@link ProceedingService}</p>
 *
 * <p>It implements the {@link ProceedingsApi} interface, which could be an API definition,
 * presumably from a Swagger or OpenAPI specification or some other contract definition.</p>
 *
 * @see ProceedingService
 */
@RestController
@RequiredArgsConstructor
public class ProceedingController implements ProceedingsApi {

  private final ProceedingService proceedingService;

  /**
   * GET Proceedings by category of law, matter type, whether they are enabled,
   * and whether they are amendment-only proceedings.
   *
   * @param categoryOfLaw - the category of law code
   * @param matterType - the matter type
   * @param enabled - whether the proceeding is enabled
   * @param amendmentOnly - whether the proceeding is amendment-only
   * @param pageable - the pagination settings
   * @return ResponseEntity with the results of the search
   */
  @Override
  public ResponseEntity<ProceedingDetails> getProceedings(
      final String categoryOfLaw,
      final String matterType,
      final Boolean amendmentOnly,
      final Boolean enabled,
      final Boolean lead,
      final String applicationType,
      final Boolean larScopeFlag,
      final Pageable pageable) {


    if (Boolean.TRUE.equals(lead)) {
      return ResponseEntity.ok(proceedingService.getLeadProceedings(
          categoryOfLaw,
          matterType,
          amendmentOnly,
          enabled,
          applicationType,
          larScopeFlag,
          pageable));
    } else {
      return ResponseEntity.ok(proceedingService.getProceedings(
          categoryOfLaw,
          matterType,
          amendmentOnly,
          enabled,
          pageable));
    }
  }


  /**
   * Retrieves a proceeding by its code.
   *
   * @param code the code for the Proceeding
   * @return ResponseEntity with the ProceedingDetail if found,
   *     or ResponseEntity.notFound() if not found
   */
  @Override
  public ResponseEntity<ProceedingDetail> getProceeding(String code) {
    return proceedingService.getProceeding(code)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

}
