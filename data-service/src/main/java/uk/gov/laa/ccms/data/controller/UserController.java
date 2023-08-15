package uk.gov.laa.ccms.data.controller;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.UsersApi;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.mapper.UserMapper;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.service.UserService;

/**
 * Controller responsible for user-related operations.
 *
 * <p>This controller serves as an interface to manage user data and actions. It delegates
 * the business logic to the {@link UserService} and maps the data to and from DTOs using the
 * {@link UserMapper}.</p>
 *
 * <p>It implements the {@link UsersApi} interface, which could be an API definition,
 * presumably from a Swagger or OpenAPI specification or some other contract definition.</p>
 *
 * @see UserService
 * @see UserMapper
 */
@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

  private final UserService userService;
  private final UserMapper userMapper;

  /**
   * Retrieves a user by their login ID.
   *
   * @param loginId the login ID of the user
   * @return ResponseEntity with the UserDetails if found, or ResponseEntity.notFound() if not found
   */
  @Override
  public ResponseEntity<UserDetail> getUser(String loginId) {
    Optional<User> user = userService.getUser(loginId);

    if (user.isPresent()) {
      UserDetail response = userMapper.toUserDetail(user.get());
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
