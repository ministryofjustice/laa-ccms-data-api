package uk.gov.laa.ccms.data.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;
import uk.gov.laa.ccms.data.model.CounselLookupDetail;
import uk.gov.laa.ccms.data.model.CounselLookupValueDetail;

public class LookupControllerIntegrationTest extends BaseLookupControllerIntegrationTest {

  @ParameterizedTest
  @CsvSource(
      value = {"null, null, null, null"},
      nullValues = "null")
  @DisplayName("WHEN: No parameters -> THEN: Return bad request with all params empty message.")
  public void get400BadRequestForAllEmptyParams(
      String name, String company, String legalAidSuppNumber, String category) throws Exception {

    restTestClient
        .get()
        .uri(getUriBuilder(name, company, legalAidSuppNumber, category))
        .exchange()
        .expectStatus()
        .isBadRequest()
        .expectBody()
        .jsonPath("$.code")
        .isEqualTo(HttpStatus.BAD_REQUEST.value())
        .jsonPath("$.message")
        .isEqualTo(ALL_PARAMS_EMPTY);
  }

  @ParameterizedTest
  @CsvSource(
      value = {"null, null, ASHU, null"},
      nullValues = "null")
  @DisplayName("WHEN: NO data found -> THEN: Return 200 OK with empty results array.")
  public void get200OKForNonNullOneOfParameter(
      String name, String company, String legalAidSuppNumber, String category) throws Exception {

    restTestClient
        .get()
        .uri(getUriBuilder(name, category, company, legalAidSuppNumber))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(CounselLookupDetail.class)
        .isEqualTo(expectedResponse(0, 0, 0, 10, List.of()));
  }

  @ParameterizedTest
  @CsvSource(
      value = {"XYZ198, null, null, null"},
      nullValues = "null")
  @DisplayName("WHEN: Data found -> THEN: Return 200 OK with array of one element.")
  public void get200OKForDataFoundExactlyOneElement(
      String name, String company, String legalAidSuppNumber, String category) throws Exception {

    List<CounselLookupValueDetail> content =
        List.of(
            new CounselLookupValueDetail[] {
              new CounselLookupValueDetail()
                  .name("XYZ198")
                  .company("XYZ198")
                  .legalAidSupplierNumber("993WF")
                  .category("Junior")
                  .county(null)
            });

    restTestClient
        .get()
        .uri(getUriBuilder(name, category, company, legalAidSuppNumber))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(CounselLookupDetail.class)
        .isEqualTo(expectedResponse(1, 1, 0, 10, content));
  }

  @ParameterizedTest
  @CsvSource(
      value = {"XYZ19, null, null, null"},
      nullValues = "null")
  @DisplayName("WHEN: Data found -> THEN: Return 200 OK with array of more than 1 elements.")
  public void get200OKForDataFoundMoreThanOneElement(
      String name, String company, String legalAidSuppNumber, String category) {

    List<CounselLookupValueDetail> content =
        IntStream.range(0, 10)
            .mapToObj(
                x ->
                    new CounselLookupValueDetail()
                        .name("XYZ19" + x)
                        .company("XYZ19" + x)
                        .legalAidSupplierNumber("993WF")
                        .category("Junior")
                        .county(null))
            .toList();

    CounselLookupDetail expectedBody = expectedResponse(2, 11, 0, 10, content);

    CounselLookupDetail responseBody =
        restTestClient
            .get()
            .uri(getUriBuilder(name, category, company, legalAidSuppNumber))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(CounselLookupDetail.class)
            .returnResult()
            .getResponseBody();

    assertThat(responseBody)
        .usingRecursiveComparison()
        .ignoringCollectionOrderInFields("content")
        .isEqualTo(expectedBody);
  }

  @ParameterizedTest
  @CsvSource(
      value = {"null, null, null, Junior"},
      nullValues = "null")
  @DisplayName("WHEN: No parameters -> THEN: Return bad request with all params empty message.")
  public void get400BadRequestForTooManyResult(
      String name, String company, String legalAidSuppNumber, String category) throws Exception {

    restTestClient
        .get()
        .uri(getUriBuilder(name, company, legalAidSuppNumber, category))
        .exchange()
        .expectStatus()
        .isBadRequest()
        .expectBody()
        .jsonPath("$.code")
        .isEqualTo(HttpStatus.BAD_REQUEST.value())
        .jsonPath("$.message")
        .isEqualTo(TOO_MANY_RESULTS);
  }
}
