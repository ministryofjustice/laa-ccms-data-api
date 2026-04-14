package uk.gov.laa.ccms.data.controller;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.laa.ccms.data.entity.CounselLookupValue;
import uk.gov.laa.ccms.data.mapper.LookupMapperImpl;
import uk.gov.laa.ccms.data.model.ApiError;
import uk.gov.laa.ccms.data.service.LookupService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = LookupController.class)
@Import(LookupMapperImpl.class)
@DisplayName("Counsel Controller Test")
final class CounselControllerTest extends BaseCounselControllerTest {

  @MockitoBean private LookupService lookupService;

  @Autowired private MockMvc mockMvc;

  @BeforeEach
  void setUp() {}

  @Nested
  @DisplayName("Get counsel test cases")
  class GetCounselTestCases {

    @Test
    @DisplayName("WHEN -> Data found for criteria, THEN -> Return 200 OK with array.")
    void shouldReturnData() throws Exception {

      List<CounselLookupValue> counselLookupValues = getAllCounselLookupValues();

      Page<CounselLookupValue> counselPage =
          new PageImpl<>(
              counselLookupValues, Pageable.ofSize(10).withPage(0), counselLookupValues.size());

      when(lookupService.getCounselLookupValues(
              eq("SHAUN S DODDS"), any(), any(), any(), any(Pageable.class)))
          .thenReturn(counselPage);

      String expected = getJsonString(getCounselLookupDetail(counselLookupValues));

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

      List<CounselLookupValue> emptyContent = new ArrayList<>();

      Page<CounselLookupValue> emptyPage =
          new PageImpl<>(emptyContent, Pageable.ofSize(10).withPage(0), emptyContent.size());

      when(lookupService.getCounselLookupValues(
              eq("ASHU"), any(), any(), any(), any(Pageable.class)))
          .thenReturn(emptyPage);

      String expected = getJsonString(getCounselLookupDetail(emptyContent));

      mockMvc
          .perform(
              get("/lookup/counsels")
                  .param("page", "0")
                  .param("size", "10")
                  .param("sort", "name,desc")
                  .param("name", "ASHU"))
          .andExpect(status().isOk()) // Response 200 OK
          .andExpect(content().string(not(emptyString()))) // // Paginated response with empty array
          .andExpect(content().json(expected)); // Expected no content response
    }

    @Test
    @DisplayName(
        "WHEN -> Rows more than 500, THEN -> Return 400 Bad-Request with too many rows message.")
    void shouldReturnBadRequestForToManyRows() throws Exception {

      String expectedError =
          getJsonString(
              new ApiError().message(TOO_MANY_RESULTS).code(HttpStatus.BAD_REQUEST.value()));

      List<CounselLookupValue> lookup501Values = get501CounselLookupValues();

      Page<CounselLookupValue> lookup501Page =
          new PageImpl<>(lookup501Values, Pageable.ofSize(10).withPage(0), lookup501Values.size());

      when(lookupService.getCounselLookupValues(
              eq("ASHU"), any(), any(), any(), any(Pageable.class)))
          .thenReturn(lookup501Page);

      mockMvc
          .perform(
              get("/lookup/counsels")
                  .param("page", "0")
                  .param("size", "10")
                  .param("sort", "name,asc")
                  .param("name", "ASHU"))
          .andExpect(status().isBadRequest()) // Response 400 Bad Request
          .andExpect(content().json(expectedError));
    }

    @Test
    @DisplayName(
        "WHEN -> All params empty: THEN -> Return 400 Bad-Request with required at least one param message.")
    void shouldReturnBadRequestForAllEmptyParams() throws Exception {

      String expectedError =
          getJsonString(
              new ApiError().message(ALL_PARAMS_EMPTY).code(HttpStatus.BAD_REQUEST.value()));

      mockMvc
          .perform(
              get("/lookup/counsels")
                  .param("page", "0")
                  .param("size", "1")
                  .param("sort", "name, asc"))
          .andExpect(status().isBadRequest())
          .andExpect(content().json(expectedError));
    }
  }
}
