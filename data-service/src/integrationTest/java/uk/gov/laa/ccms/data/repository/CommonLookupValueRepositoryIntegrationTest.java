package uk.gov.laa.ccms.data.repository;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;


@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase=BEFORE_TEST_METHOD,scripts= "/sql/common_lookup_values_create_schema.sql")
@Sql(executionPhase=AFTER_TEST_METHOD,scripts= "/sql/common_lookup_values_drop_schema.sql")
public class CommonLookupValueRepositoryIntegrationTest extends AbstractIntegrationTest {

        @Autowired
        private CommonLookupValueRepository commonLookupValueRepository;

        @ParameterizedTest
        @Sql(statements = {
                "INSERT INTO XXCCMS_COMMON_LOV_V (LOV_TYPE, CODE, DESCRIPTION, START_DATE_ACTIVE) " +
                        "VALUES ('type1', 'code1', 'Description 1', CURRENT_TIMESTAMP)",
                "INSERT INTO XXCCMS_COMMON_LOV_V (LOV_TYPE, CODE, DESCRIPTION, START_DATE_ACTIVE) " +
                        "VALUES ('type1', 'code2', 'Description 2', CURRENT_TIMESTAMP)",
                // Add more INSERT statements as needed
        })
        @CsvSource(value= {
                "type1, null, 2",
                "type2, null, 0",
                "null, code1, 1"},
                nullValues={"null"})
        public void testFindAllWithExampleAndPageable(String type, String code, Long expectedElements) {
            // Create an example with desired filters
            CommonLookupValue example = new CommonLookupValue();
            example.setType(type);
            example.setCode(code);

            // Create a pageable object
            Pageable pageable = PageRequest.of(0, 10);

            // Call the repository method
            Page<CommonLookupValue> result = commonLookupValueRepository.findAll(Example.of(example), pageable);

            // Assert the result
            assertNotNull(result);
            assertEquals(expectedElements, result.getTotalElements());
        }
    }
