package uk.gov.laa.ccms.data.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.mapper.CaseDetailsMapper;
import uk.gov.laa.ccms.data.mapper.TransactionStatusMapper;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.CaseInqRSXml;
import uk.gov.laa.ccms.data.model.CaseDetail;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.repository.CaseDetailRepository;
import uk.gov.laa.ccms.data.repository.TransactionStatusRepository;

/**
 * Service class responsible for handling case-related operations.
 *
 * <p>This class provides methods to retrieve transaction statuses for case-related
 * transactions. It utilizes a repository for database access and a mapper for converting
 * database entities to objects used in the application.</p>
 *
 * @see TransactionStatusRepository
 * @see TransactionStatusMapper
 * @author Jamie Briggs
 */
@Service
public class CaseService {

  private final CaseDetailRepository caseDetailRepository;
  private final CaseDetailsMapper caseDetailsMapper;
  private final TransactionStatusRepository transactionStatusRepository;
  private final TransactionStatusMapper transactionStatusMapper;

  public CaseService(CaseDetailRepository caseDetailRepository, CaseDetailsMapper caseDetailsMapper,
      TransactionStatusRepository transactionStatusRepository,
      TransactionStatusMapper transactionStatusMapper) {
    this.caseDetailRepository = caseDetailRepository;
    this.caseDetailsMapper = caseDetailsMapper;
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
   * @throws ClientServiceException throws exception when there was an error found
   *     with the associated transaction ID
   */
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

  public Optional<CaseDetail> getCaseDetails(String caseReferenceNumber)
      throws JsonProcessingException, SQLException {
    CaseInqRSXml caseXml = caseDetailRepository.getCaseDetailXml(caseReferenceNumber, 26517L,
        "Tracey");
    return Optional.of(caseDetailsMapper.mapToCaseDetail(caseXml.getCaseDetail()));
  }
}
