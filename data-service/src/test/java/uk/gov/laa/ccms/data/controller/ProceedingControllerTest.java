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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.laa.ccms.data.model.ProceedingDetail;
import uk.gov.laa.ccms.data.model.ProceedingDetails;
import uk.gov.laa.ccms.data.service.ProceedingService;

@ExtendWith(MockitoExtension.class)
class ProceedingControllerTest {

    @Mock
    private ProceedingService proceedingService;

    @InjectMocks
    private ProceedingController proceedingController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(proceedingController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
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
    void getProceeding_notFound() throws Exception{
        String code = "proc1";

        when(proceedingService.getProceeding(code)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/proceedings/{proceeding-code}", code))
            .andDo(print())
            .andExpect(status().isNotFound());

        verify(proceedingService).getProceeding(code);
    }

    @Test
    void getProceedings_returnsData() throws Exception {
        String categoryOfLawCode = "cat1";
        String matterType = "mat1";
        Boolean amendOnly = true;
        Boolean enabled = true;
        Boolean larScope = true;
        Pageable pageable = Pageable.ofSize(20);

        when(proceedingService.getProceedings(categoryOfLawCode, matterType, amendOnly,
            enabled, pageable)).thenReturn(new ProceedingDetails());

        this.mockMvc.perform(get("/proceedings")
                .param("category-of-law", categoryOfLawCode)
                .param("matter-type", matterType)
                .param("amendment-only", amendOnly.toString())
                .param("enabled", enabled.toString())
                .param("lar-scope", larScope.toString()))
            .andDo(print())
            .andExpect(status().isOk());

        verify(proceedingService).getProceedings(
            categoryOfLawCode, matterType, amendOnly, enabled, pageable);
    }

    @Test
    void getProceedings_leadProceedings_returnsData() throws Exception {
        String categoryOfLaw = "cat1";
        String matterType = "mat1";
        Boolean amendOnly = true;
        Boolean enabled = true;
        String applicationType = "app1";
        Boolean larScope = true;
        Boolean lead = true;
        Pageable pageable = Pageable.ofSize(20);

        when(proceedingService.getLeadProceedings(categoryOfLaw, matterType, amendOnly,
            enabled, applicationType, larScope, pageable)).thenReturn(new ProceedingDetails());

        this.mockMvc.perform(get("/proceedings")
                .param("category-of-law", categoryOfLaw)
                .param("matter-type", matterType)
                .param("amendment-only", amendOnly.toString())
                .param("enabled", enabled.toString())
                .param("application-type", applicationType)
                .param("lar-scope-flag", larScope.toString())
                .param("lead", lead.toString()))
            .andDo(print())
            .andExpect(status().isOk());

        verify(proceedingService).getLeadProceedings(
            categoryOfLaw, matterType, amendOnly, enabled, applicationType, larScope, pageable);
    }
}
