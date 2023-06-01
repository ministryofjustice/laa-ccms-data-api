package uk.gov.laa.ccms.data.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.jdbc.JdbcTestUtils;
import uk.gov.laa.ccms.data.AbstractIntegrationTest;
import uk.gov.laa.ccms.data.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.*;


@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase=BEFORE_TEST_METHOD,scripts="/sql/users_create_schema.sql" )
@Sql(executionPhase=AFTER_TEST_METHOD,scripts="/sql/users_drop_schema.sql")
public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql(statements = {
            "INSERT INTO XXCCMS_USER_FIRMS_V (PROVIDERFIRM_ID, PROVIDER_NAME, USER_END_DATE) " +
                    "VALUES (1, 'Firm 1', TO_TIMESTAMP('2023-06-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));",
            "INSERT INTO XXCCMS_USERS_V (USER_ID, USER_LOGIN_ID, USER_PARTY_ID, USER_NAME, USER_TYPE, PROVIDER_NAME, PROVIDERFIRM_ID) " +
                    "VALUES (1, 'user1', 1001, 'John Doe', 'Admin', 'Firm 1', 1);",
            "INSERT INTO XXCCMS_USER_ROLES_V (USER_LOGIN_ID, FUNCTION) " +
                    "VALUES ('user1', 'Role 1');"
    })
    public void testFindById_returnsData(){
        Optional<User> expectedUser = userRepository.findById("user1");
        assertTrue(expectedUser.isPresent());
    }

    @Test
    public void testFindById_noData(){
        Optional<User> user = userRepository.findById("user1");
        assertFalse(user.isPresent());
    }
}
