package uk.gov.laa.ccms.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.entity.TransactionStatus.TransactionStatusId;

/**
 * Represents a transaction status entity from the "XXCCMS_TRANSACTION_STATUS_V" database view.
 *
 * <p>This entity captures details about transaction statuses, such as the request ID,
 *     process name, record reference key, status, error description, and transaction
 *     occurrence date.</p>
 *
 * <p>This class is immutable, and its instances can be created using the builder pattern.</p>
 */
@Entity
@Table(name = "XXCCMS_TRANSACTION_STATUS_V", schema = "XXCCMS")
@Getter
@Builder
@IdClass(TransactionStatusId.class)
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionStatus {

  @Id
  @Column(name = "REQUEST_ID")
  private String requestId;

  @Id
  @Column(name = "TRANSACTION_OCCURRENCE_DATE")
  private LocalDateTime transactionOccurrenceDate;

  @Id
  @Column(name = "PROCESS_NAME", length = 50)
  private String processName;

  @Column(name = "RECORD_REF_KEY", length = 100)
  private String recordRefKey;

  @Column(name = "RECORD_REF_VALUE", length = 250)
  private String recordRefValue;

  @Column(name = "STATUS", length = 10)
  private String status;

  @Column(name = "ERROR_DESCRIPTION", length = 2000)
  private String errorDescription;

  /**
   * This class represents the composite primary key for the TransactionStatus entity.
   *
   * @author Jamie Briggs
   * @see TransactionStatus
   */
  @Getter
  @Setter
  public static class TransactionStatusId implements Serializable {
    private String requestId;
    private String processName;
    private LocalDateTime transactionOccurrenceDate;

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      TransactionStatusId that = (TransactionStatusId) o;
      return Objects.equals(requestId, that.requestId) && Objects.equals(
          processName, that.processName) && Objects.equals(transactionOccurrenceDate,
          that.transactionOccurrenceDate);
    }

    @Override
    public int hashCode() {
      return Objects.hash(requestId, processName, transactionOccurrenceDate);
    }
  }
}
