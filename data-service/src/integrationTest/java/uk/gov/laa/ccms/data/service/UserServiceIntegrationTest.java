package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.OracleIntegrationTestInterface;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.model.UserDetails;


@SpringBootTest
@SqlMergeMode(MERGE)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/providers_create_schema.sql")
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/users_create_schema.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/sql/providers_drop_schema.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/sql/users_drop_schema.sql")
class UserServiceIntegrationTest implements OracleIntegrationTestInterface {

    @Autowired
    private UserService userService;

  @Test
  @Sql(statements = {
      "INSERT INTO XXCCMS.XXCCMS_PROVIDERFIRMS_V (PROVIDERFIRM_ID, PROVIDERFIRM_NAME) " +
          "VALUES (1, 'Firm 1');",
      "INSERT INTO XXCCMS.XXCCMS_PROVIDER_OFFICES_V (PROVIDERFIRM_ID, OFFICE_ID, OFFICE_NAME) " +
          "VALUES (1, 1, 'Main Office');",
      "INSERT INTO XXCCMS.XXCCMS_USER_FIRMS_V (PROVIDERFIRM_ID, PROVIDER_NAME, USER_LOGIN_ID, USER_END_DATE) " +
          "VALUES (1, 'Firm 1', 'user1', CURRENT_TIMESTAMP + INTERVAL '1' DAY);",
      "INSERT INTO XXCCMS.XXCCMS_USERS_V (USER_ID, USER_LOGIN_ID, USER_PARTY_ID, USER_NAME, USER_TYPE, PROVIDER_NAME, PROVIDERFIRM_ID) " +
          "VALUES (1, 'user1', 1001, 'John Doe', 'Admin', 'Firm 1', 1);",
      "INSERT INTO XXCCMS.XXCCMS_USER_ROLES_V (USER_LOGIN_ID, FUNCTION) " +
          "VALUES ('user1', 'Role 1');"
  })
  void getUserReturnsData(){
        Optional<UserDetail> expectedUser = userService.getUser("user1");
        assertTrue(expectedUser.isPresent());
        assertEquals(1, expectedUser.get().getFirms().size());
        assertEquals(1, expectedUser.get().getFunctions().size());
        assertNotNull(expectedUser.get().getProvider());
    }

  @Test
  void getUserNoData(){
        Optional<UserDetail> user = userService.getUser("user1");
        assertFalse(user.isPresent());
    }

  @ParameterizedTest
  @Sql(statements = {
      "INSERT INTO XXCCMS.XXCCMS_PROVIDERFIRMS_V (PROVIDERFIRM_ID, PROVIDERFIRM_NAME) " +
          "VALUES (10, 'ProviderFirm 1');",
      "INSERT INTO XXCCMS.XXCCMS_PROVIDERFIRMS_V (PROVIDERFIRM_ID, PROVIDERFIRM_NAME) " +
          "VALUES (11, 'ProviderFirm 2');",
      "INSERT INTO XXCCMS.XXCCMS_PROVIDER_OFFICES_V (PROVIDERFIRM_ID, OFFICE_ID, OFFICE_NAME) " +
          "VALUES (10, 100, 'Main Office');",
      "INSERT INTO XXCCMS.XXCCMS_USER_FIRMS_V (PROVIDERFIRM_ID, PROVIDER_NAME, USER_LOGIN_ID, USER_END_DATE) " +
          "VALUES (10, 'UserFirm 1', 'user1', CURRENT_TIMESTAMP + INTERVAL '1' DAY);",
      "INSERT INTO XXCCMS.XXCCMS_USER_FIRMS_V (PROVIDERFIRM_ID, PROVIDER_NAME, USER_LOGIN_ID, USER_END_DATE) " +
          "VALUES (11, 'UserFirm 2', 'user1', CURRENT_TIMESTAMP + INTERVAL '1' DAY);",
      "INSERT INTO XXCCMS.XXCCMS_USER_FIRMS_V (PROVIDERFIRM_ID, PROVIDER_NAME, USER_LOGIN_ID, USER_END_DATE) " +
          "VALUES (10, 'UserFirm 1', 'user2', CURRENT_TIMESTAMP + INTERVAL '1' DAY);",
      "INSERT INTO XXCCMS.XXCCMS_USERS_V (USER_ID, USER_LOGIN_ID, USER_PARTY_ID, USER_NAME, USER_TYPE, PROVIDER_NAME, PROVIDERFIRM_ID) " +
          "VALUES (1, 'user1', 1001, 'John Doe', 'Admin', 'ProviderFirm 1', 10);",
      "INSERT INTO XXCCMS.XXCCMS_USERS_V (USER_ID, USER_LOGIN_ID, USER_PARTY_ID, USER_NAME, USER_TYPE, PROVIDER_NAME, PROVIDERFIRM_ID) " +
          "VALUES (2, 'user2', 1001, 'Another User', 'Admin', 'ProviderFirm 1', 10);",
      "INSERT INTO XXCCMS.XXCCMS_USER_ROLES_V (USER_LOGIN_ID, FUNCTION) " +
          "VALUES ('user1', 'Role 1');"
  })
  @CsvSource(value = {
      "10, 2",
      "11, 1",
      "12, 0"})
  void getUsersReturnsData(Integer providerId, int expectedElements){
        UserDetails userDetails = userService.getUsers(providerId, Pageable.ofSize(10).withPage(0));
        assertNotNull(userDetails);
        assertEquals(expectedElements, userDetails.getTotalElements());
    }
}
