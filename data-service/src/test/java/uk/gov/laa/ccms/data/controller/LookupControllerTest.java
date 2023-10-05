package uk.gov.laa.ccms.data.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupDetail;
import uk.gov.laa.ccms.data.service.LookupService;

@ExtendWith(MockitoExtension.class)
class LookupControllerTest {

    @Mock
    private LookupService lookupService;

    @InjectMocks
    private LookupController lookupController;

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
}
