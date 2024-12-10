package uk.gov.laa.ccms.data.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.CaseReferenceApi;
import uk.gov.laa.ccms.data.model.CaseReferenceSummary;
import uk.gov.laa.ccms.data.repository.NewCaseReferenceRepository;

@RestController
@AllArgsConstructor
public class CaseReferenceController implements CaseReferenceApi {

  private final NewCaseReferenceRepository newCaseReferenceRepository;

  @Override
  public ResponseEntity<CaseReferenceSummary> postCaseReference() {
    // TODO: Need to use a proper mapper still, just testing if this repository works with view
    String nextCaseReference = newCaseReferenceRepository.getNextCaseReference();
    return new ResponseEntity<>(new CaseReferenceSummary().caseReferenceNumber(nextCaseReference), HttpStatus.CREATED);
  }
}
