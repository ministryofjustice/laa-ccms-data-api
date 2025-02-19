package uk.gov.laa.ccms.data.service;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.gov.laa.ccms.data.entity.ClientDetail;
import uk.gov.laa.ccms.data.mapper.ClientDetailsMapper;
import uk.gov.laa.ccms.data.mapper.TransactionStatusMapper;
import uk.gov.laa.ccms.data.model.ClientDetails;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.repository.ClientDetailRepository;
import uk.gov.laa.ccms.data.repository.TransactionStatusRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Client Service Test")
class ClientServiceTest {

  @Mock
  TransactionStatusRepository transactionStatusRepository;
  @Mock
  ClientDetailRepository clientDetailRepository;
  @Mock
  TransactionStatusMapper transactionStatusMapper;
  @Mock
  ClientDetailsMapper clientDetailsMapper;

  ClientService clientService;

  @BeforeEach
  void beforeEach() {
    clientService =
        new ClientService(clientDetailRepository, transactionStatusRepository,
            clientDetailsMapper, transactionStatusMapper);
  }

  @Nested
  @DisplayName("GetTransactionStatus() Tests")
  class GetTransactionStatusTest {


    @Test
    @DisplayName("Should return empty client transaction status")
    void shouldReturnEmptyClientTransactionStatus() {
      // Given
      String transactionId = "123";
      // When
      Optional<TransactionStatus> transactionStatus = clientService.getTransactionStatus(
          transactionId);
      // Then
      assertTrue(transactionStatus.isEmpty());
    }

    @Test
    @DisplayName("Should return client transaction status")
    void shouldReturnClientTransactionStatus() {
      // Given
      String transactionId = "123";
      uk.gov.laa.ccms.data.entity.TransactionStatus entity =
          uk.gov.laa.ccms.data.entity.TransactionStatus.builder()
              .requestId(transactionId)
              .processName("CreateClient").status("Success").build();
      when(transactionStatusRepository.findClientTransactionByTransactionId(transactionId))
          .thenReturn(Optional.of(entity));
      TransactionStatus result = new TransactionStatus().submissionStatus("Success")
          .referenceNumber("123");
      when(transactionStatusMapper.toTransactionStatus(entity)).thenReturn(
          result);
      // When
      Optional<TransactionStatus> transactionStatus = clientService.getTransactionStatus(
          transactionId);
      // Then
      verify(transactionStatusRepository, times(1)).findAllUserFunctionTransactionsByTransactionId(
          transactionId);
      verify(transactionStatusRepository, times(1)).findClientTransactionByTransactionId(
          transactionId);
      assertTrue(transactionStatus.isPresent());
      assertEquals(result, transactionStatus.get());
    }

    @Test
    @DisplayName("Should return client transaction status even with success transaction")
    void shouldReturnClientTransactionStatusEvenWithSuccessTransactions() {
      // Given
      String transactionId = "123";
      uk.gov.laa.ccms.data.entity.TransactionStatus successTransaction =
          uk.gov.laa.ccms.data.entity.TransactionStatus.builder()
              .requestId(transactionId)
              .processName("XXCCMS_COMMON_UTIL.USER_FUNC_AUTH").status("Success").build();
      when(
          transactionStatusRepository.findAllUserFunctionTransactionsByTransactionId(transactionId))
          .thenReturn(singletonList(successTransaction));
      uk.gov.laa.ccms.data.entity.TransactionStatus entity =
          uk.gov.laa.ccms.data.entity.TransactionStatus.builder()
              .requestId(transactionId)
              .processName("CreateClient").status("Success").build();
      when(transactionStatusRepository.findClientTransactionByTransactionId(transactionId))
          .thenReturn(Optional.of(entity));
      TransactionStatus result = new TransactionStatus().submissionStatus("Success")
          .referenceNumber("123");
      when(transactionStatusMapper.toTransactionStatus(entity)).thenReturn(
          result);

      // When
      Optional<TransactionStatus> transactionStatus = clientService.getTransactionStatus(
          transactionId);

      // Then
      verify(transactionStatusRepository, times(1)).findAllUserFunctionTransactionsByTransactionId(
          transactionId);
      verify(transactionStatusRepository, times(1)).findClientTransactionByTransactionId(
          transactionId);
      assertTrue(transactionStatus.isPresent());
      assertEquals(result, transactionStatus.get());
    }

    @Test
    @DisplayName("Should throw exception when user function transaction is error")
    void shouldThrowExceptionWhenUserFunctionTransactionIsError() {
      // Given
      String transactionId = "123";
      uk.gov.laa.ccms.data.entity.TransactionStatus successTransaction =
          uk.gov.laa.ccms.data.entity.TransactionStatus.builder()
              .requestId(transactionId)
              .processName("XXCCMS_COMMON_UTIL.USER_FUNC_AUTH").status("Error").build();
      when(
          transactionStatusRepository.findAllUserFunctionTransactionsByTransactionId(transactionId))
          .thenReturn(singletonList(successTransaction));

      // When & Then
      assertThrows(ClientServiceException.class,
          () -> clientService.getTransactionStatus(transactionId));
      verify(transactionStatusRepository, times(1)).findAllUserFunctionTransactionsByTransactionId(
          transactionId);
      verify(transactionStatusRepository, times(0)).findClientTransactionByTransactionId(
          transactionId);
    }
  }

  @Nested
  @DisplayName("GetClientDetails() Tests")
  class GetClientDetailsTest {

    @Test
    @DisplayName("Should return client details")
    void shouldReturnClientDetails(){
      // Given
      ClientDetail clientDetail = new ClientDetail();
      PageImpl<ClientDetail> pagedResult = new PageImpl<>(singletonList(
          clientDetail
      ));
      when(clientDetailRepository.findAll(any(), any())).thenReturn(pagedResult);
      when(clientDetailsMapper.mapToClientDetails(pagedResult)).thenReturn(new ClientDetails()
          .size(1).totalPages(20));
      // When
      Optional<ClientDetails> result = clientService.getClients("First", "Last",
          LocalDate.of(2000, 1, 1), "", "", "",
          "", Pageable.unpaged());
      // Then
      assertTrue(result.isPresent());
      assertEquals(1, result.get().getSize());
      assertEquals(20, result.get().getTotalPages());
    }

  }
}
