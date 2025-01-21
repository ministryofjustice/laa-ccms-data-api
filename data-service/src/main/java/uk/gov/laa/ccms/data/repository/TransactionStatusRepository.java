package uk.gov.laa.ccms.data.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.TransactionStatus;

/**
 * Repository interface for accessing {@link TransactionStatus} entities.
 *
 * <p>This repository extends the {@link ReadOnlyRepository} interface, which supports
 * read-only operations for the {@link TransactionStatus} entity.</p>
 *
 * @see TransactionStatus
 * @see ReadOnlyRepository
 * @author Jamie Briggs
 */
@Repository
public interface TransactionStatusRepository extends ReadOnlyRepository<TransactionStatus, String> {

  /**
   * Finds a client transaction with a specific transaction ID.
   *
   * @param transactionId the unique identifier of the transaction to search for
   * @return an {@code Optional} containing the {@code TransactionStatus} if found, or an
   *     empty {@code Optional} if not found.
   */
  @Query("""
    SELECT ts FROM TransactionStatus ts
    WHERE ts.requestId = ?1
    AND ts.processName = 'CreateClient'
      """)
  Optional<TransactionStatus> findClientTransactionByTransactionId(String transactionId);
}
