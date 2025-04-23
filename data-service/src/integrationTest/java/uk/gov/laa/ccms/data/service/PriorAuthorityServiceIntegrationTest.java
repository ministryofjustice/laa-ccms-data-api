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
import uk.gov.laa.ccms.data.model.PriorAuthorityTypeDetails;


@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/prior_authority_create_schema.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/sql/prior_authority_drop_schema.sql")
class PriorAuthorityServiceIntegrationTest implements OracleIntegrationTestInterface {

    @Autowired
    private PriorAuthorityService priorAuthorityService;

  @ParameterizedTest
  @Sql(statements = {
      "INSERT INTO XXCCMS.XXCCMS_PRIOR_AUTHORITY_TYPE_V (CODE, DESCRIPTION, VALUE_REQUIRED_FLAG) " +
          "VALUES ('TYPE1', 'Auth Type 1', 'Y');",
      "INSERT INTO XXCCMS.XXCCMS_PRIOR_AUTHORITY_TYPE_V (CODE, DESCRIPTION, VALUE_REQUIRED_FLAG) " +
          "VALUES ('TYPE2', 'Auth Type 2', 'N');",
      "INSERT INTO XXCCMS.XXCCMS_PRIOR_AUTH_DETAILS_V (CODE, DESCRIPTION, DATA_TYPE, LOV_CODE, MANDATORY_FLAG, PRIOR_AUTHORITY_TYPE_CODE) " +
          "VALUES ('AUTH1', 'Prior Auth 1', 'Data Type 1', 'Lov 1', 'Y', 'TYPE1');",
      "INSERT INTO XXCCMS.XXCCMS_PRIOR_AUTH_DETAILS_V (CODE, DESCRIPTION, DATA_TYPE, LOV_CODE, MANDATORY_FLAG, PRIOR_AUTHORITY_TYPE_CODE) " +
          "VALUES ('AUTH2', 'Prior Auth 2', 'Data Type 2', 'Lov 2', 'N', 'TYPE1');",
    })
  @CsvSource(value = {"TYPE1, null, 2",
      "TYPE1, true, 2",
      "TYPE2, false, 0"}, nullValues = "null")
  void getPriorAuthorityTypes(String code, Boolean valueRequired, Integer expectedSubElements) {
        // Create a pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Call the repository method
        PriorAuthorityTypeDetails result =
                priorAuthorityService.getPriorAuthorityTypes(code, valueRequired, pageable);

        // Assert the result
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(code, result.getContent().getFirst().getCode());
        assertNotNull(result.getContent().getFirst().getPriorAuthorities());
        assertEquals(expectedSubElements, result.getContent().getFirst().getPriorAuthorities().size());
    }
}
