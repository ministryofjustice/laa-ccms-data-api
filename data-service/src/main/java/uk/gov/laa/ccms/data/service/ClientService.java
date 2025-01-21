package uk.gov.laa.ccms.data.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.mapper.TransactionStatusMapper;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.repository.TransactionStatusRepository;

/**
 * Service class responsible for handling client-related operations.
 *
 * <p>This class provides methods to retrieve transaction statuses for client-related transactions.
 * It utilizes a repository for database access and a mapper for converting database entities
 * to objects used in the application.</p>
 *
 * @see TransactionStatusRepository
 * @see TransactionStatusMapper
 * @author Jamie Briggs
 */
@Service
public class ClientService {

  private final TransactionStatusMapper transactionStatusMapper;
  private final TransactionStatusRepository transactionStatusRepository;

  public ClientService(TransactionStatusMapper transactionStatusMapper,
      TransactionStatusRepository transactionStatusRepository) {
    this.transactionStatusMapper = transactionStatusMapper;
    this.transactionStatusRepository = transactionStatusRepository;
  }

  /**
   * Retrieves the transaction status for a given transaction ID. If the transaction
   *     is not found, an empty {@code Optional} is returned.
   *
   * @param transactionId the unique identifier of the transaction whose status
   *     is to be retrieved
   * @return an {@code Optional} containing the {@link TransactionStatus} if found,
   *     or an empty {@code Optional} if not found
   */
  public Optional<TransactionStatus> getTransactionStatus(String transactionId) {
    return transactionStatusRepository.findClientTransactionByTransactionId(transactionId).map(
        transactionStatusMapper::toTransactionStatus);
  }
}
