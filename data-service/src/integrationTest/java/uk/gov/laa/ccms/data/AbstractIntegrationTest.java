package uk.gov.laa.ccms.data;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Base class for integration tests, providing setup for a test Oracle database.
 * This class is configured to spin up an Oracle container with a random port
 * for the purpose of integration testing. The container is initialized with
 * the specified Oracle image and the connection details are dynamically
 * registered for use within the Spring environment.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext
public class AbstractIntegrationTest {

  /**
   * Represents the Oracle database container.
   */
  public static OracleContainer oracleDB;

  static {
    oracleDB = new OracleContainer("gvenzl/oracle-xe:21-slim");
    oracleDB.start();
  }

  /**
   * Dynamically registers properties for the Spring environment.
   * Specifically, it registers the database URL, username, and password.
   *
   * @param registry The dynamic property registry used to add properties.
   */
  @DynamicPropertySource
  public static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", oracleDB::getJdbcUrl);
    registry.add("spring.datasource.username", oracleDB::getUsername);
    registry.add("spring.datasource.password", oracleDB::getPassword);
  }

}