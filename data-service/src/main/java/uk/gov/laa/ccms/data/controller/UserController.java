package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.UsersApi;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.model.UserDetails;
import uk.gov.laa.ccms.data.service.UserService;

/**
 * Controller responsible for user-related operations.
 *
 * <p>This controller serves as an interface to manage user data and actions. It delegates the
 * business logic to the {@link UserService}
 *
 * <p>It implements the {@link UsersApi} interface, which could be an API definition, presumably
 * from a Swagger or OpenAPI specification or some other contract definition.
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
  public ResponseEntity<UserDetail> lookupUsers(String loginId) {
    return userService
        .getUserByLoginId(loginId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Retrieves a user by their login ID.
   *
   * @param userId the user ID of the user
   * @return ResponseEntity with the UserDetails if found, or ResponseEntity.notFound() if not found
   */
  @Override
  public ResponseEntity<UserDetail> getUser(Integer userId) {
    return userService
        .getByUserId(userId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Retrieves a Page of Users by their related providerId.
   *
   * @param providerId the related provider ID of the user
   * @param loginId the optional login ID of the user
   * @return ResponseEntity containing a UserDetails.
   */
  @Override
  public ResponseEntity<UserDetails> getUsers(
      Integer providerId, String loginId, Pageable pageable) {
    return ResponseEntity.ok(userService.getUsers(providerId, loginId, pageable));
  }
}
