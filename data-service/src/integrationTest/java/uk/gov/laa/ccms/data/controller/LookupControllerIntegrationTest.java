package uk.gov.laa.ccms.data.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import uk.gov.laa.ccms.data.model.CounselLookupDetail;
import uk.gov.laa.ccms.data.model.CounselLookupValueDetail;

public class LookupControllerIntegrationTest extends BaseLookupControllerIntegrationTest {

  @Test
  @DisplayName("WHEN: No parameters -> THEN: Return bad request with all params empty message.")
  public void get400BadRequestForAllEmptyParams() throws Exception {

    restTestClient
        .get()
        .uri(getUriBuilder(null, null, null, null))
        .exchange()
        .expectStatus()
        .isBadRequest()
        .expectBody()
        .jsonPath("$.code")
        .isEqualTo(HttpStatus.BAD_REQUEST.value())
        .jsonPath("$.message")
        .isEqualTo(ALL_PARAMS_EMPTY);
  }

  @Test
  @DisplayName("WHEN: NO data found -> THEN: Return 200 OK with empty results array.")
  public void get200OKForNonNullOneOfParameter() throws Exception {

    restTestClient
        .get()
        .uri(getUriBuilder(null, null, "ASHU", null))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(CounselLookupDetail.class)
        .isEqualTo(expectedResponse(0, 0, 0, 10, List.of()));
  }

  @Test
  @DisplayName("WHEN: Data found -> THEN: Return 200 OK with array of one element.")
  public void get200OKForDataFoundExactlyOneElement() throws Exception {

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
        .uri(getUriBuilder("XYZ198", null, null, null))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(CounselLookupDetail.class)
        .isEqualTo(expectedResponse(1, 1, 0, 10, content));
  }

  @Test
  @DisplayName("WHEN: Data found -> THEN: Return 200 OK with array of more than 1 elements.")
  public void get200OKForDataFoundMoreThanOneElement() {

    List<CounselLookupValueDetail> content =
        IntStream.range(0, 11)
            .mapToObj(x -> x == 0 ? getElements("XYZ19") : getElements("XYZ19" + (x - 1)))
            .toList();

    CounselLookupDetail expectedBody = expectedResponse(2, 11, 0, 10, content);

    CounselLookupDetail responseBody =
        restTestClient
            .get()
            .uri(getUriBuilder("XYZ19", null, null, null))
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

  @Test
  @DisplayName("WHEN: No parameters -> THEN: Return bad request with all params empty message.")
  public void get400BadRequestForTooManyResult() throws Exception {

    restTestClient
        .get()
        .uri(getUriBuilder(null, null, null, "Junior"))
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
