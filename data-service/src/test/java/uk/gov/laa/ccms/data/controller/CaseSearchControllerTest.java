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
import uk.gov.laa.ccms.data.service.CaseSearchService;

@ExtendWith({SpringExtension.class})
@ContextConfiguration
@WebAppConfiguration
@DisplayName("Case search controller test")
class CaseSearchControllerTest {

  @Mock
  private CaseSearchService caseSearchService;

  @InjectMocks
  private CaseSearchController caseSearchController;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @Autowired
  WebApplicationContext webApplicationContext;

  @BeforeEach
  public void setup() {
    mockMvc = standaloneSetup(caseSearchController)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .build();
    objectMapper = new ObjectMapper();
  }

  @Test
  @DisplayName("getCases: Returns data")
  void getCases_returnsData() throws Exception {
    // Given
    CaseSummary caseSummary = new CaseSummary().caseReferenceNumber("123");
    CaseDetails caseDetails = new CaseDetails().addContentItem(caseSummary);
    when(caseSearchService.getCases(Mockito.any(),
        Mockito.any(), Mockito.any(), Mockito.any(),
        Mockito.any(), Mockito.any(), Mockito.any()))
        .thenReturn(Optional.of(caseDetails));
    // Then
    String jsonContent = objectMapper.writeValueAsString(caseDetails);
    this.mockMvc.perform(get("/cases"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(jsonContent));
  }

  @Test
  @DisplayName("getCases: Returns not found")
  void getCases_returnsNotFound() throws Exception {
    // Then
    this.mockMvc.perform(get("/cases"))
        .andDo(print())
        .andExpect(status().isNotFound());
  }
}
