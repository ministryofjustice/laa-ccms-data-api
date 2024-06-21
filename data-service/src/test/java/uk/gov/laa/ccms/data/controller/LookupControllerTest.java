package uk.gov.laa.ccms.data.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AssessmentSummaryAttributeLookupDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CategoryOfLawLookupDetail;
import uk.gov.laa.ccms.data.model.ClientInvolvementTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.EvidenceDocumentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.LevelOfServiceLookupDetail;
import uk.gov.laa.ccms.data.model.MatterTypeLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupDetail;
import uk.gov.laa.ccms.data.service.LookupService;

@ExtendWith(MockitoExtension.class)
class LookupControllerTest {

    @Mock
    private LookupService lookupService;

    @InjectMocks
    private LookupController lookupController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(lookupController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
    }

    @Test
    void getCommonValues_returnsCommonValuesList() {
        String type = "type";
        String code = "code";
        String desc = "desc";
        Pageable pageable = Pageable.unpaged();

        CommonLookupDetail expectedResponse = new CommonLookupDetail();

        when(lookupService.getCommonLookupValues(type, code, desc, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<CommonLookupDetail> responseEntity =
            lookupController.getCommonLookupValues(type, code, desc, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getCaseStatusValues_returnsCaseStatusValuesList() {
        String code = "code";
        Boolean copyAllowed = Boolean.TRUE;
        Pageable pageable = Pageable.unpaged();

        CaseStatusLookupDetail expectedResponse = new CaseStatusLookupDetail();

        when(lookupService.getCaseStatusLookupValues(code, copyAllowed, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<CaseStatusLookupDetail> responseEntity =
            lookupController.getCaseStatusLookupValues(code, copyAllowed, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getAmendmentTypeValues_returnAmendmentTypeValuesList(){
        String code = "code";
        Pageable pageable = Pageable.unpaged();

        AmendmentTypeLookupDetail expectedResponse = new AmendmentTypeLookupDetail();

        when(lookupService.getAmendmentTypeLookupValues(code, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<AmendmentTypeLookupDetail> responseEntity =
                lookupController.getAmendmentTypeLookupValues(code, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getCountriesValues_returnsCommonValuesList() {
        String code = "country_code";
        Pageable pageable = Pageable.unpaged();

        CommonLookupDetail expectedResponse = new CommonLookupDetail();

        when(lookupService.getCountryLookupValues(code, pageable)).thenReturn(expectedResponse);

        ResponseEntity<CommonLookupDetail> responseEntity =
            lookupController.getCountriesLookupValues(code, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getOutcomeResultValues_returnsOutcomeResultValuesList() {
        String code = "code";
        String result = "result";
        Pageable pageable = Pageable.unpaged();

        OutcomeResultLookupDetail expectedResponse = new OutcomeResultLookupDetail();

        when(lookupService.getOutcomeResultLookupValues(code, result, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<OutcomeResultLookupDetail> responseEntity =
            lookupController.getOutcomeResultLookupValues(code, result, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getStageEndValues_returnsStageEndValuesList() {
        String code = "code";
        String stageEnd = "stageEnd";
        Pageable pageable = Pageable.unpaged();

        StageEndLookupDetail expectedResponse = new StageEndLookupDetail();

        when(lookupService.getStageEndLookupValues(code, stageEnd, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<StageEndLookupDetail> responseEntity =
            lookupController.getStageEndLookupValues(code, stageEnd, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getPersonToCaseRelationshipLookupValues() {
        String code = "code";
        String description = "description";
        Pageable pageable = Pageable.unpaged();

        RelationshipToCaseLookupDetail expectedResponse = new RelationshipToCaseLookupDetail();

        when(lookupService.getPersonToCaseRelationshipLookupValues(code, description, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<RelationshipToCaseLookupDetail> responseEntity =
            lookupController.getPersonToCaseRelationshipLookupValues(code, description, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getAwardTypeValues_returnsAwardTypeValuesList() {
        String code = "code";
        String awardType = "awardType";
        Pageable pageable = Pageable.unpaged();

        AwardTypeLookupDetail expectedResponse = new AwardTypeLookupDetail();

        when(lookupService.getAwardTypeLookupValues(code, awardType, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<AwardTypeLookupDetail> responseEntity =
            lookupController.getAwardTypeLookupValues(code, awardType, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getOrganisationToCaseRelationshipLookupValues() {
        String code = "code";
        String description = "description";
        Pageable pageable = Pageable.unpaged();

        RelationshipToCaseLookupDetail expectedResponse = new RelationshipToCaseLookupDetail();

        when(lookupService.getOrganisationToCaseRelationshipLookupValues(code, description, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<RelationshipToCaseLookupDetail> responseEntity =
            lookupController.getOrganisationToCaseRelationshipLookupValues(code, description, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getCategoryOfLawValues_returnsCategoryOfLawValuesList() {
        String code = "code";
        String desc = "desc";
        Boolean copyCostLimit = Boolean.TRUE;

        Pageable pageable = Pageable.unpaged();

        CategoryOfLawLookupDetail expectedResponse = new CategoryOfLawLookupDetail();

        when(lookupService.getCategoryOfLawLookupValues(code, desc, copyCostLimit, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<CategoryOfLawLookupDetail> responseEntity =
            lookupController.getCategoryOfLawLookupValues(code, desc, copyCostLimit, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getLevelOfServiceLookupValues_returnsData() throws Exception {
        String proceedingCode = "proc1";
        String matterType = "mat1";
        String categoryOfLaw = "cat1";
        Pageable pageable = Pageable.ofSize(20);

        when(lookupService.getLevelOfServiceLookupValues(proceedingCode, matterType, categoryOfLaw, pageable))
            .thenReturn(new LevelOfServiceLookupDetail());

        this.mockMvc.perform(get("/lookup/level-of-service")
                .param("proceeding-code", proceedingCode)
                .param("matter-type", matterType)
                .param("category-of-law", categoryOfLaw))
            .andDo(print())
            .andExpect(status().isOk());

        verify(lookupService).getLevelOfServiceLookupValues(proceedingCode, matterType, categoryOfLaw, pageable);
    }

    @Test
    void getMatterTypeLookupValues_returnsData() throws Exception {
        String description = "desc1";
        String matterType = "mat1";
        String categoryOfLaw = "cat1";
        Pageable pageable = Pageable.ofSize(20);

        when(lookupService.getMatterTypeLookupValues(description, matterType, categoryOfLaw, pageable))
            .thenReturn(new MatterTypeLookupDetail());

        mockMvc.perform(get("/lookup/matter-types")
                .param("description", description)
                .param("matter-type", matterType)
                .param("category-of-law", categoryOfLaw))
            .andDo(print())
            .andExpect(status().isOk());

        verify(lookupService).getMatterTypeLookupValues(description, matterType, categoryOfLaw, pageable);
    }

    @Test
    void getProceedingClientInvolvementTypeLookupValues_returnsData() throws Exception {
        String proceedingCode = "proc1";
        String clientInvolvementType = "client1";
        Pageable pageable = Pageable.ofSize(20);

        when(lookupService.getClientInvolvementTypeLookupValues(proceedingCode, clientInvolvementType, pageable))
            .thenReturn(new ClientInvolvementTypeLookupDetail());

        mockMvc.perform(get("/lookup/proceeding-client-involvement-types")
                .param("proceeding-code", proceedingCode)
                .param("client-involvement-type", clientInvolvementType))
            .andDo(print())
            .andExpect(status().isOk());

        verify(lookupService).getClientInvolvementTypeLookupValues(proceedingCode, clientInvolvementType, pageable);
    }

    @Test
    void getEvidenceDocumentTypeLookupValues_returnsData() throws Exception {
        String type = "type1";
        String code = "code1";
        Pageable pageable = Pageable.ofSize(20);

        when(lookupService.getEvidenceDocumentTypeLookupValues(type, code, pageable))
            .thenReturn(new EvidenceDocumentTypeLookupDetail());

        mockMvc.perform(get("/lookup/evidence-document-types")
                .param("type", type)
                .param("code", code))
            .andDo(print())
            .andExpect(status().isOk());

        verify(lookupService).getEvidenceDocumentTypeLookupValues(type, code, pageable);
    }

    @Test
    void getAssessmentSummaryAttributes_returnsData() throws Exception {
        String summaryType = "summaryType";
        Pageable pageable = Pageable.unpaged();

        AssessmentSummaryAttributeLookupDetail expectedResponse = new AssessmentSummaryAttributeLookupDetail();

        when(lookupService.getAssessmentSummaryAttributes(summaryType, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<AssessmentSummaryAttributeLookupDetail> responseEntity =
            lookupController.getAssessmentSummaryAttributes(summaryType, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

}
