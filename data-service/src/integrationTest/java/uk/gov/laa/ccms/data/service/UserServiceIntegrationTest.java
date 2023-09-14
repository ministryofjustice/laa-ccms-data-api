package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.AbstractIntegrationTest;
import uk.gov.laa.ccms.data.model.UserDetail;


@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase=BEFORE_TEST_METHOD,scripts="/sql/providers_create_schema.sql" )
@Sql(executionPhase=BEFORE_TEST_METHOD,scripts="/sql/users_create_schema.sql" )
@Sql(executionPhase=AFTER_TEST_METHOD,scripts="/sql/providers_drop_schema.sql")
@Sql(executionPhase=AFTER_TEST_METHOD,scripts="/sql/users_drop_schema.sql")
public class UserServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    @Sql(statements = {
            "INSERT INTO XXCCMS_PROVIDERFIRMS_V (PROVIDERFIRM_ID, PROVIDERFIRM_NAME) " +
                    "VALUES (1, 'Firm 1');",
            "INSERT INTO XXCCMS_PROVIDER_OFFICES_V (PROVIDERFIRM_ID, OFFICE_ID, OFFICE_NAME) " +
                    "VALUES (1, 1, 'Main Office');",
            "INSERT INTO XXCCMS_USER_FIRMS_V (PROVIDERFIRM_ID, PROVIDER_NAME, USER_LOGIN_ID, USER_END_DATE) " +
                    "VALUES (1, 'Firm 1', 'user1', TO_TIMESTAMP('2024-06-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));",
            "INSERT INTO XXCCMS_USERS_V (USER_ID, USER_LOGIN_ID, USER_PARTY_ID, USER_NAME, USER_TYPE, PROVIDER_NAME, PROVIDERFIRM_ID) " +
                    "VALUES (1, 'user1', 1001, 'John Doe', 'Admin', 'Firm 1', 1);",
            "INSERT INTO XXCCMS_USER_ROLES_V (USER_LOGIN_ID, FUNCTION) " +
                    "VALUES ('user1', 'Role 1');"
    })
    public void testGetUser_returnsData(){
        Optional<UserDetail> expectedUser = userService.getUser("user1");
        assertTrue(expectedUser.isPresent());
        assertEquals(1, expectedUser.get().getFirms().size());
        assertEquals(1, expectedUser.get().getFunctions().size());
        assertNotNull(expectedUser.get().getProvider());
    }

    @Test
    public void testGetUser_noData(){
        Optional<UserDetail> user = userService.getUser("user1");
        assertFalse(user.isPresent());
    }
}
