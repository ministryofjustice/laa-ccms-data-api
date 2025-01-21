package uk.gov.laa.ccms.data.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.ClientsApi;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.service.ClientService;

/**
 * Controller class responsible for handling client-related requests.
 *
 * <p>This controller serves as an interface to return requested client
 * information. It delegates the business logic to the {@link ClientService}.</p>
 *
 * <p>This class implements the {@link ClientsApi} interface and provides endpoints
 * for retrieving client based information for users.</p>
 *
 * @see ClientsApi
 * @see ClientService
 * @author Jamie Briggs
 */
@RestController
public class ClientsController implements ClientsApi {

  private final ClientService transactionService;

  public ClientsController(ClientService transactionService) {
    this.transactionService = transactionService;
  }

  /**
   * Retrieves the transaction status for a client based on the transaction request ID.
   *
   * @param transactionRequestId the transaction request ID (required)
   * @return a {@code ResponseEntity} containing the related transaction status.
   */
  @Override
  public ResponseEntity<TransactionStatus> getClientTransactionStatus(String transactionRequestId) {
    return transactionService.getTransactionStatus(transactionRequestId).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
