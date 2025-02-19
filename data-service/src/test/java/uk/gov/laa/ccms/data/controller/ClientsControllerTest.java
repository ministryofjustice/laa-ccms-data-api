package uk.gov.laa.ccms.data.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.laa.ccms.data.model.ClientDetails;
import uk.gov.laa.ccms.data.model.ClientSummary;
import uk.gov.laa.ccms.data.model.TransactionStatus;
import uk.gov.laa.ccms.data.service.ClientService;
import uk.gov.laa.ccms.data.service.ClientServiceException;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
@WebAppConfiguration
@DisplayName("Clients Controller Test")
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
    mockMvc = standaloneSetup(clientsController)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .build();
    objectMapper = new ObjectMapper();
  }

  @Nested
  @DisplayName("getClientTransactionStatus() Tests")
  class GetClientTransactionStatusTests {

    @Test
    @DisplayName("Should return data")
    void shouldReturnData() throws Exception {
      // Given
      TransactionStatus status = new TransactionStatus().submissionStatus("status")
          .referenceNumber("123");
      when(clientService.getTransactionStatus("ABCDEF")).thenReturn(Optional.of(status));
      String jsonContent = objectMapper.writeValueAsString(status);
      // Then
      mockMvc.perform(get("/clients/status/ABCDEF"))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().json(jsonContent));
    }

    @Test
    @DisplayName("Should return not found")
    void shouldReturnNotFound() throws Exception {
      // Then
      mockMvc.perform(get("/clients/status/ABCDEF"))
          .andDo(print())
          .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return 500 error")
    void shouldReturn500Error() throws Exception {
      // When
      when(clientService.getTransactionStatus("ABCDEF")).thenThrow(
          new ClientServiceException("error"));
      // Then
      mockMvc.perform(get("/clients/status/ABCDEF"))
          .andDo(print())
          .andExpect(status().is5xxServerError());
    }

  }

  @Nested
  @DisplayName("getClients() Tests")
  class GetClientsTests{

    @Test
    @DisplayName("Should return data")
    void shouldReturnData() throws Exception {
      // Given
      ClientDetails details = new ClientDetails().addContentItem(new ClientSummary()
          .clientReferenceNumber("123"));
      doReturn(Optional.of(details)).when(clientService).getClients(
          any(), any(), any(), any(),
          any(), any(), any(), any());

      // Then
      String jsonContent = objectMapper.writeValueAsString(details);
      mockMvc.perform(get("/clients?first-name=first&surname=last&date-of-birth=2010-01-01"))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().json(jsonContent));
    }

    @Test
    @DisplayName("Should return not found")
    void shouldReturnNotFound() throws Exception {
      // Then
      mockMvc.perform(get("/clients?first-name=first&surname=last&date-of-birth=2010-01-01"))
          .andDo(print())
          .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("Should return bad request when missing first name")
    void shouldReturnBadRequestWhenMissingFirstName() throws Exception {
      // Then
      mockMvc.perform(get("/clients?surname=last&date-of-birth=2010-01-01"))
          .andDo(print())
          .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return bad request when missing surname")
    void shouldReturnBadRequestWhenMissingSurname() throws Exception {
      // Then
      mockMvc.perform(get("/clients?first-name=first&date-of-birth=2010-01-01"))
          .andDo(print())
          .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return bad request when missing date of birth")
    void shouldReturnBadRequestWhenMissingDateOfBirth() throws Exception {
      // Then
      mockMvc.perform(get("/clients?first-name=firstsurname=last"))
          .andDo(print())
          .andExpect(status().isBadRequest());
    }
  }
}
