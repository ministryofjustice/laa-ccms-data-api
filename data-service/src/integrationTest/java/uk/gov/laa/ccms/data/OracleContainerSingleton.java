package uk.gov.laa.ccms.data;

import org.testcontainers.oracle.OracleContainer;

public class OracleContainerSingleton {

  private static OracleContainerSingleton instance;
  private final OracleContainer oracleContainer;

  private OracleContainerSingleton() {
    oracleContainer = new OracleContainer("gvenzl/oracle-free:23-slim-faststart")
         .withUsername("XXCCMS");
    oracleContainer.start();
  }

  public static synchronized OracleContainerSingleton getInstance() {
    if (instance == null) {
      instance = new OracleContainerSingleton();
    }
    return instance;
  }

  public OracleContainer getOracleContainer() {
    return oracleContainer;
  }
}

