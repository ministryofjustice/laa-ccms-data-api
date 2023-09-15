package uk.gov.laa.ccms.data.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.AbstractIntegrationTest;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;

@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase=BEFORE_TEST_METHOD,scripts="/sql/lookup_create_schema.sql" )
@Sql(executionPhase=AFTER_TEST_METHOD,scripts="/sql/lookup_drop_schema.sql")
public class LookupServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private LookupService lookupService;

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
    public void testGetAmendmentTypes(String code, String expectedDescription) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail result =
                lookupService.getAmendmentTypeLookupValues(code, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(expectedDescription, result.getContent().get(0).getApplicationTypeDescription());
    }

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
    public void testGetCaseStatusValues(Boolean copyAllowed, Integer expectedResults) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        CaseStatusLookupDetail result =
            lookupService.getCaseStatusLookupValues(null, copyAllowed, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedResults, result.getTotalElements());
    }

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
    public void testGetCommonValues(String type, String code, Integer expectedElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        CommonLookupDetail result = lookupService.getCommonLookupValues(type, code, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS_COUNTRY_V (CODE, DESCRIPTION) " +
            "VALUES ('code1', 'Description 1')",
        "INSERT INTO XXCCMS_COUNTRY_V (CODE, DESCRIPTION) " +
            "VALUES ('code2', 'Description 2')",
    })
    @CsvSource(value= {
        "code1, 1",
        "code2, 1",
        "null, 2"},
        nullValues={"null"})
    public void testGetCountries(String code, Integer expectedElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        CommonLookupDetail result = lookupService.getCountryLookupValues(code, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }
}