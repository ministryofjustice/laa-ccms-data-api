package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.mapper.UserMapper;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void getUser_returnsUser() {
        String userId = "test";
        User expectedUser = new User();
        expectedUser.setUserId(12345);
        expectedUser.setLoginId(userId);
        expectedUser.setUsername(userId);

        UserDetail expectedResponse = new UserDetail();

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));
        when(userMapper.toUserDetail(expectedUser)).thenReturn(expectedResponse);

        Optional<UserDetail> actualResponse = userService.getUser(userId);

        assertEquals(Optional.of(expectedResponse), actualResponse);
    }

    @Test
    void getUser_returnsEmptyOptional() {
        String userId = "test";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<UserDetail> actualUser = userService.getUser(userId);

        assertEquals(Optional.empty(), actualUser);
    }
}