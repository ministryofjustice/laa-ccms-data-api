package uk.gov.laa.ccms.data.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.laa.ccms.data.model.Notification;
import uk.gov.laa.ccms.data.model.NotificationInfo;
import uk.gov.laa.ccms.data.model.NotificationSummary;
import uk.gov.laa.ccms.data.model.Notifications;
import uk.gov.laa.ccms.data.service.NotificationService;

@ExtendWith({SpringExtension.class})
@ContextConfiguration
@WebAppConfiguration
class NotificationsControllerTest {

  @Mock
  private NotificationService notificationService;

  @InjectMocks
  private NotificationsController notificationsController;

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  private ObjectMapper objectMapper;

  @BeforeEach
  public void setup() {
    mockMvc = standaloneSetup(notificationsController)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .build();
    objectMapper = new ObjectMapper();
  }

  @Test
  @DisplayName("getUserNotificationSummary: Returns data")
  void getUserNotificationSummary_returnsData() throws Exception {
    //Given
    String loginId = "123";
    NotificationSummary expected = new NotificationSummary().notifications(1).standardActions(1)
        .overdueActions(1);
    when(notificationService.getUserNotificationSummary(loginId)).thenReturn(Optional.of(expected));
    // Then
    String jsonContent = objectMapper.writeValueAsString(expected);
    this.mockMvc.perform(get("/users/{loginId}/notifications/summary", loginId))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(jsonContent));
  }

  @Test
  @DisplayName("getUserNotificationSummary: Not found")
  void getUserNotificationSummary_notFound() throws Exception {
    // Given
    String loginId = "123";
    // Then
    this.mockMvc.perform(get("/users/{loginId}/notifications/summary", loginId))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("getNotifications: Returns data")
  void getNotifications_returnsData() throws Exception {
    //Given
    Notifications expected = new Notifications().addContentItem(new NotificationInfo().notificationId("123"));
    when(notificationService.getNotifications(Mockito.eq(123L), Mockito.any(), Mockito.any(), Mockito.any(),
        Mockito.any(), Mockito.any(), Mockito.eq(true), Mockito.any(), Mockito.any(),
        Mockito.any(), Mockito.any())).thenReturn(Optional.of(
        expected));
    // Then
    String jsonContent = objectMapper.writeValueAsString(expected);
    this.mockMvc.perform(get("/notifications?provider-id=123"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(jsonContent));
  }

  @Test
  @DisplayName("getNotifications: Not found")
  void getNotifications_notFound() throws Exception {
    //Given
    // Then
    this.mockMvc.perform(get("/notifications?provider-id=123"))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("getNotifications: Bad request")
  void getNotifications_badRequest() throws Exception {
    //Given
    // Then
    this.mockMvc.perform(get("/notifications"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("getNotification: Returns data")
  void getNotification_returnsData() throws Exception {
    //Given
    Notification expected = new Notification().notificationId("123").providerFirmId("1");
    when(notificationService.getNotification(123L, "2", 1L)).thenReturn(Optional.of(expected));
    // Then
    String jsonContent = objectMapper.writeValueAsString(expected);
    this.mockMvc.perform(get("/notifications/123?user-id=2&provider-id=1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(jsonContent));
  }

  @Test
  @DisplayName("getNotification: Forbidden data")
  void getNotification_forbidden() throws Exception {
    //Given
    when(notificationService.getNotification(123L, "2",1L))
        .thenThrow(new ResponseStatusException(FORBIDDEN, "Access Denied"));
    // Then
    this.mockMvc.perform(get("/notifications/123?user-id=2&provider-id=1"))
        .andDo(print())
        .andExpect(status().isForbidden());
  }


  @Test
  @DisplayName("getNotification: Not found")
  void getNotification_notFound() throws Exception {
    //Given
    // Then
    this.mockMvc.perform(get("/notifications/123?user-id=2&provider-id=1"))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("getNotification: Bad request missing provider ID")
  void getNotification_badRequest() throws Exception {
    //Given
    // Then
    this.mockMvc.perform(get("/notifications/abc?user-id=123"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("getNotification: Bad request missing user ID")
  void getNotification_badRequestMissingUser() throws Exception {
    //Given
    // Then
    this.mockMvc.perform(get("/notifications/abc?provider-id=123"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}