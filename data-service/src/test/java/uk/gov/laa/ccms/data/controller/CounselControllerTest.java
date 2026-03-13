package uk.gov.laa.ccms.data.controller;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import uk.gov.laa.ccms.data.advice.GlobalExceptionHandler;
import uk.gov.laa.ccms.data.model.ApiError;
import uk.gov.laa.ccms.data.model.CounselLookupDetail;
import uk.gov.laa.ccms.data.service.LookupService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = LookupController.class)
@DisplayName("Counsel Controller Test")
final class CounselControllerTest extends BaseCounselControllerTest {

  @MockitoBean private LookupService lookupService;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {}

  @BeforeEach
  public void setup() {
    mockMvc =
        standaloneSetup(new LookupController(lookupService))
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Nested
  @DisplayName("Get counsel test cases")
  class GetCounselTestCases {

    @Test
    @DisplayName("WHEN -> Data found for criteria, THEN -> Return 200 OK with array.")
    void shouldReturnData() throws Exception {

      CounselLookupDetail serviceReturnedCounselLookupDetail = getAllCounselLookupValues();

      when(lookupService.getCounselLookupValues(eq("SHAUN S DODDS"), any(), any(), any(), any()))
          .thenReturn(serviceReturnedCounselLookupDetail);

      String expected = getJsonString(serviceReturnedCounselLookupDetail);

      ResultActions resultActions =
          mockMvc
              .perform(
                  get("/lookup/counsels")
                      .param("page", "0")
                      .param("size", "10")
                      .param("sort", "name,desc")
                      .param("name", "SHAUN S DODDS"))
              .andExpect(status().isOk())
              .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("WHEN -> No rows found, THEN -> Return 200 OK with empty results array.")
    void shouldReturnNotFound() throws Exception {

      CounselLookupDetail noRowResponse = getAllCounselLookupValues();
      noRowResponse.setContent(new ArrayList<>());

      when(lookupService.getCounselLookupValues(eq("ASHU"), any(), any(), any(), any()))
          .thenReturn(noRowResponse);

      String expected = getJsonString(noRowResponse);

      String json =
          mockMvc
              .perform(
                  get("/lookup/counsels")
                      .param("page", "0")
                      .param("size", "10")
                      .param("sort", "name,desc")
                      .param("name", "ASHU"))
              .andExpect(status().isOk()) // Response 200 OK
              .andExpect(
                  content().string(not(emptyString()))) // // Paginated response with empty array
              .andExpect(content().json(expected)) // Expected no content response
              .andReturn()
              .getResponse()
              .getContentAsString();

      JsonNode content = getJson(json).get("content");
      Assertions.assertEquals(RECORDS_NOT_FOUND, content.size()); // Empty array
    }

    @Test
    @DisplayName(
        "WHEN -> Rows more than 500, THEN -> Return 400 Bad-Request with too many rows message.")
    void shouldReturnBadRequestForToManyRows() throws Exception {

      CounselLookupDetail response501Rows = getAllCounselLookupValues();
      response501Rows.setTotalElements(501);

      when(lookupService.getCounselLookupValues(eq("ASHU"), any(), any(), any(), any()))
          .thenReturn(response501Rows);

      ApiError apiErrorResponse =
          getRealObject(
              mockMvc
                  .perform(
                      get("/lookup/counsels")
                          .param("page", "0")
                          .param("size", "10")
                          .param("sort", "name,asc")
                          .param("name", "ASHU"))
                  .andExpect(status().isBadRequest()) // Response 400 Bad Request
                  .andReturn()
                  .getResponse()
                  .getContentAsString(),
              ApiError.class);

      Assertions.assertEquals(TOO_MANY_RESULTS, apiErrorResponse.getMessage());
    }

    @Test
    @DisplayName(
        "WHEN -> All params empty: THEN -> Return 400 Bad-Request with required at least one param message.")
    void shouldReturnBadRequestForAllEmptyParams() throws Exception {

      String json =
          mockMvc
              .perform(
                  get("/lookup/counsels")
                      .param("page", "0")
                      .param("size", "1")
                      .param("sort", "name, asc"))
              .andExpect(status().isBadRequest())
              .andReturn()
              .getResponse()
              .getContentAsString();

      ApiError apiErrorResponse = getRealObject(json, ApiError.class);
      Assertions.assertEquals(ALL_PARAMS_EMPTY, apiErrorResponse.getMessage());
    }
  }
}
