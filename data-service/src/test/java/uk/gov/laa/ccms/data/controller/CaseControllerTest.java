package uk.gov.laa.ccms.data.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.laa.ccms.data.model.CaseDetails;
import uk.gov.laa.ccms.data.model.CaseSummary;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.service.CaseSearchService;
import uk.gov.laa.ccms.data.service.CaseService;
import uk.gov.laa.ccms.data.service.ClientServiceException;

@ExtendWith({SpringExtension.class})
@ContextConfiguration
@WebAppConfiguration
@DisplayName("Case Controller Test")
class CaseControllerTest {

  @Mock
  private CaseSearchService caseSearchService;
  @Mock
  private CaseService caseService;

  @InjectMocks
  private CaseController caseController;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @Autowired
  WebApplicationContext webApplicationContext;

  @BeforeEach
  void setup() {
    mockMvc = standaloneSetup(caseController)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .build();
    objectMapper = new ObjectMapper();
  }

  @Nested
  @DisplayName("getCases() Tests")
  class GetCasesTests {

    @Test
    @DisplayName("Should return data")
    void shouldReturnData() throws Exception {
      // Given
      CaseSummary caseSummary = new CaseSummary().caseReferenceNumber("123");
      CaseDetails caseDetails = new CaseDetails().addContentItem(caseSummary);
      when(caseSearchService.getCases(Mockito.eq(123L), Mockito.any(),
          Mockito.any(), Mockito.any(), Mockito.any(),
          Mockito.any(), Mockito.any(), Mockito.any()))
          .thenReturn(Optional.of(caseDetails));
      // Then
      String jsonContent = objectMapper.writeValueAsString(caseDetails);
      mockMvc.perform(get("/cases?provider-id=123"))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().json(jsonContent));
    }

    @Test
    @DisplayName("Should return bad request")
    void shouldReturnBadRequest() throws Exception {
      // Then
      mockMvc.perform(get("/cases"))
          .andDo(print())
          .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return not found")
    void shouldReturnNotFound() throws Exception {
      // Then
      mockMvc.perform(get("/cases?provider-id=123"))
          .andDo(print())
          .andExpect(status().isNotFound());
    }
  }

  @Nested
  @DisplayName("getCaseTransactionStatus() Tests")
  class GetCaseTransactionStatusTests {
    @Test
    @DisplayName("Should return data")
    void shouldReturnData() throws Exception {
      // Given
      TransactionStatus status = new TransactionStatus().submissionStatus("status").referenceNumber("123");
      when(caseService.getTransactionStatus("ABCDEF")).thenReturn(Optional.of(status));
      String jsonContent = objectMapper.writeValueAsString(status);
      // Then
      mockMvc.perform(get("/cases/status/ABCDEF"))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().json(jsonContent));
    }

    @Test
    @DisplayName("Should return not found")
    void shouldReturnNotFound() throws Exception {
      // Then
      mockMvc.perform(get("/cases/status/ABCDEF"))
          .andDo(print())
          .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return 500 error")
    void shouldReturn500Error() throws Exception {
      // When
      when(caseService.getTransactionStatus("ABCDEF")).thenThrow(new ClientServiceException("error"));
      // Then
      mockMvc.perform(get("/cases/status/ABCDEF"))
          .andDo(print())
          .andExpect(status().is5xxServerError());
    }
  }
}
