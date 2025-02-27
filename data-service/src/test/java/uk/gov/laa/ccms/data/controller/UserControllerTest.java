package uk.gov.laa.ccms.data.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.model.UserDetails;
import uk.gov.laa.ccms.data.service.UserService;


@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebAppConfiguration
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void getUser_isOk() throws Exception{
        String loginId = "test";

        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(12345);
        userDetail.setLoginId(loginId);


        when(userService.getUser(loginId)).thenReturn(Optional.of(userDetail));

        this.mockMvc.perform(get("/users/{loginId}", loginId))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getUser_notFound() throws Exception{
        String loginId = "test";

        when(userService.getUser(loginId)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/users/{loginId}", loginId))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    void getUsers_returnsData() {
        Integer providerId = 123;
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        UserDetails expectedResponse = new UserDetails();

        when(userService.getUsers(providerId, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<UserDetails> responseEntity =
            userController.getUsers(providerId, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

}