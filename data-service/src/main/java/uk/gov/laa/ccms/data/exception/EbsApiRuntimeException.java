package uk.gov.laa.ccms.data.exception;

/**
 * Represents a custom runtime exception used for handling errors in the EBS API.
 * This class extends {@link RuntimeException}.
 *
 * @author Jamie Briggs
 */
public class EbsApiRuntimeException extends RuntimeException {

  public EbsApiRuntimeException() {
  }

  public EbsApiRuntimeException(String message) {
    super(message);
  }

  public EbsApiRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public EbsApiRuntimeException(Throwable cause) {
    super(cause);
  }

  public EbsApiRuntimeException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
