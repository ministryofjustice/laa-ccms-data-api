package uk.gov.laa.ccms.data.controller;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserLookupControllerTest {

  @Mock private UserService userService;

  @InjectMocks private UserLookupController userLookupController;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = standaloneSetup(userLookupController).build();
  }

  @Test
  public void getUser_isOk() throws Exception {
    String loginId = "12345";

    UserDetail userDetail = new UserDetail();
    userDetail.setLoginId(loginId);

    when(userService.getUserByLoginId(loginId)).thenReturn(Optional.of(userDetail));

    this.mockMvc
        .perform(get("/user-lookup").param("login-id", loginId))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void getUser_notFound() throws Exception {
    String loginId = "12346";

    when(userService.getUserByLoginId(loginId)).thenReturn(Optional.empty());

    this.mockMvc
        .perform(get("/user-lookup").param("login-id", loginId))
        .andDo(print())
        .andExpect(status().isNotFound());
  }
}
