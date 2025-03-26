package uk.gov.laa.ccms.data.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.mapper.TransactionStatusMapper;
import uk.gov.laa.ccms.data.mapper.casedetails.CaseDetailsMapper;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.CaseInqRsXml;
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

  /**
   * Constructs an instance of {@code CaseService}.
   *
   * @param caseDetailRepository the repository used for accessing case details
   * @param caseDetailsMapper the mapper used for mapping case detail entities to models
   * @param transactionStatusRepository the repository used for accessing transaction statuses
   * @param transactionStatusMapper the mapper used for mapping transaction status entities
   *                                to models
   */
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

  /**
   * Retrieves the details of a case based on the provided case reference number, provider ID,
   * and client's first name.
   *
   * @param caseReferenceNumber the unique reference number of the case
   * @param providerId the ID of the provider associated with the case
   * @param clientFirstName the first name of the client linked to the case
   * @return an {@code Optional} containing the {@link CaseDetail} if the case details are found,
   *         or an empty {@code Optional} if no details are available
   * @throws JsonProcessingException if there is an error processing XML data related to the case
   * @throws SQLException if an error occurs while interacting with the database
   */
  public Optional<CaseDetail> getCaseDetails(String caseReferenceNumber, Long providerId,
      String clientFirstName)
      throws SQLException {
    CaseInqRsXml caseXml = caseDetailRepository.getCaseDetailXml(caseReferenceNumber, providerId,
        clientFirstName);
    if (caseXml != null && caseXml.getCaseDetail() != null) {
      CaseDetail caseDetail = caseDetailsMapper.mapToCaseDetail(caseXml.getCaseDetail());
      return Optional.of(caseDetail);
    }
    return Optional.empty();
  }
}
