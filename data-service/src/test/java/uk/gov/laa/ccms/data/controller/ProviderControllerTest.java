package uk.gov.laa.ccms.data.controller;

import static org.mockito.Mockito.verify;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import uk.gov.laa.ccms.data.model.ProviderDetail;
import uk.gov.laa.ccms.data.service.ProviderService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebAppConfiguration
class ProviderControllerTest {

    @Mock
    private ProviderService providerService;

    @InjectMocks
    private ProviderController providerController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

  @BeforeEach
  void setup() {
        mockMvc = standaloneSetup(providerController).build();
    }

    @Test
    void getProvider_returnsData() throws Exception {
        Integer providerId = 123;

        when(providerService.getProvider(providerId)).thenReturn(Optional.of(new ProviderDetail()));

        this.mockMvc.perform(get("/providers/{providerId}", providerId))
            .andDo(print())
            .andExpect(status().isOk());

        verify(providerService).getProvider(providerId);
    }

    @Test
    void getProvider_notFound() throws Exception{
        Integer providerId = 123;

        when(providerService.getProvider(providerId)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/providers/{providerId}", providerId))
            .andDo(print())
            .andExpect(status().isNotFound());

        verify(providerService).getProvider(providerId);
    }
}
