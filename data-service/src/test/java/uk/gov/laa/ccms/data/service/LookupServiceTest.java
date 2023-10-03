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
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.mapper.LookupMapper;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.repository.AmendmentTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CaseStatusLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CommonLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CountryLookupValueRepository;

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

    @Mock
    private LookupMapper lookupMapper;

    @InjectMocks
    private LookupService lookupService;

    @Test
    void getCommonValues_returnsPageOfCommonValues() {
        CommonLookupValue commonLookupValue = new CommonLookupValue();
        commonLookupValue.setCode("code");
        commonLookupValue.setType("type");
        commonLookupValue.setDescription("desc");
        Example<CommonLookupValue> example = Example.of(commonLookupValue);
        Pageable pageable = Pageable.unpaged();
        Page<CommonLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(commonLookupValue));
        CommonLookupDetail expectedResponse = new CommonLookupDetail();

        when(commonLookupValueRepository.findAll(example, pageable)).thenReturn(expectedPage);
        when(lookupMapper.toCommonLookupDetail(expectedPage)).thenReturn(expectedResponse);

        CommonLookupDetail actualResponse = lookupService.getCommonLookupValues(
            commonLookupValue.getType(),
            commonLookupValue.getCode(),
            commonLookupValue.getDescription(),
            pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getCommonValuesStartsWithWildcard_createsEndsWithMatcher() {
        CommonLookupValue commonLookupValue = new CommonLookupValue();
        commonLookupValue.setCode("code");
        commonLookupValue.setType("type");
        commonLookupValue.setDescription("*desc");

        CommonLookupValue exampleLookupValue = new CommonLookupValue();
        exampleLookupValue.setCode(commonLookupValue.getCode());
        exampleLookupValue.setType(commonLookupValue.getType());
        exampleLookupValue.setDescription("desc");

        ExampleMatcher wildcardMatcher = ExampleMatcher.matchingAll()
            .withMatcher("description", GenericPropertyMatchers.endsWith());

        Example<CommonLookupValue> wildcardExample = Example.of(exampleLookupValue,
            wildcardMatcher);
        Pageable pageable = Pageable.unpaged();
        Page<CommonLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(commonLookupValue));
        CommonLookupDetail expectedResponse = new CommonLookupDetail();

        when(commonLookupValueRepository.findAll(wildcardExample, pageable))
            .thenReturn(expectedPage);
        when(lookupMapper.toCommonLookupDetail(expectedPage)).thenReturn(expectedResponse);

        CommonLookupDetail actualResponse = lookupService.getCommonLookupValues(
            commonLookupValue.getType(),
            commonLookupValue.getCode(),
            commonLookupValue.getDescription(),
            pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getCommonValuesEndsWithWildcard_returnsStartsWithMatcher() {
        CommonLookupValue commonLookupValue = new CommonLookupValue();
        commonLookupValue.setCode("code");
        commonLookupValue.setType("type");
        commonLookupValue.setDescription("desc*");

        CommonLookupValue exampleLookupValue = new CommonLookupValue();
        exampleLookupValue.setCode(commonLookupValue.getCode());
        exampleLookupValue.setType(commonLookupValue.getType());
        exampleLookupValue.setDescription("desc");

        ExampleMatcher wildcardMatcher = ExampleMatcher.matchingAll()
            .withMatcher("description", GenericPropertyMatchers.startsWith());

        Example<CommonLookupValue> wildcardExample = Example.of(exampleLookupValue,
            wildcardMatcher);
        Pageable pageable = Pageable.unpaged();
        Page<CommonLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(commonLookupValue));
        CommonLookupDetail expectedResponse = new CommonLookupDetail();

        when(commonLookupValueRepository.findAll(wildcardExample, pageable))
            .thenReturn(expectedPage);
        when(lookupMapper.toCommonLookupDetail(expectedPage)).thenReturn(expectedResponse);

        CommonLookupDetail actualResponse = lookupService.getCommonLookupValues(
            commonLookupValue.getType(),
            commonLookupValue.getCode(),
            commonLookupValue.getDescription(),
            pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getCommonValuesBookendWildcard_returnsContainsMatcher() {
        CommonLookupValue commonLookupValue = new CommonLookupValue();
        commonLookupValue.setCode("*code");
        commonLookupValue.setType("type*");
        commonLookupValue.setDescription("*desc*");

        CommonLookupValue exampleLookupValue = new CommonLookupValue();
        exampleLookupValue.setCode("code");
        exampleLookupValue.setType("type");
        exampleLookupValue.setDescription("desc");

        ExampleMatcher wildcardMatcher = ExampleMatcher.matchingAll()
            .withMatcher("code", GenericPropertyMatchers.endsWith())
            .withMatcher("type", GenericPropertyMatchers.startsWith())
            .withMatcher("description", GenericPropertyMatchers.contains());

        Example<CommonLookupValue> wildcardExample = Example.of(exampleLookupValue,
            wildcardMatcher);
        Pageable pageable = Pageable.unpaged();
        Page<CommonLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(commonLookupValue));
        CommonLookupDetail expectedResponse = new CommonLookupDetail();

        when(commonLookupValueRepository.findAll(wildcardExample, pageable))
            .thenReturn(expectedPage);
        when(lookupMapper.toCommonLookupDetail(expectedPage)).thenReturn(expectedResponse);

        CommonLookupDetail actualResponse = lookupService.getCommonLookupValues(
            commonLookupValue.getType(),
            commonLookupValue.getCode(),
            commonLookupValue.getDescription(),
            pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getCaseStatusValues_returnsPageOfCaseStatusValues() {
        CaseStatusLookupValue caseStatusLookupValue = new CaseStatusLookupValue();
        caseStatusLookupValue.setCode("code");
        caseStatusLookupValue.setCopyAllowed(Boolean.TRUE);
        Example<CaseStatusLookupValue> example = Example.of(caseStatusLookupValue);
        Pageable pageable = Pageable.unpaged();
        Page<CaseStatusLookupValue> expectedPage = new PageImpl<>(Collections.singletonList(caseStatusLookupValue));
        CaseStatusLookupDetail expectedResponse = new CaseStatusLookupDetail();

        when(caseStatusLookupValueRepository.findAll(example, pageable)).thenReturn(expectedPage);
        when(lookupMapper.toCaseStatusLookupDetail(expectedPage)).thenReturn(expectedResponse);

        CaseStatusLookupDetail actualResponse = lookupService.getCaseStatusLookupValues(
            caseStatusLookupValue.getCode(), caseStatusLookupValue.getCopyAllowed(), pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getAmendmentTypesValues_returnsPageOfAmendmentTypeValues() {
        AmendmentTypeLookupValue amendmentTypeLookupValue = new AmendmentTypeLookupValue();
        amendmentTypeLookupValue.setApplicationTypeCode("apptype");

        Example<AmendmentTypeLookupValue> example = Example.of(amendmentTypeLookupValue);
        Pageable pageable = Pageable.unpaged();
        Page<AmendmentTypeLookupValue> expectedPage = new PageImpl<>(Collections.singletonList(
            amendmentTypeLookupValue));
        AmendmentTypeLookupDetail expectedResponse = new AmendmentTypeLookupDetail();

        when(amendmentTypeLookupValueRepository.findAll(example, pageable)).thenReturn(
            expectedPage);
        when(lookupMapper.toAmendmentTypeLookupDetail(expectedPage)).thenReturn(expectedResponse);

        AmendmentTypeLookupDetail actualResponse = lookupService.getAmendmentTypeLookupValues(
            amendmentTypeLookupValue.getApplicationTypeCode(), pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getCountryValues_returnsPageOfCountryValues() {
        CountryLookupValue countryLookupValue = new CountryLookupValue();
        countryLookupValue.setCode("code");
        Example<CountryLookupValue> example = Example.of(countryLookupValue);
        Pageable pageable = Pageable.unpaged();
        Page<CountryLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(countryLookupValue));
        CommonLookupDetail expectedResponse = new CommonLookupDetail();

        when(countryLookupValueRepository.findAll(example, pageable)).thenReturn(expectedPage);
        when(lookupMapper.toCommonLookupDetailFromCountries(expectedPage)).thenReturn(
            expectedResponse);

        CommonLookupDetail actualResponse = lookupService.getCountryLookupValues(
            countryLookupValue.getCode(), pageable);

        assertEquals(expectedResponse, actualResponse);
    }
}
