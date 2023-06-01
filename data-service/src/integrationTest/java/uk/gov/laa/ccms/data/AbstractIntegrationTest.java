package uk.gov.laa.ccms.data;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext
public class AbstractIntegrationTest {
    public static OracleContainer oracleDB;

    static {
        oracleDB = new OracleContainer("gvenzl/oracle-xe:21-slim");
        oracleDB.start();
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",oracleDB::getJdbcUrl);
        registry.add("spring.datasource.username", oracleDB::getUsername);
        registry.add("spring.datasource.password", oracleDB::getPassword);
    }

}