package uk.gov.laa.ccms.data.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.ClientsApi;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.service.TransactionService;

@RestController
public class ClientsController implements ClientsApi {

  private final TransactionService transactionService;

  public ClientsController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public ResponseEntity<TransactionStatus> getClientTransactionStatus(String transactionRequestId) {
    return transactionService.getTransactionStatus(transactionRequestId).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
