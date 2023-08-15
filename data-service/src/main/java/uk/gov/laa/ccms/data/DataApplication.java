package uk.gov.laa.ccms.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Data Application.
 *
 * <p>This class bootstraps the Spring Boot application, setting up the configuration,
 * and starting the embedded application server. Running the {@code main} method will
 * initiate the application.</p>
 *
 * @see SpringBootApplication
 */
@SpringBootApplication
public class DataApplication {
  public static void main(String[] args) {
    SpringApplication.run(DataApplication.class, args);
  }

}
