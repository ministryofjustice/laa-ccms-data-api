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
import uk.gov.laa.ccms.data.OracleIntegrationTestInterface;
import uk.gov.laa.ccms.data.model.AssessmentSummaryEntityLookupDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CategoryOfLawLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.EvidenceDocumentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupDetail;
import uk.gov.laa.ccms.data.model.ProviderRequestTypeLookupDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupDetail;


@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase=BEFORE_TEST_METHOD,scripts="/sql/lookup_create_schema.sql" )
@Sql(executionPhase=AFTER_TEST_METHOD,scripts="/sql/lookup_drop_schema.sql")
public class LookupServiceIntegrationTest implements OracleIntegrationTestInterface {

    @Autowired
    private LookupService lookupService;

    @ParameterizedTest
    @Sql(statements = {
            "INSERT INTO XXCCMS.XXCCMS_APP_AMEND_TYPES_V (APP_TYPE_CODE, APP_TYPE_DESCRIPTION, COST_LIMIT_CAP, DEVOLVED_POWERS_IND, DEFAULT_LAR_SCOPE_FLAG) " +
                    "VALUES ('DP', 'Del. Functions', '1350.00', 'Y', 'Y');",
            "INSERT INTO XXCCMS.XXCCMS_APP_AMEND_TYPES_V (APP_TYPE_CODE, APP_TYPE_DESCRIPTION, COST_LIMIT_CAP, DEVOLVED_POWERS_IND, DEFAULT_LAR_SCOPE_FLAG) " +
                    "VALUES ('ECF', 'ECF', NULL, NULL, 'Y');",
            "INSERT INTO XXCCMS.XXCCMS_APP_AMEND_TYPES_V (APP_TYPE_CODE, APP_TYPE_DESCRIPTION, COST_LIMIT_CAP, DEVOLVED_POWERS_IND, DEFAULT_LAR_SCOPE_FLAG) " +
                    "VALUES ('EMER', 'Emergency', '1350.00', NULL, 'Y');",
            "INSERT INTO XXCCMS.XXCCMS_APP_AMEND_TYPES_V (APP_TYPE_CODE, APP_TYPE_DESCRIPTION, COST_LIMIT_CAP, DEVOLVED_POWERS_IND, DEFAULT_LAR_SCOPE_FLAG) " +
                    "VALUES ('SUB', 'Substantive', NULL, NULL, 'Y');",
            "INSERT INTO XXCCMS.XXCCMS_APP_AMEND_TYPES_V (APP_TYPE_CODE, APP_TYPE_DESCRIPTION, COST_LIMIT_CAP, DEVOLVED_POWERS_IND, DEFAULT_LAR_SCOPE_FLAG) " +
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
        assertEquals(expectedDescription, result.getContent().getFirst().getApplicationTypeDescription());
    }

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_APP_CASE_STATUS_V (STATUS_CODE, STATUS_DESCRIPTION, COPY_ALLOWED_IND) " +
            "VALUES ('S1', 'Status One', 'N');",
        "INSERT INTO XXCCMS.XXCCMS_APP_CASE_STATUS_V (STATUS_CODE, STATUS_DESCRIPTION, COPY_ALLOWED_IND) " +
            "VALUES ('S2', 'Status Two', 'Y');",
        "INSERT INTO XXCCMS.XXCCMS_APP_CASE_STATUS_V (STATUS_CODE, STATUS_DESCRIPTION, COPY_ALLOWED_IND) " +
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
        "INSERT INTO XXCCMS.XXCCMS_COMMON_LOV_V (LOV_TYPE, CODE, DESCRIPTION, START_DATE_ACTIVE) " +
            "VALUES ('type1', 'code1', 'Description 1', CURRENT_TIMESTAMP)",
        "INSERT INTO XXCCMS.XXCCMS_COMMON_LOV_V (LOV_TYPE, CODE, DESCRIPTION, START_DATE_ACTIVE) " +
            "VALUES ('type1', 'code2', 'Description 2', CURRENT_TIMESTAMP)",
        // Add more INSERT statements as needed
    })
    @CsvSource(value= {
        "type1, null, null, 2",
        "type2, null, null, 0",
        "null, code1, null, 1",
        "null, null, Description 1, 1",
        "type1, *ode*, null, 2",
        "type*, *ode*, , 2",
        "null, null, Desc*, 2",
    }, nullValues={"null"})
    public void testGetCommonValues(String type, String code, String desc,
        Integer expectedElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        CommonLookupDetail result = lookupService.getCommonLookupValues(
            type, code, desc, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_COUNTRY_V (CODE, DESCRIPTION) " +
            "VALUES ('code1', 'Description 1')",
        "INSERT INTO XXCCMS.XXCCMS_COUNTRY_V (CODE, DESCRIPTION) " +
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

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_OUTCOME_RESULTS_V (PROCEEDING_CODE, OUTCOME_RESULT, "
            + "OUTCOME_RESULT_DESCRIPTION, OUTCOME_RESULT_LOV, ENABLED_FLAG) " +
            "VALUES ('code1', 'Result 1', 'Desc 1', 'Lov 1', 'Y')",
        "INSERT INTO XXCCMS.XXCCMS_OUTCOME_RESULTS_V (PROCEEDING_CODE, OUTCOME_RESULT, "
            + "OUTCOME_RESULT_DESCRIPTION, OUTCOME_RESULT_LOV, ENABLED_FLAG) " +
            "VALUES ('code1', 'Result 2', 'Desc 2', 'Lov 2', 'N')",
    })
    @CsvSource(value= {
        "code1, null, 2",
        "code1, Result 2, 1",
        "null, null, 2"},
        nullValues={"null"})
    public void testGetOutcomeResults(String code, String results, Integer expectedElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        OutcomeResultLookupDetail result = lookupService.getOutcomeResultLookupValues(
            code, results, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_STAGE_END_V (PROCEEDING_CODE, STAGE_END, "
            + "STAGE_END_DESCRIPTION, STAGE_END_LOV, ENABLED_FLAG) " +
            "VALUES ('code1', 'StageEnd 1', 'Desc 1', 'Lov 1', 'Y')",
        "INSERT INTO XXCCMS.XXCCMS_STAGE_END_V (PROCEEDING_CODE, STAGE_END, "
            + "STAGE_END_DESCRIPTION, STAGE_END_LOV, ENABLED_FLAG) " +
            "VALUES ('code1', 'StageEnd 2', 'Desc 2', 'Lov 2', 'N')",
    })
    @CsvSource(value= {
        "code1, null, 2",
        "code1, StageEnd 2, 1",
        "null, null, 2"},
        nullValues={"null"})
    public void testGetStageEnds(String code, String stageEnd, Integer expectedElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        StageEndLookupDetail result = lookupService.getStageEndLookupValues(
            code, stageEnd, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_PER_RELTOCASE_V (CODE, DESCRIPTION, DEFAULT_CODE, OPPONENT_IND, DOB_MANDATORY, COPY_PARTY) " +
            "VALUES ('REL1', 'Relationship 1', 'Y', 'N', 'N', 'Y');",
        "INSERT INTO XXCCMS.XXCCMS_PER_RELTOCASE_V (CODE, DESCRIPTION, DEFAULT_CODE, OPPONENT_IND, DOB_MANDATORY, COPY_PARTY) " +
            "VALUES ('REL2', 'Relationship 2', 'N', 'Y', 'Y', 'N');",
        "INSERT INTO XXCCMS.XXCCMS_PER_RELTOCASE_V (CODE, DESCRIPTION, DEFAULT_CODE, OPPONENT_IND, DOB_MANDATORY, COPY_PARTY) " +
            "VALUES ('REL3', 'Relationship 3', 'N', 'N', 'N', 'N');"
    })
    @CsvSource(value = {
        "REL1, Relationship 1, 1",
        "REL2, Relationship 2, 1",
        "REL3, Relationship 3, 1",
        "REL4, null, 0",
        "null, null, 3"},
        nullValues={"null"})
    public void testGetPersonRelationshipsToCase(String code, String description, Integer expectedElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        RelationshipToCaseLookupDetail result = lookupService.getPersonToCaseRelationshipLookupValues(
            code, description, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_ORG_RELTOCASE_V (CODE, DESCRIPTION, DEFAULT_CODE, OPPONENT_IND, COPY_PARTY) " +
            "VALUES ('REL1', 'Relationship 1', 'Y', 'N', 'Y');",
        "INSERT INTO XXCCMS.XXCCMS_ORG_RELTOCASE_V (CODE, DESCRIPTION, DEFAULT_CODE, OPPONENT_IND, COPY_PARTY) " +
            "VALUES ('REL2', 'Relationship 2', 'N', 'Y', 'N');",
        "INSERT INTO XXCCMS.XXCCMS_ORG_RELTOCASE_V (CODE, DESCRIPTION, DEFAULT_CODE, OPPONENT_IND, COPY_PARTY) " +
            "VALUES ('REL3', 'Relationship 3', 'N', 'N', 'N');"
    })
    @CsvSource(value = {
        "REL1, Relationship 1, 1",
        "REL2, Relationship 2, 1",
        "REL3, Relationship 3, 1",
        "REL4, null, 0",
        "null, null, 3"},
        nullValues={"null"})
    public void testGetOrganisationRelationshipsToCase(String code, String description, Integer expectedElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        RelationshipToCaseLookupDetail result = lookupService.getOrganisationToCaseRelationshipLookupValues(
            code, description, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }


    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_AWARD_TYPE_V (CODE, DESCRIPTION, AWARD_TYPE, "
            + "START_DATE_ACTIVE, END_DATE_ACTIVE, ENABLED_FLAG) " +
            "VALUES ('COST', 'AwardType 1', 'AWARD1', TO_DATE('1900-01-01', 'YYYY-MM-DD'), null, 'Y')",
        "INSERT INTO XXCCMS.XXCCMS_AWARD_TYPE_V (CODE, DESCRIPTION, AWARD_TYPE, "
            + "START_DATE_ACTIVE, END_DATE_ACTIVE, ENABLED_FLAG) " +
            "VALUES ('COST_AGR', 'AwardType 2', 'AWARD1', TO_DATE('1900-01-01', 'YYYY-MM-DD'), null, 'Y')",
        "INSERT INTO XXCCMS.XXCCMS_AWARD_TYPE_V (CODE, DESCRIPTION, AWARD_TYPE, "
            + "START_DATE_ACTIVE, END_DATE_ACTIVE, ENABLED_FLAG) " +
            "VALUES ('DAMAGE', 'AwardType 3', 'AWARD2', TO_DATE('1900-01-01', 'YYYY-MM-DD'), null, 'Y')",
    })
    @CsvSource(value= {
        "COST, null, 1",
        "null, AWARD1, 2",
        "null, null, 3"},
        nullValues={"null"})
    public void testGetAwardTypes(String code, String awardType, Integer expectedElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        AwardTypeLookupDetail result = lookupService.getAwardTypeLookupValues(
            code, awardType, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_CATEGORY_OF_LAW_V (CATEGORY_OF_LAW_CODE, MATTER_TYPE_DESCRIPTION, COPY_COST_LIMIT_IND) " +
            "VALUES ('CAT1', 'Mat 1', 'Y')",
        "INSERT INTO XXCCMS.XXCCMS_CATEGORY_OF_LAW_V (CATEGORY_OF_LAW_CODE, MATTER_TYPE_DESCRIPTION, COPY_COST_LIMIT_IND) " +
            "VALUES ('CAT2', 'Mat 1', 'N')",
        "INSERT INTO XXCCMS.XXCCMS_CATEGORY_OF_LAW_V (CATEGORY_OF_LAW_CODE, MATTER_TYPE_DESCRIPTION, COPY_COST_LIMIT_IND) " +
            "VALUES ('CAT3', 'Mat 2', 'Y')",
    })
    @CsvSource(value= {
        "CAT1, null, null, 1",
        "null, Mat 1, null, 2",
        "null, null, false, 1"},
        nullValues={"null"})
    public void testGetCategoriesOfLaw(String code, String desc, Boolean copyCostLimit, Integer expectedElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        CategoryOfLawLookupDetail result = lookupService.getCategoryOfLawLookupValues(
            code, desc, copyCostLimit, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_EVIDENCE_DOC_TYPE_V (LOV_TYPE, CODE, DESCRIPTION) " +
            "VALUES ('TYPE1', 'CODE1', 'description 1')",
        "INSERT INTO XXCCMS.XXCCMS_EVIDENCE_DOC_TYPE_V (LOV_TYPE, CODE, DESCRIPTION) " +
            "VALUES ('TYPE1', 'CODE2', 'description 2')",
        "INSERT INTO XXCCMS.XXCCMS_EVIDENCE_DOC_TYPE_V (LOV_TYPE, CODE, DESCRIPTION) " +
            "VALUES ('TYPE2', 'CODE2', 'description 3')",
    })
    @CsvSource(value= {
        "TYPE1, null, 2",
        "TYPE1, CODE2, 1",
        "null, CODE2, 2"},
        nullValues={"null"})
    public void testGetEvidenceDocumentTypes(String type, String code, Integer expectedElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the service method
        EvidenceDocumentTypeLookupDetail result = lookupService.getEvidenceDocumentTypeLookupValues(
            type, code, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }

    @ParameterizedTest
    @Sql(scripts = {"/sql/insert_opa_lookup_data.sql"})
    @CsvSource(value= {
        "PARENT, 1",
        "CHILD, 2",
        "UNKNOWN, 3",
        "null, 3"},
        nullValues={"null"})
    public void testGetAssessmentSummaryAttributes(String summaryType, Integer expectedElements) {

        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the service method
        AssessmentSummaryEntityLookupDetail result = lookupService.getAssessmentSummaryAttributes(
            summaryType, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedElements, result.getTotalElements());
    }

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_PROVIDER_REQTYPES_V (REQUEST_TYPE, REQUEST_NAME, CASE_RELATED_FLAG, ADDITIONAL_INFO_PROMPT, TASK_TYPE_ID, FILE_UPLD_ENABLED, ACCESS_FUNC_CODE, FILE_UPLD_PROMPT) " +
            "VALUES ('TYPE1', 'Provider Type 1', 'Y', 'Prompt 1', '101', 'Y', 'ACC1', 'Upload Prompt 1');",
        "INSERT INTO XXCCMS.XXCCMS_PROVIDER_REQTYPES_V (REQUEST_TYPE, REQUEST_NAME, CASE_RELATED_FLAG, ADDITIONAL_INFO_PROMPT, TASK_TYPE_ID, FILE_UPLD_ENABLED, ACCESS_FUNC_CODE, FILE_UPLD_PROMPT) " +
            "VALUES ('TYPE2', 'Provider Type 2', 'N', 'Prompt 2', '102', 'N', 'ACC2', 'Upload Prompt 2');"
    })
    @CsvSource({
        "true, 1",
        "false, 1"
    })
    public void testGetProviderRequestTypeLookupValues(Boolean isCaseRelated, Integer expectedResults) {
        Pageable pageable = PageRequest.of(0, 10);

        ProviderRequestTypeLookupDetail result = lookupService.getProviderRequestTypeLookupValues(isCaseRelated, null, pageable);

        assertNotNull(result);
        assertEquals(expectedResults, result.getTotalElements());
    }

    @ParameterizedTest
    @Sql(statements = {
        "INSERT INTO XXCCMS.XXCCMS_PROVIDER_REQTYPES_V (REQUEST_TYPE, REQUEST_NAME, CASE_RELATED_FLAG, ADDITIONAL_INFO_PROMPT, TASK_TYPE_ID, FILE_UPLD_ENABLED, ACCESS_FUNC_CODE, FILE_UPLD_PROMPT) " +
            "VALUES ('TYPE1', 'Provider Type 1', 'Y', 'Prompt 1', '101', 'Y', 'ACC1', 'Upload Prompt 1');",
        "INSERT INTO XXCCMS.XXCCMS_PROVIDER_REQTYPES_V (REQUEST_TYPE, REQUEST_NAME, CASE_RELATED_FLAG, ADDITIONAL_INFO_PROMPT, TASK_TYPE_ID, FILE_UPLD_ENABLED, ACCESS_FUNC_CODE, FILE_UPLD_PROMPT) " +
            "VALUES ('TYPE2', 'Provider Type 2', 'N', 'Prompt 2', '102', 'N', 'ACC2', 'Upload Prompt 2');"
    })
    @CsvSource({
        "TYPE1, Provider Type 1",
        "TYPE2, Provider Type 2"
    })
    public void testGetProviderRequestTypeLookupValuesByType(String type, String expectedName) {
        Pageable pageable = PageRequest.of(0, 10);

        ProviderRequestTypeLookupDetail
            result = lookupService.getProviderRequestTypeLookupValues(null, type, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(expectedName, result.getContent().getFirst().getName());
    }




}
