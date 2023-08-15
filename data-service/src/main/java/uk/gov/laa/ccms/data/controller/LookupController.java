package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.LookupApi;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.mapper.LookupMapper;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
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
  private final LookupMapper lookupMapper;

  /**
   * GET common lookup values by type and code.
   *
   * <p>This endpoint retrieves a paginated list of common lookup values based on the given type
   * and code.</p>
   *
   * @param type     the type of the common lookup values
   * @param code     the code of the common lookup values
   * @param pageable pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of common values in the body
   */
  @Override
  public ResponseEntity<CommonLookupDetail> getCommonLookupValues(
          String type, String code, Pageable pageable) {
    CommonLookupValue example = new CommonLookupValue();
    example.setType(type);
    example.setCode(code);

    Page<CommonLookupValue> page = lookupService.getCommonLookupValues(
            Example.of(example), pageable);
    CommonLookupDetail response = lookupMapper.toCommonLookupDetail(page);

    return ResponseEntity.ok(response);
  }

  /**
   * GET amendment type lookup values by application type.
   *
   * <p>This endpoint retrieves a paginated list of amendment type lookup values based on the given
   * application type.</p>
   *
   * @param applicationType     the application type of the amendment type lookup values
   * @param pageable            pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of amendment type lookup values
   *         in the body
   */
  @Override
  public ResponseEntity<AmendmentTypeLookupDetail> getAmendmentTypeLookupValues(
          String applicationType, Pageable pageable) {
    AmendmentTypeLookupValue example = new AmendmentTypeLookupValue();
    example.setApplicationTypeCode(applicationType);

    Page<AmendmentTypeLookupValue> page = lookupService.getAmendmentTypeLookupValues(
            Example.of(example), pageable);
    AmendmentTypeLookupDetail response = lookupMapper.toAmendmentTypeLookupDetail(page);

    return ResponseEntity.ok(response);
  }

  /**
   * GET case status lookup values by code and copyAllowed flag.
   *
   * <p>This endpoint retrieves a paginated list of case status lookup values based on the given
   * code and copyAllowed flag.</p>
   *
   * @param code          the code of the case status lookup values
   * @param copyAllowed   the value of the copyAllowed flag for the case status lookup values
   * @param pageable      pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of case status values in the body
   */
  @Override
  public ResponseEntity<CaseStatusLookupDetail> getCaseStatusLookupValues(
          String code, Boolean copyAllowed, Pageable pageable) {
    CaseStatusLookupValue example = new CaseStatusLookupValue();
    example.setCode(code);
    example.setCopyAllowed(copyAllowed);

    Page<CaseStatusLookupValue> page = lookupService.getCaseStatusLookupValues(
            Example.of(example), pageable);
    CaseStatusLookupDetail response = lookupMapper.toCaseStatusLookupDetail(page);

    return ResponseEntity.ok(response);
  }
}