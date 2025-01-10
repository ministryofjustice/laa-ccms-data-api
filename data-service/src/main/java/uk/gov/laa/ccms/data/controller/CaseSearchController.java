package uk.gov.laa.ccms.data.controller;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.CasesApi;
import uk.gov.laa.ccms.data.model.CaseDetails;
import uk.gov.laa.ccms.data.service.CaseSearchService;

@RestController
@RequiredArgsConstructor
public class CaseSearchController implements CasesApi {

  private final CaseSearchService caseSearchService;

  @Override
  public ResponseEntity<CaseDetails> getCases(String caseReferenceNumber,
      String providerCaseReference, String caseStatus, String clientSurname, Long feeEarnerId,
      Long officeId, Pageable pageable) {
    Optional<CaseDetails> cases = caseSearchService.getCases(caseReferenceNumber,
        providerCaseReference, caseStatus, clientSurname, feeEarnerId,
        officeId, pageable);
    return cases.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
