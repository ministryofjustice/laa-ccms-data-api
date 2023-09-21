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
import uk.gov.laa.ccms.data.model.ProceedingDetail;
import uk.gov.laa.ccms.data.service.ProceedingService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebAppConfiguration
class ProceedingControllerTest {

    @Mock
    private ProceedingService proceedingService;

    @InjectMocks
    private ProceedingController proceedingController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(proceedingController).build();
    }

    @Test
    void getProceeding_returnsData() throws Exception {
        String code = "proc1";

        when(proceedingService.getProceeding(code)).thenReturn(Optional.of(new ProceedingDetail()));

        this.mockMvc.perform(get("/proceedings/{proceeding-code}", code))
            .andDo(print())
            .andExpect(status().isOk());

        verify(proceedingService).getProceeding(code);
    }

    @Test
    void getProvider_notFound() throws Exception{
        String code = "proc1";

        when(proceedingService.getProceeding(code)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/proceedings/{proceeding-code}", code))
            .andDo(print())
            .andExpect(status().isNotFound());

        verify(proceedingService).getProceeding(code);
    }
}
