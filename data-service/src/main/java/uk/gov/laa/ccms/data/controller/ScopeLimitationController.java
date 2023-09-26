package uk.gov.laa.ccms.data.controller;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.ScopelimitationsApi;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetail;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetails;
import uk.gov.laa.ccms.data.service.ScopeLimitationService;

/**
 * REST controller for managing Scope Limitation values.
 *
 * <p>This controller provides endpoints for retrieving scope limitation values.</p>
 */
@RestController
@RequiredArgsConstructor
public class ScopeLimitationController implements ScopelimitationsApi {

  private final ScopeLimitationService scopeLimitationService;

  /**
   /**
   * GET a page of scope limitations that match the provided search criteria.
   *
   * @param scopeLimitations        (optional) scope limitations value
   * @param categoryOfLaw           (optional) the category of law
   * @param matterType              (optional) the matter type
   * @param proceedingCode          (optional) the proceeding code
   * @param levelOfService          (optional) the level of service
   * @param defaultWording          (optional) the default wording
   * @param stage                   (optional) the stage
   * @param costLimitation          (optional) the cost limitation
   * @param emergencyCostLimitation (optional) the emergency cost limitation
   * @param nonStandardWording      (optional) the non standard wording value
   * @param emergencyScopeDefault   (optional) the emergency scope default value
   * @param emergency               (optional) the emergency value
   * @param defaultCode             (optional) the default code value
   * @param scopeDefault            (optional) the scope default value
   * @param pageable pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of scope limitations in the body
   */
  @Override
  public ResponseEntity<ScopeLimitationDetails> getScopeLimitations(String scopeLimitations,
      String categoryOfLaw, String matterType, String proceedingCode, String levelOfService,
      String defaultWording, Integer stage, BigDecimal costLimitation,
      BigDecimal emergencyCostLimitation, Boolean nonStandardWording, Boolean emergencyScopeDefault,
      Boolean emergency, Boolean defaultCode, Boolean scopeDefault, Pageable pageable) {
    ScopeLimitationDetail scopeLimitationDetail = new ScopeLimitationDetail()
        .scopeLimitations(scopeLimitations)
        .categoryOfLaw(categoryOfLaw)
        .matterType(matterType)
        .proceedingCode(proceedingCode)
        .levelOfService(levelOfService)
        .defaultWording(defaultWording)
        .stage(stage)
        .costLimitation(costLimitation)
        .emergencyCostLimitation(emergencyCostLimitation)
        .nonStandardWordingRequired(nonStandardWording)
        .emergencyScopeDefault(emergencyScopeDefault)
        .emergency(emergency)
        .defaultCode(defaultCode)
        .scopeDefault(scopeDefault);

    return ResponseEntity.ok(scopeLimitationService.getScopeLimitations(
        scopeLimitationDetail, pageable));
  }
}