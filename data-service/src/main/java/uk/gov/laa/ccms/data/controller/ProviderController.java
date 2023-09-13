package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.ProvidersApi;
import uk.gov.laa.ccms.data.model.ProviderDetail;
import uk.gov.laa.ccms.data.service.ProviderService;

/**
 * Controller responsible for provider-related operations.
 *
 * <p>This controller serves as an interface to manage provider data and actions. It delegates
 * the business logic to the {@link ProviderService}</p>
 *
 * <p>It implements the {@link ProvidersApi} interface, which could be an API definition,
 * presumably from a Swagger or OpenAPI specification or some other contract definition.</p>
 *
 * @see ProviderService
 */
@RestController
@RequiredArgsConstructor
public class ProviderController implements ProvidersApi {

  private final ProviderService providerService;

  /**
   * Retrieves a provider by provider ID.
   *
   * @param providerId the ID of the Provider
   * @return ResponseEntity with the ProviderDetails if found,
   *     or ResponseEntity.notFound() if not found
   */
  @Override
  public ResponseEntity<ProviderDetail> getProvider(Integer providerId) {
    return providerService.getProvider(providerId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
