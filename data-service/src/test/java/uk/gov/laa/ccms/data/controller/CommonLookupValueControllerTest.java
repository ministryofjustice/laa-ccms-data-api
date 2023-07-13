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
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.mapper.CommonLookupValueMapper;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.service.CommonLookupValueService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommonLookupValueControllerTest {

    @Mock
    private CommonLookupValueService commonLookupValueService;

    @Mock
    private CommonLookupValueMapper commonLookupValueMapper;

    @InjectMocks
    private CommonLookupValueController commonLookupValueController;

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

        when(commonLookupValueService.getCommonLookupValues(Example.of(example), pageable)).thenReturn(expectedPage);
        when(commonLookupValueMapper.toCommonLookupDetail(expectedPage)).thenReturn(expectedResponse);

        ResponseEntity<CommonLookupDetail> responseEntity = commonLookupValueController.getCommonLookupValues(type, code, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
