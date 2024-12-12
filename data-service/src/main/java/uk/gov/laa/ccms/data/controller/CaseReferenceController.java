package uk.gov.laa.ccms.data.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.CaseReferenceApi;
import uk.gov.laa.ccms.data.model.CaseReferenceSummary;
import uk.gov.laa.ccms.data.service.NewCaseReferenceService;

/**
 * REST Controller responsible for allocating and returning the next case reference number.
 *
 * <p>The controller implements the {@link CaseReferenceApi} interface, which defines the API
 * contract for working with case references.</p>
 *
 * @author Jamie Briggs
 * @see NewCaseReferenceService
 */
@RestController
@AllArgsConstructor
public class CaseReferenceController implements CaseReferenceApi {

  private final NewCaseReferenceService newCaseReferenceService;

  @Override
  public ResponseEntity<CaseReferenceSummary> postCaseReference() {
    return new ResponseEntity<>((newCaseReferenceService.getNextAvailableCaseReference()),
        HttpStatus.CREATED);
  }
}
