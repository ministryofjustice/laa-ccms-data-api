package uk.gov.laa.ccms.data.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetails;
import uk.gov.laa.ccms.data.service.ScopeLimitationService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebAppConfiguration
class ScopeLimitationControllerTest {

    @Mock
    private ScopeLimitationService scopeLimitationService;

    @InjectMocks
    private ScopeLimitationController scopeLimitationController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

  @BeforeEach
  void setup() {
        mockMvc = standaloneSetup(scopeLimitationController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
    }

    @Test
    void getScopeLimitations_returnsData() throws Exception {
        uk.gov.laa.ccms.data.model.ScopeLimitationDetail scopeLimitationDetail = new uk.gov.laa.ccms.data.model.ScopeLimitationDetail()
            .scopeLimitations("scopeLimitations")
            .categoryOfLaw("categoryOfLaw")
            .matterType("matterType")
            .proceedingCode("proceedingCode")
            .levelOfService("levelOfService")
            .defaultWording("defaultWording")
            .stage(1)
            .costLimitation(BigDecimal.TEN)
            .emergencyCostLimitation(BigDecimal.TEN)
            .nonStandardWordingRequired(Boolean.TRUE)
            .emergencyScopeDefault(Boolean.TRUE)
            .emergency(Boolean.TRUE)
            .defaultCode(Boolean.TRUE)
            .scopeDefault(Boolean.TRUE);

        Pageable pageable = Pageable.ofSize(20);

        when(scopeLimitationService.getScopeLimitations(scopeLimitationDetail, pageable))
            .thenReturn(new ScopeLimitationDetails());

        this.mockMvc.perform(get("/scope-limitations")
                .param("scope-limitations", scopeLimitationDetail.getScopeLimitations())
                .param("category-of-law", scopeLimitationDetail.getCategoryOfLaw())
                .param("matter-type", scopeLimitationDetail.getMatterType())
                .param("proceeding-code", scopeLimitationDetail.getProceedingCode())
                .param("level-of-service", scopeLimitationDetail.getLevelOfService())
                .param("default-wording", scopeLimitationDetail.getDefaultWording())
                .param("stage", String.valueOf(scopeLimitationDetail.getStage()))
                .param("cost-limitation", scopeLimitationDetail.getCostLimitation().toString())
                .param("emergency-cost-limitation", scopeLimitationDetail.getEmergencyCostLimitation().toString())
                .param("non-standard-wording", scopeLimitationDetail.getNonStandardWordingRequired().toString())
                .param("emergency-scope-default", scopeLimitationDetail.getEmergencyScopeDefault().toString())
                .param("emergency", scopeLimitationDetail.getEmergency().toString())
                .param("default-code", scopeLimitationDetail.getDefaultCode().toString())
                .param("scope-default", scopeLimitationDetail.getScopeDefault().toString()))
            .andDo(print())
            .andExpect(status().isOk());

        verify(scopeLimitationService).getScopeLimitations(scopeLimitationDetail, pageable);
    }
}
