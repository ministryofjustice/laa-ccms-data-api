package uk.gov.laa.ccms.data.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.repository.CommonLookupValueRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommonLookupValueServiceTest {

    @Mock
    private CommonLookupValueRepository commonLookupValueRepository;

    @InjectMocks
    private CommonLookupValueService commonLookupValueService;

    @Test
    void getCommonValues_returnsPageOfCommonValues() {
        Example<CommonLookupValue> example = Example.of(new CommonLookupValue());
        Pageable pageable = Pageable.unpaged();
        Page<CommonLookupValue> expectedPage = new PageImpl<>(Collections.singletonList(new CommonLookupValue()));

        when(commonLookupValueRepository.findAll(example, pageable)).thenReturn(expectedPage);

        Page<CommonLookupValue> actualPage = commonLookupValueService.getCommonLookupValues(example, pageable);

        assertEquals(expectedPage, actualPage);
    }
}
