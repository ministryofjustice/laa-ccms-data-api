package uk.gov.laa.ccms.data.exception;

/** Exception class to handle 400 - bad request scenarios. */
public class BadRequestException extends RuntimeException {

  /**
   * Constructor to set the message for bad request.
   *
   * @param message Message for the bad request scenario.
   */
  public BadRequestException(String message) {
    super(message);
  }
}
