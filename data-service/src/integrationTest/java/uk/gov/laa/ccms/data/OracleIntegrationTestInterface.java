package uk.gov.laa.ccms.data;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext
public interface OracleIntegrationTestInterface {

  OracleContainerSingleton oracleContainerSingleton = OracleContainerSingleton.getInstance();

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    OracleContainer oracleContainer = oracleContainerSingleton.getOracleContainer();
    registry.add("spring.datasource.url", oracleContainer::getJdbcUrl);
    registry.add("spring.datasource.username", oracleContainer::getUsername);
    registry.add("spring.datasource.password", oracleContainer::getPassword);
  }
}

