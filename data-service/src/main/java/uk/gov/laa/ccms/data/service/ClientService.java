package uk.gov.laa.ccms.data.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.ClientDetail;
import uk.gov.laa.ccms.data.mapper.ClientDetailsMapper;
import uk.gov.laa.ccms.data.mapper.TransactionStatusMapper;
import uk.gov.laa.ccms.data.model.ClientDetails;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.repository.ClientDetailRepository;
import uk.gov.laa.ccms.data.repository.TransactionStatusRepository;
import uk.gov.laa.ccms.data.repository.specification.ClientDetailSpecification;
import uk.gov.laa.ccms.data.repository.spring.JPAClientDetailRepository;

/**
 * Service class responsible for handling client-related operations.
 *
 * <p>This class provides methods to retrieve transaction statuses for client-related transactions.
 * It utilizes a repository for database access and a mapper for converting database entities
 * to objects used in the application.</p>
 *
 * @see TransactionStatusRepository
 * @see TransactionStatusMapper
 * @author Jamie Briggs
 */
@Service
@RequiredArgsConstructor
public class ClientService {

  private final JPAClientDetailRepository clientDetailRepository;
  private final ClientDetailRepository oldclientDetailRepository;
  private final TransactionStatusRepository transactionStatusRepository;
  private final ClientDetailsMapper clientDetailsMapper;
  private final TransactionStatusMapper transactionStatusMapper;

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
  public Optional<TransactionStatus> getTransactionStatus(String transactionId)
      throws ClientServiceException {
    List<uk.gov.laa.ccms.data.entity.TransactionStatus> userFuncStatus =
        transactionStatusRepository.findAllUserFunctionTransactionsByTransactionId(transactionId);
    if (userFuncStatus.stream().anyMatch(x -> x.getStatus()
        .equalsIgnoreCase("ERROR"))) {
      throw new ClientServiceException("Error found in user function");
    }
    return transactionStatusRepository.findClientTransactionByTransactionId(transactionId)
        .map(transactionStatusMapper::toTransactionStatus);
  }


  public Optional<ClientDetails> getClients(String firstName, String surname,
      LocalDate dateOfBirth, String gender, String clientReferenceNumber,
      String homeOfficeReference, String nationalInsuranceNumber, Pageable pageable) {
    Page<ClientDetail> pagedClients = clientDetailRepository.findAll(
        ClientDetailSpecification.filterBy(firstName, surname,
        dateOfBirth, gender, clientReferenceNumber, homeOfficeReference,
        nationalInsuranceNumber), pageable);
    ClientDetails clientDetails = clientDetailsMapper.mapToClientDetails(
        pagedClients);
    return Optional.ofNullable(clientDetails);
  }


}
