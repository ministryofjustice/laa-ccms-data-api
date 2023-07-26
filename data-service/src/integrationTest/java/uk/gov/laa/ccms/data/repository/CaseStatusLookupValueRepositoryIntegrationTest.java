package uk.gov.laa.ccms.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.AbstractIntegrationTest;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;


@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/app_case_status_create_schema.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/sql/app_case_status_drop_schema.sql")
public class CaseStatusLookupValueRepositoryIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private CaseStatusLookupValueRepository caseStatusLookupValueRepository;

  @ParameterizedTest
  @Sql(statements = {
      "INSERT INTO XXCCMS_APP_CASE_STATUS_V (STATUS_CODE, STATUS_DESCRIPTION, COPY_ALLOWED_IND) " +
          "VALUES ('S1', 'Status One', 'N');",
      "INSERT INTO XXCCMS_APP_CASE_STATUS_V (STATUS_CODE, STATUS_DESCRIPTION, COPY_ALLOWED_IND) " +
          "VALUES ('S2', 'Status Two', 'Y');",
      "INSERT INTO XXCCMS_APP_CASE_STATUS_V (STATUS_CODE, STATUS_DESCRIPTION, COPY_ALLOWED_IND) " +
          "VALUES ('S3', 'Status Three', 'N');",
  })
  @CsvSource({"true, 1", "false, 2"})
  public void testFindAllWithExample(Boolean copyAllowed, Long expectedResults) {
    // Create an example with desired filters
    CaseStatusLookupValue example = new CaseStatusLookupValue();
    example.setCopyAllowed(copyAllowed);

    // Create a pageable object
    Pageable pageable = PageRequest.of(0, 10);

    // Call the repository method
    Page<CaseStatusLookupValue> result =
        caseStatusLookupValueRepository.findAll(Example.of(example), pageable);

    // Assert the result
    assertNotNull(result);
    assertEquals(expectedResults, result.getTotalElements());
  }
}
