package uk.gov.laa.ccms.data.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.laa.ccms.data.entity.CommonValue;
import uk.gov.laa.ccms.data.model.CommonValueListDetails;
import uk.gov.laa.ccms.data.service.CommonValueService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommonValueControllerTest {

    @Mock
    private CommonValueService commonValueService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommonValueController commonValueController;

    @Test
    void getCommonValues_returnsCommonValuesList() {
        String type = "type";
        String code = "code";
        Pageable pageable = Pageable.unpaged();
        CommonValue example = new CommonValue();
        example.setType(type);
        example.setCode(code);

        Page<CommonValue> expectedPage = new PageImpl<>(Collections.singletonList(new CommonValue()));
        CommonValueListDetails expectedResponse = new CommonValueListDetails();

        when(commonValueService.getCommonValues(Example.of(example), pageable)).thenReturn(expectedPage);
        when(modelMapper.map(expectedPage, CommonValueListDetails.class)).thenReturn(expectedResponse);

        ResponseEntity<CommonValueListDetails> responseEntity = commonValueController.getCommonValues(type, code, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
