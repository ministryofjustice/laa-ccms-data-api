package uk.gov.laa.ccms.data.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.model.ProviderResponse;
import uk.gov.laa.ccms.data.model.UserResponse;
import uk.gov.laa.ccms.data.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebAppConfiguration
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        mockMvc = standaloneSetup(userController).build();;
    }

    @Test
    public void getUser_isOk() throws Exception{
        String loginId = "test";

        User user = new User();
        user.setUserId(12345);
        user.setLoginId(loginId);
        user.setUsername(loginId);

        Provider provider = new Provider();
        provider.setId(12345);
        provider.setName("test provider");
        user.setProvider(provider);

        List<Firm> firms = new ArrayList<>();
        user.setFirms(firms);

        List<String> functions = new ArrayList<>();
        user.setFunctions(functions);

        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(12345);
        userResponse.setLoginId(loginId);


        when(userService.getUser(loginId)).thenReturn(Optional.of(user));

        when(modelMapper.map(Optional.of(user), UserResponse.class)).thenReturn(userResponse);

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

}