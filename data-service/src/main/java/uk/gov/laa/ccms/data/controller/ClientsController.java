package uk.gov.laa.ccms.data.controller;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.ClientsApi;
import uk.gov.laa.ccms.data.model.ClientDetails;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.service.ClientService;
import uk.gov.laa.ccms.data.service.ClientServiceException;

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

  private final ClientService clientService;

  public ClientsController(ClientService clientService) {
    this.clientService = clientService;
  }

  /**
   * Retrieves the transaction status for a client based on the transaction request ID.
   *
   * @param transactionRequestId the transaction request ID (required)
   * @return a {@code ResponseEntity} containing the related transaction status.
   */
  @Override
  public ResponseEntity<TransactionStatus> getClientTransactionStatus(String transactionRequestId) {
    try {
      return clientService.getTransactionStatus(transactionRequestId).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    } catch (ClientServiceException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @Override
  public ResponseEntity<ClientDetails> getClients(String firstName, String surname,
      LocalDate dateOfBirth, String gender, String clientReferenceNumber, String homeOfficeReference,
      String nationalInsuranceNumber, Pageable pageable) {
    Optional<ClientDetails> clients = clientService.getClients(firstName, surname, dateOfBirth,
        gender,
        clientReferenceNumber, homeOfficeReference, nationalInsuranceNumber,
        pageable);
    return clients.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
