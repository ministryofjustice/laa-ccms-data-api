package uk.gov.laa.ccms.data.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.laa.ccms.data.model.CaseReferenceSummary;
import uk.gov.laa.ccms.data.service.NewCaseReferenceService;

@ExtendWith({SpringExtension.class})
@ContextConfiguration
@WebAppConfiguration
class CaseReferenceControllerTest {

  @Mock
  private NewCaseReferenceService newCaseReferenceService;

  @InjectMocks
  private CaseReferenceController caseReferenceController;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @Autowired
  WebApplicationContext webApplicationContext;

  @BeforeEach
  void setup(){
    mockMvc = standaloneSetup(caseReferenceController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void post_returnsData() throws Exception {
    // Given
    CaseReferenceSummary summary = new CaseReferenceSummary().caseReferenceNumber("123456");
    when(newCaseReferenceService.getNextAvailableCaseReference()).thenReturn(summary);
    // Then
    String jsonContent = objectMapper.writeValueAsString(summary);
    this.mockMvc.perform(post("/case-reference"))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().json(jsonContent));
  }
}