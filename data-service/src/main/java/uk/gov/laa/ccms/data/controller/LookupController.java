package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.LookupApi;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupDetail;
import uk.gov.laa.ccms.data.service.LookupService;

/**
 * REST controller for managing lookup values.
 *
 * <p>This controller provides endpoints for retrieving various types of lookup values.
 * Lookup values are essentially the enumerated values stored in a system for fields that rarely
 * change.The lookup values provide more context and meaning to these fields, by providing the
 * actual human-readable text instead of the stored numerical or coded value.</p>
 *
 * <p>This controller supports operations for three types of lookup values: Common, Amendment
 * Type, and Case Status.</p>
 */
@RestController
@RequiredArgsConstructor
public class LookupController implements LookupApi {

  private final LookupService lookupService;

  /**
   * GET common lookup values by type and code.
   *
   * <p>This endpoint retrieves a paginated list of common lookup values based on the given type
   * and code.</p>
   *
   * @param type        the type of the common lookup values
   * @param code        the code of the common lookup values
   * @param description the description of the common lookup values
   * @param pageable    pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of common values in the body
   */
  @Override
  public ResponseEntity<CommonLookupDetail> getCommonLookupValues(
          String type, String code, String description, Pageable pageable) {
    return ResponseEntity.ok(lookupService.getCommonLookupValues(
        type, code, description, pageable));
  }

  /**
   * GET lookup values for countries.
   *
   * <p>This endpoint retrieves a paginated list of lookup values for countries</p>
   *
   * @param code     the code of the country lookup value
   * @param pageable pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of common values in the body
   */
  @Override
  public ResponseEntity<CommonLookupDetail> getCountriesLookupValues(
          String code, Pageable pageable) {
    return ResponseEntity.ok(lookupService.getCountryLookupValues(code, pageable));
  }

  /**
   * GET amendment type lookup values by application type.
   *
   * <p>This endpoint retrieves a paginated list of amendment type lookup values based on the
   * given application type.</p>
   *
   * @param applicationType the application type of the amendment type lookup values
   * @param pageable        pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of amendment type lookup values
   *         in the body
   */
  @Override
  public ResponseEntity<AmendmentTypeLookupDetail> getAmendmentTypeLookupValues(
          String applicationType, Pageable pageable) {
    return ResponseEntity.ok(lookupService.getAmendmentTypeLookupValues(applicationType, pageable));
  }

  /**
   * GET case status lookup values by code and copyAllowed flag.
   *
   * <p>This endpoint retrieves a paginated list of case status lookup values based on the given
   * code and copyAllowed flag.</p>
   *
   * @param code        the code of the case status lookup values
   * @param copyAllowed the value of the copyAllowed flag for the case status lookup values
   * @param pageable    pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of case status values in the body
   */
  @Override
  public ResponseEntity<CaseStatusLookupDetail> getCaseStatusLookupValues(
          String code, Boolean copyAllowed, Pageable pageable) {
    return ResponseEntity.ok(lookupService.getCaseStatusLookupValues(code, copyAllowed, pageable));
  }

  /**
   * GET outcome result lookup values by proceeding code and outcome result value.
   *
   * @param proceedingCode the proceeding code
   * @param outcomeResult  the outcome result
   * @param pageable    pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of outcome
   *     result values in the body
   */
  @Override
  public ResponseEntity<OutcomeResultLookupDetail> getOutcomeResultLookupValues(
      String proceedingCode, String outcomeResult, Pageable pageable) {
    return ResponseEntity.ok(lookupService.getOutcomeResultLookupValues(
        proceedingCode, outcomeResult, pageable));
  }

  /**
   * GET stage end lookup values by proceeding code and stage end value.
   *
   * @param proceedingCode the proceeding code
   * @param stageEnd  the stage end value
   * @param pageable    pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of stage end values in the body
   */
  @Override
  public ResponseEntity<StageEndLookupDetail> getStageEndLookupValues(
      String proceedingCode, String stageEnd, Pageable pageable) {
    return ResponseEntity.ok(lookupService.getStageEndLookupValues(
        proceedingCode, stageEnd, pageable));
  }
}