package uk.gov.laa.ccms.data.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.mapper.LookupMapper;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.service.LookupService;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LookupControllerTest {

    @Mock
    private LookupService lookupService;

    @Mock
    private LookupMapper lookupMapper;

    @InjectMocks
    private LookupController lookupController;

    @Test
    void getCommonValues_returnsCommonValuesList() {
        String type = "type";
        String code = "code";
        Pageable pageable = Pageable.unpaged();
        CommonLookupValue example = new CommonLookupValue();
        example.setType(type);
        example.setCode(code);

        Page<CommonLookupValue> expectedPage = new PageImpl<>(Collections.singletonList(new CommonLookupValue()));
        CommonLookupDetail expectedResponse = new CommonLookupDetail();

        when(lookupService.getCommonLookupValues(Example.of(example), pageable)).thenReturn(expectedPage);
        when(lookupMapper.toCommonLookupDetail(expectedPage)).thenReturn(expectedResponse);

        ResponseEntity<CommonLookupDetail> responseEntity = lookupController.getCommonLookupValues(type, code, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void getCaseStatusValues_returnsCaseStatusValuesList() {
        String code = "code";
        Pageable pageable = Pageable.unpaged();
        CaseStatusLookupValue example = new CaseStatusLookupValue();
        example.setCode(code);
        example.setCopyAllowed(Boolean.TRUE);

        Page<CaseStatusLookupValue> expectedPage =
            new PageImpl<>(Collections.singletonList(new CaseStatusLookupValue()));
        CaseStatusLookupDetail expectedResponse = new CaseStatusLookupDetail();

        when(lookupService.getCaseStatusLookupValues(Example.of(example), pageable)).thenReturn(expectedPage);
        when(lookupMapper.toCaseStatusLookupDetail(expectedPage)).thenReturn(expectedResponse);

        ResponseEntity<CaseStatusLookupDetail> responseEntity =
            lookupController.getCaseStatusLookupValues(code, Boolean.TRUE, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}