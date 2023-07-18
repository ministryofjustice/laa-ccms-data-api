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
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.entity.Provider;


@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase=BEFORE_TEST_METHOD,scripts="/sql/providers_create_schema.sql" )
@Sql(executionPhase=BEFORE_TEST_METHOD,scripts="/sql/fee_earners_create_schema.sql")
@Sql(executionPhase=AFTER_TEST_METHOD,scripts="/sql/providers_drop_schema.sql")
@Sql(executionPhase=AFTER_TEST_METHOD,scripts="/sql/fee_earners_drop_schema.sql")
public class FeeEarnerRepositoryIntegrationTest extends AbstractIntegrationTest {

        @Autowired
        private FeeEarnerRepository feeEarnerRepository;

        @ParameterizedTest
        @Sql(statements = {
                "INSERT INTO XXCCMS_PROVIDERFIRMS_V (PROVIDERFIRM_ID, PROVIDERFIRM_NAME) " +
                        "VALUES (10, 'Firm 1');",
                "INSERT INTO XXCCMS_PROVIDERFIRMS_V (PROVIDERFIRM_ID, PROVIDERFIRM_NAME) " +
                        "VALUES (11, 'Firm 2');",
                "INSERT INTO XXCCMS_FEE_EARNERS_V (CONTACT_ID, CONTACT_NAME, PROVIDERFIRM_ID) " +
                        "VALUES (1, 'fee earner 1', 10)",
                "INSERT INTO XXCCMS_FEE_EARNERS_V (CONTACT_ID, CONTACT_NAME, PROVIDERFIRM_ID) " +
                        "VALUES (2, 'fee earner 2', 10)",
                "INSERT INTO XXCCMS_FEE_EARNERS_V (CONTACT_ID, CONTACT_NAME, PROVIDERFIRM_ID) " +
                        "VALUES (3, 'fee earner 2', 11)",
        })
        @CsvSource({"10, 2",
                "11, 1"})
        public void testFindAllWithExampleAndPageable(Integer providerFirmId, Long expectedElements) {
            // Create an example with desired filters
            FeeEarner example = new FeeEarner();
            example.setProvider(new Provider());
            example.getProvider().setId(providerFirmId);

            // Create a pageable object
            Pageable pageable = PageRequest.of(0, 10);

            // Call the repository method
            Page<FeeEarner> result = feeEarnerRepository.findAll(Example.of(example), pageable);

            // Assert the result
            assertNotNull(result);
            assertEquals(expectedElements, result.getTotalElements());
        }
    }
