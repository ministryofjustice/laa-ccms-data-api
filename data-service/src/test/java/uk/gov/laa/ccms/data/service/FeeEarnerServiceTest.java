package uk.gov.laa.ccms.data.service;

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
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.repository.FeeEarnerRepository;

@ExtendWith(MockitoExtension.class)
class FeeEarnerServiceTest {

    @Mock
    private FeeEarnerRepository feeEarnerRepository;

    @InjectMocks
    private FeeEarnerService feeEarnerService;

    @Test
    void getFeeEarners_returnsUser() {
        FeeEarner feeEarner = new FeeEarner();
        feeEarner.setProvider(new Provider());
        feeEarner.getProvider().setId(1);

        Example<FeeEarner> example = Example.of(feeEarner);
        Pageable pageable = Pageable.unpaged();
        Page<FeeEarner> expectedPage = new PageImpl<>(Collections.singletonList(new FeeEarner()));

        when(feeEarnerRepository.findAll(example, pageable)).thenReturn(expectedPage);

        Page<FeeEarner> actualPage = feeEarnerService.getFeeEarners(example, pageable);

        assertEquals(expectedPage, actualPage);
    }
}