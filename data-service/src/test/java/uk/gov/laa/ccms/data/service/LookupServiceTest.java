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
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.repository.AmendmentTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CaseStatusLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CommonLookupValueRepository;

import java.util.Collections;
import uk.gov.laa.ccms.data.repository.CountryLookupValueRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LookupServiceTest {

    @Mock
    private CommonLookupValueRepository commonLookupValueRepository;

    @Mock
    private CaseStatusLookupValueRepository caseStatusLookupValueRepository;

    @Mock
    private AmendmentTypeLookupValueRepository amendmentTypeLookupValueRepository;

    @Mock
    private CountryLookupValueRepository countryLookupValueRepository;

    @InjectMocks
    private LookupService lookupService;

    @Test
    void getCommonValues_returnsPageOfCommonValues() {
        Example<CommonLookupValue> example = Example.of(new CommonLookupValue());
        Pageable pageable = Pageable.unpaged();
        Page<CommonLookupValue> expectedPage = new PageImpl<>(Collections.singletonList(new CommonLookupValue()));

        when(commonLookupValueRepository.findAll(example, pageable)).thenReturn(expectedPage);

        Page<CommonLookupValue> actualPage = lookupService.getCommonLookupValues(example, pageable);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void getCaseStatusValues_returnsPageOfCaseStatusValues() {
        Example<CaseStatusLookupValue> example = Example.of(new CaseStatusLookupValue());
        Pageable pageable = Pageable.unpaged();
        Page<CaseStatusLookupValue> expectedPage = new PageImpl<>(Collections.singletonList(new CaseStatusLookupValue()));

        when(caseStatusLookupValueRepository.findAll(example, pageable)).thenReturn(expectedPage);

        Page<CaseStatusLookupValue> actualPage = lookupService.getCaseStatusLookupValues(example, pageable);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void getAmendmentTypesValues_returnsPageOfAmendmentTypeValues() {
        Example<AmendmentTypeLookupValue> example = Example.of(new AmendmentTypeLookupValue());
        Pageable pageable = Pageable.unpaged();
        Page<AmendmentTypeLookupValue> expectedPage = new PageImpl<>(Collections.singletonList(new AmendmentTypeLookupValue()));

        when(amendmentTypeLookupValueRepository.findAll(example, pageable)).thenReturn(expectedPage);

        Page<AmendmentTypeLookupValue> actualPage = lookupService.getAmendmentTypeLookupValues(example, pageable);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void getCountryValues_returnsPageOfCountryValues() {
        Example<CountryLookupValue> example = Example.of(new CountryLookupValue());
        Pageable pageable = Pageable.unpaged();
        Page<CountryLookupValue> expectedPage = new PageImpl<>(Collections.singletonList(new CountryLookupValue()));

        when(countryLookupValueRepository.findAll(example, pageable)).thenReturn(expectedPage);

        Page<CountryLookupValue> actualPage = lookupService.getCountryLookupValues(example, pageable);

        assertEquals(expectedPage, actualPage);
    }
}
