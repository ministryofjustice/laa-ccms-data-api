package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.CasesApi;
import uk.gov.laa.ccms.data.model.CaseDetails;

@RestController
@RequiredArgsConstructor
public class CaseSearchController implements CasesApi {

  @Override
  public ResponseEntity<CaseDetails> getCases(String caseReferenceNumber,
      String providerCaseReference, String caseStatus, String clientSurname, Integer feeEarnerId,
      Integer officeId, Pageable pageable) {
    return null;
  }
}
