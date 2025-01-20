package uk.gov.laa.ccms.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionStatus {

  @Id
  @Column(name = "REQUEST_ID")
  private String requestId;

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

  @Column(name = "TRANSACTION_OCCURRENCE_DATE")
  private Date transactionOccurrenceDate;
}
