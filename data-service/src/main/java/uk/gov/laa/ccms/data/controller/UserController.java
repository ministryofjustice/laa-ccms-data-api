package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.UsersApi;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.service.UserService;

/**
 * Controller responsible for user-related operations.
 *
 * <p>This controller serves as an interface to manage user data and actions. It delegates
 * the business logic to the {@link UserService}</p>
 *
 * <p>It implements the {@link UsersApi} interface, which could be an API definition,
 * presumably from a Swagger or OpenAPI specification or some other contract definition.</p>
 *
 * @see UserService
 */
@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

  private final UserService userService;

  /**
   * Retrieves a user by their login ID.
   *
   * @param loginId the login ID of the user
   * @return ResponseEntity with the UserDetails if found, or ResponseEntity.notFound() if not found
   */
  @Override
  public ResponseEntity<UserDetail> getUser(String loginId) {
    return userService.getUser(loginId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
