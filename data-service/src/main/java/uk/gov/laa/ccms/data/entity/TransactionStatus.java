package uk.gov.laa.ccms.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@Table(name = "XXCCMS_TRANSACTION_STATUS_V")
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

  @Getter
  @Setter
  @EqualsAndHashCode
  public static class TransactionStatusId implements Serializable {
    private String requestId;
    private LocalDateTime transactionOccurrenceDate;
  }
}
