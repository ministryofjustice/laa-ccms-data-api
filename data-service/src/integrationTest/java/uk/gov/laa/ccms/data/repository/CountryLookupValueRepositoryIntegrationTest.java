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
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;

@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase=BEFORE_TEST_METHOD,scripts= "/sql/country_create_schema.sql")
@Sql(executionPhase=AFTER_TEST_METHOD,scripts= "/sql/country_drop_schema.sql")
public class CountryLookupValueRepositoryIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private CountryLookupValueRepository countryLookupValueRepository;

  @ParameterizedTest
  @Sql(statements = {
          "INSERT INTO XXCCMS_COUNTRY_V (CODE, DESCRIPTION) " +
                  "VALUES ('code1', 'Description 1')",
          "INSERT INTO XXCCMS_COUNTRY_V (CODE, DESCRIPTION) " +
                  "VALUES ('code2', 'Description 2')",
  })
  @CsvSource(value= {
          "code1, null, 1",
          "code2, null, 1",
          "null, Description 1, 1"},
          nullValues={"null"})
  public void testFindAllWithExampleAndPageable(String code, String description, Long expectedElements) {
    // Create an example with desired filters
    CountryLookupValue example = new CountryLookupValue();
    example.setDescription(description);
    example.setCode(code);

    // Create a pageable object
    Pageable pageable = PageRequest.of(0, 10);

    // Call the repository method
    Page<CountryLookupValue> result = countryLookupValueRepository.findAll(Example.of(example), pageable);

    // Assert the result
    assertNotNull(result);
    assertEquals(expectedElements, result.getTotalElements());
  }

}
