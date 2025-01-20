package uk.gov.laa.ccms.data.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.mapper.TransactionStatusMapper;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.repository.TransactionStatusRepository;

@Service
public class TransactionService {

  private final TransactionStatusMapper transactionStatusMapper;
  private final TransactionStatusRepository transactionStatusRepository;

  public TransactionService(TransactionStatusMapper transactionStatusMapper,
      TransactionStatusRepository transactionStatusRepository) {
    this.transactionStatusMapper = transactionStatusMapper;
    this.transactionStatusRepository = transactionStatusRepository;
  }

  public Optional<TransactionStatus> getTransactionStatus(String transactionId) {
    return transactionStatusRepository.findClientTransactionByTransactionId(transactionId).map(
        transactionStatusMapper::toTransactionStatus);
  }
}
