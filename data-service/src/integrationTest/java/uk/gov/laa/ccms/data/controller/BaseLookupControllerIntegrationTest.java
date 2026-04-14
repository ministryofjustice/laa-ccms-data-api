package uk.gov.laa.ccms.data.controller;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import uk.gov.laa.ccms.data.OracleIntegrationTestInterface;
import uk.gov.laa.ccms.data.model.CounselLookupDetail;
import uk.gov.laa.ccms.data.model.CounselLookupValueDetail;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SqlMergeMode(MERGE)
@Sql(executionPhase = BEFORE_TEST_CLASS, scripts = "/sql/lookup_create_schema.sql")
@Sql(executionPhase = BEFORE_TEST_CLASS, scripts = "/sql/insert_counsel_lookup_bulk_data.sql")
@Sql(executionPhase = AFTER_TEST_CLASS, scripts = "/sql/lookup_drop_schema.sql")
public class BaseLookupControllerIntegrationTest implements OracleIntegrationTestInterface {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  protected static final String ALL_PARAMS_EMPTY =
      "Invalid request. Please input at least one parameter for your search criteria.";

  @Autowired protected WebApplicationContext webApplicationContext;

  protected RestTestClient restTestClient;

  @Value("${laa.springboot.starter.auth.authorized-clients}")
  private String authorizedClients;

  @BeforeEach
  void setup() throws JsonProcessingException {

    String authenticationToken =
        OBJECT_MAPPER.readTree(authorizedClients).get(0).get("token").asText();

    restTestClient =
        RestTestClient.bindToApplicationContext(webApplicationContext)
            .defaultHeader("Authorization", authenticationToken)
            .build();
  }

  protected CounselLookupValueDetail getElements(String x) {
    return new CounselLookupValueDetail()
        .name(x)
        .company(x)
        .legalAidSupplierNumber("993WF")
        .category("Junior")
        .county(null);
  }

  protected Function<UriBuilder, URI> getUriBuilder(
      String name, String company, String legalAidSuppNumber, String category) {

    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();

    UriBuilder builder =
        factory
            .builder()
            .path("/lookup/counsels")
            .queryParamIfPresent("name", Optional.ofNullable(name))
            .queryParamIfPresent("company", Optional.ofNullable(company))
            .queryParamIfPresent(
                "legal_aid_supplier_number", Optional.ofNullable(legalAidSuppNumber))
            .queryParamIfPresent("category", Optional.ofNullable(category))
            .queryParam("page", "0")
            .queryParam("size", "10")
            .queryParam("sort", "name,desc");

    return uriBuilder -> builder.build();
  }

  protected CounselLookupDetail expectedResponse(
      int p, int e, int n, int s, List<CounselLookupValueDetail> l) {
    return new CounselLookupDetail().totalPages(p).totalElements(e).number(n).size(s).content(l);
  }
}
