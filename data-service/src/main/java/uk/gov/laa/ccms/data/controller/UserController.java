/**
 * Controller class for handling user-related API requests.
 */
package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.UsersApi;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.mapper.UserMapper;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.service.UserService;

import java.util.Optional;

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
