package uk.gov.laa.ccms.data.service;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.mapper.casedetails.CaseDetailsMapper;
import uk.gov.laa.ccms.data.mapper.TransactionStatusMapper;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseDetailXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseInqRsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.common.MessageXml;
import uk.gov.laa.ccms.data.model.CaseDetail;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.repository.CaseDetailRepository;
import uk.gov.laa.ccms.data.repository.TransactionStatusRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Case Service Test")
public class CaseServiceTest {

  @Mock
  CaseDetailRepository caseDetailRepository;
  @Mock
  CaseDetailsMapper caseDetailsMapper;
  @Mock
  TransactionStatusMapper transactionStatusMapper;
  @Mock
  TransactionStatusRepository transactionStatusRepository;

  CaseService caseService;

  @BeforeEach
  void beforeEach(){
    caseService =
        new CaseService(caseDetailRepository, caseDetailsMapper,
            transactionStatusRepository, transactionStatusMapper);
  }

  @Test
  @DisplayName("Should return empty case transaction status")
  void shouldReturnEmptyCaseTransactionStatus(){
    // Given
    String transactionId = "123";
    // When
    Optional<TransactionStatus> transactionStatus = caseService.getTransactionStatus(transactionId);
    // Then
    assertTrue(transactionStatus.isEmpty());
  }

  @Test
  @DisplayName("Should return case transaction status")
  void shouldReturnCaseTransactionStatus() {
    // Given
    String transactionId = "123";
    uk.gov.laa.ccms.data.entity.TransactionStatus entity =
        uk.gov.laa.ccms.data.entity.TransactionStatus.builder()
            .requestId(transactionId)
            .processName("CreateCaseApplication").status("Success").build();
    when(transactionStatusRepository.findCaseApplicationTransactionByTransactionId(transactionId))
        .thenReturn(Optional.of(entity));
    TransactionStatus result = new TransactionStatus().submissionStatus("Success")
        .referenceNumber("123");
    when(transactionStatusMapper.toTransactionStatus(entity)).thenReturn(
        result);
    // When
    Optional<TransactionStatus> transactionStatus = caseService.getTransactionStatus(
        transactionId);
    // Then
    verify(transactionStatusRepository, times(1)).findAllUserFunctionTransactionsByTransactionId(
        transactionId);
    verify(transactionStatusRepository, times(1)).findCaseApplicationTransactionByTransactionId(
        transactionId);
    assertTrue(transactionStatus.isPresent());
    assertEquals(result, transactionStatus.get());
  }

  @Test
  @DisplayName("Should return case transaction status even with success transaction")
  void shouldReturnCaseTransactionStatusEvenWithSuccessTransactions() {
    // Given
    String transactionId = "123";
    uk.gov.laa.ccms.data.entity.TransactionStatus successTransaction =
        uk.gov.laa.ccms.data.entity.TransactionStatus.builder()
            .requestId(transactionId)
            .processName("XXCCMS_COMMON_UTIL.USER_FUNC_AUTH").status("Success").build();
    when(transactionStatusRepository.findAllUserFunctionTransactionsByTransactionId(transactionId))
        .thenReturn(singletonList(successTransaction));
    uk.gov.laa.ccms.data.entity.TransactionStatus entity =
        uk.gov.laa.ccms.data.entity.TransactionStatus.builder()
            .requestId(transactionId)
            .processName("CreateCaseApplication").status("Success").build();
    when(transactionStatusRepository.findCaseApplicationTransactionByTransactionId(transactionId))
        .thenReturn(Optional.of(entity));
    TransactionStatus result = new TransactionStatus().submissionStatus("Success")
        .referenceNumber("123");
    when(transactionStatusMapper.toTransactionStatus(entity)).thenReturn(
        result);

    // When
    Optional<TransactionStatus> transactionStatus = caseService.getTransactionStatus(
        transactionId);

    // Then
    verify(transactionStatusRepository, times(1)).findAllUserFunctionTransactionsByTransactionId(
        transactionId);
    verify(transactionStatusRepository, times(1)).findCaseApplicationTransactionByTransactionId(
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
    when(transactionStatusRepository.findAllUserFunctionTransactionsByTransactionId(transactionId))
        .thenReturn(singletonList(successTransaction));

    // When & Then
    assertThrows(ClientServiceException.class, () -> caseService.getTransactionStatus(transactionId));
    verify(transactionStatusRepository, times(1)).findAllUserFunctionTransactionsByTransactionId(
        transactionId);
    verify(transactionStatusRepository, times(0)).findCaseApplicationTransactionByTransactionId(
        transactionId);
  }

  @Nested
  @DisplayName("GetCaseDetails() Tests")
  class GetCaseDetailsTests {

    @Test
    @DisplayName("Should return case details")
    void shouldReturnCaseDetails() throws SQLException {
      // Given
      CaseInqRsXml caseInqRsXml = CaseInqRsXml.builder()
          .caseDetail(CaseDetailXml.builder()
              .message(new MessageXml("200", "status", "text"))
              .build()).build();
      CaseDetail caseDetail = new CaseDetail();
      caseDetail.setCaseReferenceNumber("123");
      when(caseDetailRepository.getCaseDetailXml("123", 1L, "user"))
          .thenReturn(caseInqRsXml);
      when(caseDetailsMapper.mapToCaseDetail(caseInqRsXml.getCaseDetail()))
          .thenReturn(caseDetail);
      // When
      Optional<CaseDetail> result = caseService.getCaseDetails("123",
          1L, "user");
      // Then
      assertThat(result).isPresent();
      assertThat(result.get().getCaseReferenceNumber()).isEqualTo("123");
    }

    @Test
    @DisplayName("Should return empty optional")
    void shouldReturnEmptyOptional() throws SQLException {
      // Given
      CaseInqRsXml caseInqRsXml = CaseInqRsXml.builder()
          .caseDetail(CaseDetailXml.builder()
              .message(new MessageXml("404", "status", "not found"))
              .build()).build();
      when(caseDetailRepository.getCaseDetailXml("123", 1L, "user"))
          .thenReturn(caseInqRsXml);
      // When
      Optional<CaseDetail> result = caseService.getCaseDetails("123",
          1L, "user");
      // Then
      assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should throw exception when message contains error")
    void shouldThrowExceptionWhenMessageContainsError() throws SQLException {
      // Given
      CaseInqRsXml caseInqRsXml = CaseInqRsXml.builder()
          .caseDetail(CaseDetailXml.builder()
            .message(new MessageXml("ORA-12345", "status", "SQL Error"))
              .build()).build();
      when(caseDetailRepository.getCaseDetailXml("123", 1L, "user"))
          .thenReturn(caseInqRsXml);
      // When
      SQLException result = assertThrows(SQLException.class,
          () -> caseService.getCaseDetails("123",
          1L, "user"));
      // Then
      assertThat(result.getMessage())
          .isEqualTo("Error occurred in EBS: ORA-12345 - SQL Error");
    }

    @Test
    @DisplayName("Should throw runtime exception when no case")
    void shouldThrowRuntimeExceptionWhenNoCase() throws SQLException {
      // Given
      // When
      RuntimeException result = assertThrows(RuntimeException.class,
          () -> caseService.getCaseDetails("123",
              1L, "user"));
      // Then
      assertThat(result.getMessage())
          .isEqualTo("Could not retrieve case details from EBS");
    }

  }
}
