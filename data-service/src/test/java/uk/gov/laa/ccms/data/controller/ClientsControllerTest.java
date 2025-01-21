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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.service.ClientService;
import uk.gov.laa.ccms.data.service.ClientServiceException;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
@WebAppConfiguration
class ClientsControllerTest {

  @Mock
  private ClientService clientService;
  
  @InjectMocks
  private ClientsController clientsController;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @Autowired
  WebApplicationContext webApplicationContext;

  @BeforeEach
  public void setup(){
    mockMvc = standaloneSetup(clientsController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  @DisplayName("getClientTransactionStatus() - Returns Data")
  void getClientTransactionStatus_returnsData() throws Exception {
    // Given
    TransactionStatus status = new TransactionStatus().submissionStatus("status").referenceNumber("123");
    when(clientService.getTransactionStatus("ABCDEF")).thenReturn(Optional.of(status));
    String jsonContent = objectMapper.writeValueAsString(status);
    // Then
    this.mockMvc.perform(get("/clients/status/ABCDEF"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(jsonContent));
  }

  @Test
  @DisplayName("getClientTransactionStatus() - Returns not found")
  void getClientTransactionStatus_returnsNotFound() throws Exception {
    // Then
    this.mockMvc.perform(get("/clients/status/ABCDEF"))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("getClientTransactionStatus() - Returns 500 error")
  void getClientTransactionStatus_returns500Error() throws Exception {
    // When
    when(clientService.getTransactionStatus("ABCDEF")).thenThrow(new ClientServiceException("error"));
    // Then
    this.mockMvc.perform(get("/clients/status/ABCDEF"))
        .andDo(print())
        .andExpect(status().is5xxServerError());
  }
}
