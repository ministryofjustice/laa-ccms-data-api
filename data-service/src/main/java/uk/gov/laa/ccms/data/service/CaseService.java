package uk.gov.laa.ccms.data.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.mapper.TransactionStatusMapper;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.repository.TransactionStatusRepository;

@Service
@RequiredArgsConstructor
public class CaseService {

  private final TransactionStatusRepository transactionStatusRepository;
  private final TransactionStatusMapper transactionStatusMapper;


  public Optional<TransactionStatus> getTransactionStatus(String transactionId) {
    List<uk.gov.laa.ccms.data.entity.TransactionStatus> userFuncStatus =
        transactionStatusRepository.findAllUserFunctionTransactionsByTransactionId(transactionId);
    if (userFuncStatus.stream().anyMatch(x -> x.getStatus()
        .equalsIgnoreCase("ERROR"))) {
      throw new ClientServiceException("Error found in user function");
    }
    return transactionStatusRepository.findCaseApplicationTransactionByTransactionId(transactionId)
        .map(transactionStatusMapper::toTransactionStatus);
  }
}
