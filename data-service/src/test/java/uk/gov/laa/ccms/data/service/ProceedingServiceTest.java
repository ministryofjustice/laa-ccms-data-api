package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.gov.laa.ccms.data.entity.Proceeding;
import uk.gov.laa.ccms.data.mapper.ProceedingMapper;
import uk.gov.laa.ccms.data.model.ProceedingDetail;
import uk.gov.laa.ccms.data.model.ProceedingDetails;
import uk.gov.laa.ccms.data.repository.ProceedingRepository;

@ExtendWith(MockitoExtension.class)
class ProceedingServiceTest {

    @Mock
    private ProceedingRepository proceedingRepository;

    @Mock
    private ProceedingMapper proceedingMapper;

    @InjectMocks
    private ProceedingService proceedingService;

    @Test
    void getProceeding_returnsProceedingDetail() {
        Proceeding proceeding = buildProceeding();

        ProceedingDetail proceedingDetail = new ProceedingDetail();

        when(proceedingRepository.findById(proceeding.getCode()))
            .thenReturn(Optional.of(proceeding));
        when(proceedingMapper.toProceedingDetail(proceeding))
            .thenReturn(proceedingDetail);

        Optional<ProceedingDetail> result = proceedingService.getProceeding(proceeding.getCode());

        verify(proceedingMapper).toProceedingDetail(proceeding);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(proceedingDetail, result.get());
    }

    @Test
    void getProceeding_handlesNotFound() {
        when(proceedingRepository.findById("PROC1")).thenReturn(Optional.empty());

        Optional<ProceedingDetail> result = proceedingService.getProceeding("PROC1");

        verifyNoInteractions(proceedingMapper);

        assertNotNull(result);
        assertFalse(result.isPresent());
    }

    @Test
    void getProceedings_returnsPageOfProceeding() {
        Proceeding proceeding = buildProceeding();
        Example<Proceeding> example = Example.of(proceeding);
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<Proceeding> expectedPage = new PageImpl<>(
            Collections.singletonList(proceeding));
        ProceedingDetails expectedResponse = new ProceedingDetails();

        when(proceedingRepository.findAll(example, pageable)).thenReturn(expectedPage);
        when(proceedingMapper.toProceedingDetails(expectedPage)).thenReturn(expectedResponse);

        ProceedingDetails actualResponse = proceedingService.getProceedings(
            proceeding.getCategoryOfLawCode(), proceeding.getMatterType(),
            proceeding.getAmendmentOnly(), proceeding.getEnabled(), pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "true, Y, true, Y, true, Y",
        "true, Y, false, N, true, Y",
        "false, N, true, Y, false, N",
        "false, N, false, N, false, N",
        "null, null, true, Y, null, null",
        "null, null, false, N, null, null",
        "true, Y, null, null, true, Y",
        "false, N, null, null, false, N",
        "null, null, null, null, null, null"
    }, nullValues = "null")
    void getLeadProceedings_returnsPageOfLeadProceeding(
        Boolean amendmentOnly,
        Character expectedAmendmentOnlyString,
        Boolean enabled,
        Character expectedEnabledString,
        Boolean larScope,
        Character expectedLarScopeString) {
        String categoryOfLaw = "CAT1";
        String matterType = "MAT1";
        String appOrCertType = "APP1";
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        Proceeding proceeding = new Proceeding();
        proceeding.setCategoryOfLawCode(categoryOfLaw);
        proceeding.setMatterType(matterType);
        proceeding.setEnabled(enabled);
        proceeding.setAmendmentOnly(amendmentOnly);

        Page<Proceeding> expectedPage = new PageImpl<>(
            Collections.singletonList(proceeding));
        ProceedingDetails expectedResponse = new ProceedingDetails();

        when(proceedingRepository.findAllLeadProceedings(
            categoryOfLaw,
            matterType,
            expectedAmendmentOnlyString,
            expectedEnabledString,
            expectedLarScopeString,
            appOrCertType,
            pageable)).thenReturn(expectedPage);
        when(proceedingMapper.toProceedingDetails(expectedPage)).thenReturn(expectedResponse);

        ProceedingDetails actualResponse = proceedingService.getLeadProceedings(
            categoryOfLaw, matterType, amendmentOnly, enabled, appOrCertType, larScope, pageable);

        assertEquals(expectedResponse, actualResponse);
    }


    // Helper methods to create objects
    private Proceeding buildProceeding() {
        Proceeding proceeding = new Proceeding();
        proceeding.setCategoryOfLawCode("CAT1");
        proceeding.setMatterType("MAT1");
        proceeding.setEnabled(true);
        proceeding.setAmendmentOnly(Boolean.TRUE);
        return proceeding;
    }
}