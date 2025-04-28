package uk.gov.laa.ccms.data.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.exception.EbsApiRuntimeException;
import uk.gov.laa.ccms.data.mapper.TransactionStatusMapper;
import uk.gov.laa.ccms.data.mapper.casedetails.CaseDetailsMapper;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseInqRsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.common.MessageXml;
import uk.gov.laa.ccms.data.model.CaseDetail;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.repository.CaseDetailRepository;
import uk.gov.laa.ccms.data.repository.TransactionStatusRepository;

/**
 * Service class responsible for handling case-related operations.
 *
 * <p>This class provides methods to retrieve transaction statuses for case-related
 * transactions. It utilizes a repository for database access and a mapper for converting database
 * entities to objects used in the application.</p>
 *
 * @author Jamie Briggs
 * @see TransactionStatusRepository
 * @see TransactionStatusMapper
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
   * @param caseDetailRepository        the repository used for accessing case details
   * @param caseDetailsMapper           the mapper used for mapping case detail entities to models
   * @param transactionStatusRepository the repository used for accessing transaction statuses
   * @param transactionStatusMapper     the mapper used for mapping transaction status entities to
   *                                    models
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
   * Retrieves the transaction status for a given transaction ID. If the transaction is not found,
   * an empty {@code Optional} is returned.
   *
   * @param transactionId the unique identifier of the transaction whose status is to be retrieved
   * @return an {@code Optional} containing the {@link TransactionStatus} if found, or an empty
   *     {@code Optional} if not found
   * @throws ClientServiceException throws exception when there was an error found with the
   *                                associated transaction ID
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
   * Retrieves the details of a case based on the provided case reference number, provider ID, and
   * the username of the user fetching this case.
   *
   * @param caseReferenceNumber the unique reference number of the case
   * @param providerId          the ID of the provider associated with the case
   * @param userName            the username of the user accessing this case. Dictates what
   *                            available functions are returned alongside the case.
   * @return an {@code Optional} containing the {@link CaseDetail} if the case details are found, or
   *     an empty {@code Optional} if no details are available
   */
  public Optional<CaseDetail> getCaseDetails(String caseReferenceNumber, Long providerId,
      String userName) {
    try {
      CaseInqRsXml caseInqRsXml = caseDetailRepository.getCaseDetailXml(caseReferenceNumber,
          providerId, userName);
      validateCaseXmlObject(caseInqRsXml);
      return handleResponseStatus(caseInqRsXml.getCaseDetail().message(), caseInqRsXml);
    } catch (SQLException | JsonProcessingException e) {
      throw new EbsApiRuntimeException("Could not retrieve case details from EBS", e);
    }
  }

  private void validateCaseXmlObject(CaseInqRsXml caseInqRsXml) {
    if (Objects.isNull(caseInqRsXml)
        || Objects.isNull(caseInqRsXml.getCaseDetail())
        || Objects.isNull(caseInqRsXml.getCaseDetail().message())) {
      throw new EbsApiRuntimeException("Could not retrieve case details from EBS");
    }
  }

  private Optional<CaseDetail> handleResponseStatus(MessageXml messageXml,
      CaseInqRsXml caseInqRsXml) {
    String code = messageXml.code();
    if ("200".equals(code)) {
      CaseDetail caseDetail = caseDetailsMapper.mapToCaseDetail(caseInqRsXml.getCaseDetail());
      return Optional.of(caseDetail);
    } else if ("404".equals(code)) {
      return Optional.empty();
    } else {
      throw new EbsApiRuntimeException(
          "Error occurred in EBS: %s - %s".formatted(code, messageXml.text()));
    }
  }

}
