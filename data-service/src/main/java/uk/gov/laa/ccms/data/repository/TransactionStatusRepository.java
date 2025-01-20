package uk.gov.laa.ccms.data.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.TransactionStatus;

@Repository
public interface TransactionStatusRepository extends ReadOnlyRepository<TransactionStatus, String> {

  @Query("SELECT ts FROM TransactionStatus ts WHERE ts.requestId = ?1 AND ts.processName = 'CreateClient'")
  Optional<TransactionStatus> findClientTransactionByTransactionId(String transactionId);
}
