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
import uk.gov.laa.ccms.data.model.PriorAuthorityTypeDetails;
import uk.gov.laa.ccms.data.service.PriorAuthorityService;

@ExtendWith(MockitoExtension.class)
class PriorAuthorityControllerTest {

    @Mock
    private PriorAuthorityService priorAuthorityService;

    @InjectMocks
    private PriorAuthorityController priorAuthorityController;

    @Test
    void getPriorAuthorityTypes_returnsData() {
        String code = "code";
        Boolean valueRequired = Boolean.TRUE;
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        PriorAuthorityTypeDetails expectedResponse = new PriorAuthorityTypeDetails();

        when(priorAuthorityService.getPriorAuthorityTypes(code, valueRequired, pageable))
            .thenReturn(expectedResponse);

        ResponseEntity<PriorAuthorityTypeDetails> responseEntity =
            priorAuthorityController.getPriorAuthorityTypes(code, valueRequired, pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
