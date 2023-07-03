/**
 * Controller class for handling user-related API requests.
 */
package uk.gov.laa.ccms.data.controller;

import org.modelmapper.ModelMapper;
import uk.gov.laa.ccms.data.api.UsersApi;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.model.UserDetails;
import uk.gov.laa.ccms.data.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;
    private final ModelMapper modelMapper;

    /**
     * Retrieves a user by their login ID.
     *
     * @param loginId the login ID of the user
     * @return ResponseEntity with the UserDetails if found, or ResponseEntity.notFound() if not found
     */
    @Override
    public ResponseEntity<UserDetails> getUser(String loginId) {
        Optional<User> user = userService.getUser(loginId);

        if (user.isPresent()) {
            UserDetails response = modelMapper.map(user.get(), UserDetails.class);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
