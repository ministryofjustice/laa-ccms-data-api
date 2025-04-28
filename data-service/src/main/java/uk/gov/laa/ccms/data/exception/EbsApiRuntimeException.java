package uk.gov.laa.ccms.data.exception;

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
