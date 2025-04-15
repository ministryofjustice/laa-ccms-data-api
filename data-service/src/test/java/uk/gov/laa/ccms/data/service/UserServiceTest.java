package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.mapper.UserMapper;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.model.UserDetails;
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


    @Test
    void existsUserById_returnsTrue() {
        String loginId = "testLoginId";

        when(userRepository.existsUserByLoginId(loginId)).thenReturn(true);

        boolean result = userService.existsUserById(loginId);

        assertTrue(result);
    }

    @Test
    void getUsers_returnsPageOfValues() {
        Firm firm = new Firm();
        firm.setId(1);

        User user = new User();
        user.setFirms(new ArrayList<>());
        user.getFirms().add(firm);

        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<User> expectedPage = new PageImpl<>(
            Collections.singletonList(user));
        UserDetails expectedResponse = new UserDetails();

        when(userRepository.findByFirmsId(firm.getId(), pageable))
            .thenReturn(expectedPage);
        when(userMapper.toUserDetails(expectedPage)).thenReturn(
            expectedResponse);

        UserDetails actualResponse = userService.getUsers(
            firm.getId(),
            pageable);

        assertEquals(expectedResponse, actualResponse);
    }
}