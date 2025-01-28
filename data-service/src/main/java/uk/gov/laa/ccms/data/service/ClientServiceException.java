package uk.gov.laa.ccms.data.service;

/**
 * Custom exception class used to handle errors specific to the ClientService.
 *
 * <p>This exception is thrown when a client-related operation encounters an error
 * such as a user function transaction error.</p>
 *
 * @author Jamie Briggs
 */
public class ClientServiceException extends RuntimeException {
  public ClientServiceException(String message) {
    super(message);
  }
}
