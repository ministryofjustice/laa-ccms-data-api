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
import uk.gov.laa.ccms.data.entity.CommonValue;
import uk.gov.laa.ccms.data.repository.CommonValueRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommonValueServiceTest {

    @Mock
    private CommonValueRepository commonValueRepository;

    @InjectMocks
    private CommonValueService commonValueService;

    @Test
    void getCommonValues_returnsPageOfCommonValues() {
        Example<CommonValue> example = Example.of(new CommonValue());
        Pageable pageable = Pageable.unpaged();
        Page<CommonValue> expectedPage = new PageImpl<>(Collections.singletonList(new CommonValue()));

        when(commonValueRepository.findAll(example, pageable)).thenReturn(expectedPage);

        Page<CommonValue> actualPage = commonValueService.getCommonValues(example, pageable);

        assertEquals(expectedPage, actualPage);
    }
}
