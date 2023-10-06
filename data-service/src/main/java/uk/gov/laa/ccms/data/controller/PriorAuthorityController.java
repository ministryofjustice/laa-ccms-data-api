package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.PriorAuthorityTypesApi;
import uk.gov.laa.ccms.data.model.PriorAuthorityTypeDetails;
import uk.gov.laa.ccms.data.service.PriorAuthorityService;

/**
 * Controller responsible for prior authority-related operations.
 *
 * <p>This controller serves as an interface to manage Prior Authority data and actions.
 * It delegates the business logic to the {@link PriorAuthorityService}</p>
 *
 * <p>It implements the {@link PriorAuthorityTypesApi} interface, which could be an API definition,
 * presumably from a Swagger or OpenAPI specification or some other contract definition.</p>
 *
 * @see PriorAuthorityService
 */
@RestController
@RequiredArgsConstructor
public class PriorAuthorityController implements PriorAuthorityTypesApi {

  private final PriorAuthorityService priorAuthorityService;

  /**
   * Retrieves a provider by provider ID.
   *
   * @param code the code of the PriorAuthorityType
   * @param valueRequired the value required flag
   * @return ResponseEntity with the PriorAuthorityTypeDetails if found,
   *     or ResponseEntity.notFound() if not found
   */
  @Override
  public ResponseEntity<PriorAuthorityTypeDetails> getPriorAuthorityTypes(String code,
      Boolean valueRequired, Pageable pageable) {
    return ResponseEntity.ok(priorAuthorityService.getPriorAuthorityTypes(
        code, valueRequired, pageable));
  }
}
