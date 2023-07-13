package uk.gov.laa.ccms.data.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.entity.Office;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.mapper.UserMapper;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.service.UserService;


@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebAppConfiguration
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

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

        User user = new User();
        user.setUserId(12345);
        user.setLoginId(loginId);
        user.setUsername(loginId);

        List<Office> offices = new ArrayList<>();
        Office office = new Office();
        office.setId(1);
        office.setName("Office 1");
        offices.add(office);

        Provider provider = new Provider();
        provider.setId(12345);
        provider.setName("test provider");
        provider.setOffices(offices);
        user.setProvider(provider);

        List<Firm> firms = new ArrayList<>();
        user.setFirms(firms);

        List<String> functions = new ArrayList<>();
        user.setFunctions(functions);

        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(12345);
        userDetail.setLoginId(loginId);


        when(userService.getUser(loginId)).thenReturn(Optional.of(user));

        when(userMapper.toUserDetail(user)).thenReturn(userDetail);

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