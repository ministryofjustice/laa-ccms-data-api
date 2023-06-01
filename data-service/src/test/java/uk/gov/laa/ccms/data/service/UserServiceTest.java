package uk.gov.laa.ccms.data.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUser_returnsUser() {
        String userId = "test";
        User expectedUser = new User();
        expectedUser.setUserId(12345);
        expectedUser.setLoginId(userId);
        expectedUser.setUsername(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        Optional<User> actualUser = userService.getUser(userId);

        assertEquals(Optional.of(expectedUser), actualUser);
    }

    @Test
    void getUser_returnsEmptyOptional() {
        String userId = "test";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> actualUser = userService.getUser(userId);

        assertEquals(Optional.empty(), actualUser);
    }
}