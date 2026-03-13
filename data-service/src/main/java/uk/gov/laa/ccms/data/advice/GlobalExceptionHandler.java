package uk.gov.laa.ccms.data.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.gov.laa.ccms.data.exception.BadRequestException;
import uk.gov.laa.ccms.data.model.ApiError;

/**
 * Controller advice class responsible for handling exceptions globally and providing appropriate
 * error responses.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * Handles all exceptions tagged as `BadRequestException` globally and responds with error object.
   *
   * @param brException the exception for bad request that was caught
   * @return the response with ApiError with code 400 and respective error message
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ApiError> handleBadRequestException(BadRequestException brException) {

    log.error(
        "Bad request exception caught by GlobalExceptionHandler: {}", brException.getMessage());

    return ResponseEntity.badRequest()
        .body(
            new ApiError().code(HttpStatus.BAD_REQUEST.value()).message(brException.getMessage()));
  }
}
