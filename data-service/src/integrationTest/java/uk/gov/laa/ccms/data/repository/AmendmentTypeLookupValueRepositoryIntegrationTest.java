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
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase=BEFORE_TEST_METHOD,scripts="/sql/app_amend_types_create_schema.sql" )
@Sql(executionPhase=AFTER_TEST_METHOD,scripts="/sql/app_amend_types_drop_schema.sql")
public class AmendmentTypeLookupValueRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AmendmentTypeLookupValueRepository amendmentTypeLookupValueRepository;

    @ParameterizedTest
    @Sql(statements = {
            "INSERT INTO XXCCMS_APP_AMEND_TYPES_V (APP_TYPE_CODE, APP_TYPE_DESCRIPTION, COST_LIMIT_CAP, DEVOLVED_POWERS_IND, DEFAULT_LAR_SCOPE_FLAG) " +
                    "VALUES ('DP', 'Del. Functions', '1350.00', 'Y', 'Y');",
            "INSERT INTO XXCCMS_APP_AMEND_TYPES_V (APP_TYPE_CODE, APP_TYPE_DESCRIPTION, COST_LIMIT_CAP, DEVOLVED_POWERS_IND, DEFAULT_LAR_SCOPE_FLAG) " +
                    "VALUES ('ECF', 'ECF', NULL, NULL, 'Y');",
            "INSERT INTO XXCCMS_APP_AMEND_TYPES_V (APP_TYPE_CODE, APP_TYPE_DESCRIPTION, COST_LIMIT_CAP, DEVOLVED_POWERS_IND, DEFAULT_LAR_SCOPE_FLAG) " +
                    "VALUES ('EMER', 'Emergency', '1350.00', NULL, 'Y');",
            "INSERT INTO XXCCMS_APP_AMEND_TYPES_V (APP_TYPE_CODE, APP_TYPE_DESCRIPTION, COST_LIMIT_CAP, DEVOLVED_POWERS_IND, DEFAULT_LAR_SCOPE_FLAG) " +
                    "VALUES ('SUB', 'Substantive', NULL, NULL, 'Y');",
            "INSERT INTO XXCCMS_APP_AMEND_TYPES_V (APP_TYPE_CODE, APP_TYPE_DESCRIPTION, COST_LIMIT_CAP, DEVOLVED_POWERS_IND, DEFAULT_LAR_SCOPE_FLAG) " +
                    "VALUES ('SUBDP', 'Substantive with Delegated Function', NULL, 'Y', 'Y');"
    })
    @CsvSource({"DP, Del. Functions",
                "ECF, ECF",
                "EMER, Emergency",
                "SUB, Substantive",
                "SUBDP, Substantive with Delegated Function"})
    public void testFindAllWithExample(String code, String expectedDescription) {
        // Create an example with desired filters
        AmendmentTypeLookupValue example = new AmendmentTypeLookupValue();
        example.setApplicationTypeCode(code);

        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        Page<AmendmentTypeLookupValue> result =
                amendmentTypeLookupValueRepository.findAll(Example.of(example), pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(expectedDescription, result.getContent().get(0).getApplicationTypeDescription());
    }



}
