package uk.gov.laa.ccms.data.controller;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import uk.gov.laa.ccms.data.model.AssessmentType;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetail;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetails;
import uk.gov.laa.ccms.data.service.CaseAssessmentService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Case Assessment Controller Test")
class CaseAssessmentControllerTest {

  @Mock
  private CaseAssessmentService caseAssessmentService;

  @InjectMocks
  private CaseAssessmentController caseAssessmentController;

  protected MockMvcTester mockMvc;

  @BeforeEach
  void setup() {
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new
        MappingJackson2HttpMessageConverter();
    mappingJackson2HttpMessageConverter.setObjectMapper(new ObjectMapper());
    mockMvc = MockMvcTester.create(standaloneSetup(caseAssessmentController)
            .build()).withHttpMessageConverters(singletonList(mappingJackson2HttpMessageConverter));
  }

  @Nested
  @DisplayName("GET: /cases/assessments")
  class GetAssessmentDetailTests {

    @ParameterizedTest
    @EnumSource(AssessmentType.class)
    @DisplayName("Should return 200 response")
    void shouldReturn200Response(AssessmentType assessmentType) {
      CaseAssessmentDetails details = new CaseAssessmentDetails().caseReference("123")
          .assessmentDetails(singletonList(
              new CaseAssessmentDetail().attributeName("Attribute").attributeValue("Value")));
      when(caseAssessmentService.getCaseAssessmentDetails("123", assessmentType))
          .thenReturn(Optional.of(details));
      assertThat(
          mockMvc.perform(get("/cases/assessments")
              .queryParam("case-reference-number", "123")
              .queryParam("assessment-type", assessmentType.name())))
          .hasStatusOk()
          .bodyJson()
          .convertTo(CaseAssessmentDetails.class)
          .isEqualTo(details);
    }

    @ParameterizedTest
    @EnumSource(AssessmentType.class)
    @DisplayName("Should return 400 response when missing case reference")
    void shouldReturn400ResponseWhenMissingCaseReference(AssessmentType assessmentType) {
      assertThat(
          mockMvc.perform(get("/cases/assessments")
              .queryParam("assessment-type", assessmentType.name())))
          .hasStatus4xxClientError();
    }

    @Test
    @DisplayName("Should return 400 response when missing application type")
    void shouldReturn400ResponseWhenMissingApplicationType() {
      assertThat(
          mockMvc.perform(get("/cases/assessments")
              .queryParam("case-reference-number", "123")))
          .hasStatus4xxClientError();
    }

    @Test
    @DisplayName("Should return 400 response when application type incorrect value")
    void shouldReturn400ResponseWhenIncorrectApplicationType() {
      assertThat(
          mockMvc.perform(get("/cases/assessments")
              .queryParam("assessment-type", "INVALID")
              .queryParam("case-reference-number", "123")))
          .hasStatus4xxClientError();
    }

  }

}