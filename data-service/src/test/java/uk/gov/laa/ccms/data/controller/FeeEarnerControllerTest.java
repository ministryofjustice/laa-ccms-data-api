package uk.gov.laa.ccms.data.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
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
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.mapper.FeeEarnerMapper;
import uk.gov.laa.ccms.data.model.FeeEarnerDetail;
import uk.gov.laa.ccms.data.service.FeeEarnerService;

@ExtendWith(MockitoExtension.class)
class FeeEarnerControllerTest {

    @Mock
    private FeeEarnerService feeEarnerService;

    @Mock
    private FeeEarnerMapper feeEarnerMapper;

    @InjectMocks
    private FeeEarnerController feeEarnerController;

    @Test
    void getFeeEarners_returnsFeeEarnerList() {
        Pageable pageable = Pageable.unpaged();
        FeeEarner example = new FeeEarner();
        example.setProvider(new Provider());
        example.getProvider().setId(1);

        Page<FeeEarner> expectedPage = new PageImpl<>(Collections.singletonList(new FeeEarner()));
        FeeEarnerDetail expectedResponse = new FeeEarnerDetail();

        when(feeEarnerService.getFeeEarners(Example.of(example), pageable)).thenReturn(expectedPage);
        when(feeEarnerMapper.toFeeEarnerDetail(expectedPage)).thenReturn(expectedResponse);

        ResponseEntity<FeeEarnerDetail> responseEntity =
            feeEarnerController.getFeeEarners(example.getProvider().getId(), pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
