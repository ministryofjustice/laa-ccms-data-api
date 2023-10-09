package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.AwardTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValueId;
import uk.gov.laa.ccms.data.entity.PersonRelationshipToCaseLookupValue;
import uk.gov.laa.ccms.data.entity.StageEndLookupValue;
import uk.gov.laa.ccms.data.entity.StageEndLookupValueId;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupValueDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupValueDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupValueDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupValueDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupValueDetail;

@ExtendWith(MockitoExtension.class)
class LookupMapperImplTest {

    LookupMapperImpl mapper = new LookupMapperImpl();
    
    // Tests
    @Test
    void toCommonLookupValueListDetails() {
        List<CommonLookupValue> commonLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            commonLookupValues.add(createCommonLookupValue(String.valueOf(i)));
        }

        Page<CommonLookupValue> commonLookupValuePage = new PageImpl<>(commonLookupValues);


        CommonLookupDetail expected = new CommonLookupDetail();
        expected.setTotalPages(commonLookupValuePage.getTotalPages());
        expected.setTotalElements((int) commonLookupValuePage.getTotalElements());
        expected.setNumber(commonLookupValuePage.getNumber());
        expected.setSize(commonLookupValuePage.getSize());
        expected.setContent(mapper.commonLookupValueListToCommonLookupValueDetailList(commonLookupValuePage.getContent()));

        CommonLookupDetail actual = mapper.toCommonLookupDetail(commonLookupValuePage);

        assertEquals(expected, actual);
    }

    @Test
    void commonLookupValueToCommonLookupValueDetails() {
        CommonLookupValue commonLookupValue = createCommonLookupValue("");
        CommonLookupValueDetail expectedDetails = createCommonLookupValueDetails(commonLookupValue);
        CommonLookupValueDetail actualDetails = mapper.commonLookupValueToCommonLookupValueDetail(commonLookupValue);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void commonLookupValueListToCommonLookupValueDetailsList() {
        List<CommonLookupValue> commonLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            commonLookupValues.add(createCommonLookupValue(String.valueOf(i)));
        }

        List<CommonLookupValueDetail> expectedDetailsList = new ArrayList<>();
        for (CommonLookupValue commonLookupValue : commonLookupValues) {
            expectedDetailsList.add(createCommonLookupValueDetails(commonLookupValue));
        }

        List<CommonLookupValueDetail> actualDetailsList = mapper.commonLookupValueListToCommonLookupValueDetailList(commonLookupValues);

        assertEquals(expectedDetailsList, actualDetailsList);
    }

    @Test
    void toCaseStatusLookupValueListDetails() {
        List<CaseStatusLookupValue> caseStatusLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            caseStatusLookupValues.add(createCaseStatusLookupValue(String.valueOf(i), Boolean.TRUE));
        }

        Page<CaseStatusLookupValue> caseStatusLookupValuePage = new PageImpl<>(caseStatusLookupValues);

        CaseStatusLookupDetail expected = new CaseStatusLookupDetail();
        expected.setTotalPages(caseStatusLookupValuePage.getTotalPages());
        expected.setTotalElements((int) caseStatusLookupValuePage.getTotalElements());
        expected.setNumber(caseStatusLookupValuePage.getNumber());
        expected.setSize(caseStatusLookupValuePage.getSize());
        expected.setContent(mapper.caseStatusLookupValueListToCaseStatusLookupValueDetailList(
            (caseStatusLookupValuePage.getContent())));

        CaseStatusLookupDetail actual = mapper.toCaseStatusLookupDetail(caseStatusLookupValuePage);

        assertEquals(expected, actual);
    }

    @Test
    void caseStatusLookupValueToCaseStatusLookupValueDetails() {
        CaseStatusLookupValue caseStatusLookupValue = createCaseStatusLookupValue("", Boolean.FALSE);
        CaseStatusLookupValueDetail expectedDetails = createCaseStatusLookupValueDetail(caseStatusLookupValue);
        CaseStatusLookupValueDetail actualDetails =
            mapper.caseStatusLookupValueToCaseStatusLookupValueDetail(caseStatusLookupValue);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void caseStatusLookupValueListToCaseStatusLookupValueDetailsList() {
        List<CaseStatusLookupValue> caseStatusLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            caseStatusLookupValues.add(createCaseStatusLookupValue(String.valueOf(i), Boolean.TRUE));
        }

        List<CaseStatusLookupValueDetail> expectedDetailsList = new ArrayList<>();
        for (CaseStatusLookupValue caseStatusLookupValue : caseStatusLookupValues) {
            expectedDetailsList.add(createCaseStatusLookupValueDetail(caseStatusLookupValue));
        }

        List<CaseStatusLookupValueDetail> actualDetailsList =
            mapper.caseStatusLookupValueListToCaseStatusLookupValueDetailList(caseStatusLookupValues);

        assertEquals(expectedDetailsList, actualDetailsList);
    }

    @Test
    void toAmendmentTypeLookupDetailTest() {
        // Create a list of AmendmentTypeLookupValue
        List<AmendmentTypeLookupValue> amendmentTypeLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            amendmentTypeLookupValues.add(createAmendmentTypeLookupValue(String.valueOf(i)));
        }

        // Create a Page object
        Page<AmendmentTypeLookupValue> amendmentTypeLookupValuePage = new PageImpl<>(amendmentTypeLookupValues);

        // Expected AmendmentTypeLookupDetail
        AmendmentTypeLookupDetail expectedDetail = new AmendmentTypeLookupDetail();
        expectedDetail.setTotalPages(amendmentTypeLookupValuePage.getTotalPages());
        expectedDetail.setTotalElements((int) amendmentTypeLookupValuePage.getTotalElements());
        expectedDetail.setNumber(amendmentTypeLookupValuePage.getNumber());
        expectedDetail.setSize(amendmentTypeLookupValuePage.getSize());
        List<AmendmentTypeLookupValueDetail> expectedContent = new ArrayList<>();
        for (AmendmentTypeLookupValue value : amendmentTypeLookupValues) {
            expectedContent.add(createAmendmentTypeLookupValueDetail(value));
        }
        expectedDetail.setContent(expectedContent);

        // Actual AmendmentTypeLookupDetail
        AmendmentTypeLookupDetail actualDetail = mapper.toAmendmentTypeLookupDetail(amendmentTypeLookupValuePage);

        // Assertion
        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toAmendmentTypeLookupValueDetail() {
        AmendmentTypeLookupValue value = createAmendmentTypeLookupValue("");

        AmendmentTypeLookupValueDetail expectedDetail = createAmendmentTypeLookupValueDetail(value);

        AmendmentTypeLookupValueDetail actualDetail = mapper.toAmendmentTypeLookupValueDetail(value);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toCommonLookupDetailFromCountriesTest() {
        List<CountryLookupValue> countryLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            countryLookupValues.add(createCountryLookupValue(String.valueOf(i)));
        }

        Page<CountryLookupValue> countryLookupValuePage = new PageImpl<>(countryLookupValues);

        CommonLookupDetail expectedDetail = new CommonLookupDetail();
        expectedDetail.setTotalPages(countryLookupValuePage.getTotalPages());
        expectedDetail.setTotalElements((int) countryLookupValuePage.getTotalElements());
        expectedDetail.setNumber(countryLookupValuePage.getNumber());
        expectedDetail.setSize(countryLookupValuePage.getSize());
        List<CommonLookupValueDetail> expectedContent = new ArrayList<>();
        for (CountryLookupValue value : countryLookupValues) {
            expectedContent.add(mapper.toCommonLookupValueDetail(value));
        }
        expectedDetail.setContent(expectedContent);

        CommonLookupDetail actualDetail = mapper.toCommonLookupDetailFromCountries(countryLookupValuePage);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toCommonLookupValueDetailTest() {
        CountryLookupValue value = createCountryLookupValue("");

        CommonLookupValueDetail expectedDetail = mapper.toCommonLookupValueDetail(value);
        CommonLookupValueDetail actualDetail = mapper.toCommonLookupValueDetail(value);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toOutcomeResultLookupValueDetailTest() {
        OutcomeResultLookupValue value = createOutcomeResultLookupValue("1");

        OutcomeResultLookupValueDetail expectedDetail = createOutcomeResultLookupValueDetail(value);
        OutcomeResultLookupValueDetail actualDetail =
            mapper.toOutcomeResultLookupValueDetail(value);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toOutcomeResultLookupDetailTest() {
        List<OutcomeResultLookupValue> outcomeResultLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            outcomeResultLookupValues.add(createOutcomeResultLookupValue(String.valueOf(i)));
        }

        List<OutcomeResultLookupValueDetail> expectedDetailsList = new ArrayList<>();
        for (OutcomeResultLookupValue commonLookupValue : outcomeResultLookupValues) {
            expectedDetailsList.add(createOutcomeResultLookupValueDetail(commonLookupValue));
        }

        List<OutcomeResultLookupValueDetail> actualDetailsList =
            mapper.outcomeResultLookupValueListToOutcomeResultLookupValueDetailList(
                outcomeResultLookupValues);

        assertEquals(expectedDetailsList, actualDetailsList);
    }

    @Test
    void toStageEndLookupValueDetailTest() {
        StageEndLookupValue value = createStageEndLookupValue("1");

        StageEndLookupValueDetail expectedDetail = createStageEndLookupValueDetail(value);
        StageEndLookupValueDetail actualDetail =
            mapper.toStageEndLookupValueDetail(value);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toStageEndLookupDetailTest() {
        List<StageEndLookupValue> stageEndLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            stageEndLookupValues.add(createStageEndLookupValue(String.valueOf(i)));
        }

        List<StageEndLookupValueDetail> expectedDetailsList = new ArrayList<>();
        for (StageEndLookupValue commonLookupValue : stageEndLookupValues) {
            expectedDetailsList.add(createStageEndLookupValueDetail(commonLookupValue));
        }

        List<StageEndLookupValueDetail> actualDetailsList =
            mapper.stageEndLookupValueListToStageEndLookupValueDetailList(
                stageEndLookupValues);

        assertEquals(expectedDetailsList, actualDetailsList);
    }


    @Test
    void toRelationshipToCaseLookupValueDetail() {
        PersonRelationshipToCaseLookupValue value = createPersonRelationshipToCaseLookupvalue("1");

        RelationshipToCaseLookupValueDetail expectedDetail = createRelationshipToCaseLookupValueDetail(value);
        RelationshipToCaseLookupValueDetail actualDetail =
            mapper.toRelationshipToCaseLookupValueDetail(value);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toRelationshipToCaseLookupDetailTest() {
        List<PersonRelationshipToCaseLookupValue> values = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            values.add(createPersonRelationshipToCaseLookupvalue(String.valueOf(i)));
        }

        // Create a Page object
        Page<PersonRelationshipToCaseLookupValue> page = new PageImpl<>(values);
        RelationshipToCaseLookupDetail expectedDetail = new RelationshipToCaseLookupDetail();

        expectedDetail.setTotalPages(page.getTotalPages());
        expectedDetail.setTotalElements((int) page.getTotalElements());
        expectedDetail.setNumber(page.getNumber());
        expectedDetail.setSize(page.getSize());
        List<RelationshipToCaseLookupValueDetail> expectedContent = new ArrayList<>();
        for (PersonRelationshipToCaseLookupValue value : page) {
            expectedContent.add(createRelationshipToCaseLookupValueDetail(value));
        }
        expectedDetail.setContent(expectedContent);

        List<RelationshipToCaseLookupValueDetail> actualDetailsList =
            mapper.personRelationshipToCaseLookupValueListToRelationshipToCaseLookupValueDetailList(values);

        // Actual AmendmentTypeLookupDetail
        RelationshipToCaseLookupDetail actualDetail = mapper.toRelationshipToCaseLookupDetail(page);

        // Assertion
        assertEquals(expectedDetail, actualDetail);
    }





    @Test
    void toAwardTypeLookupValueDetailTest() {
        AwardTypeLookupValue value = createAwardTypeLookupValue("1");

        AwardTypeLookupValueDetail expectedDetail = createAwardTypeLookupValueDetail(value);
        AwardTypeLookupValueDetail actualDetail =
            mapper.toAwardTypeLookupValueDetail(value);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toAwardTypeLookupDetailTest() {
        List<AwardTypeLookupValue> awardTypeLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            awardTypeLookupValues.add(createAwardTypeLookupValue(String.valueOf(i)));
        }

        List<AwardTypeLookupValueDetail> expectedDetailsList = new ArrayList<>();
        for (AwardTypeLookupValue awardTypeLookupValue : awardTypeLookupValues) {
            expectedDetailsList.add(createAwardTypeLookupValueDetail(awardTypeLookupValue));
        }

        List<AwardTypeLookupValueDetail> actualDetailsList =
            mapper.awardTypeLookupValueListToAwardTypeLookupValueDetailList(
                awardTypeLookupValues);

        assertEquals(expectedDetailsList, actualDetailsList);
    }

    // Helper methods to create objects
    private PersonRelationshipToCaseLookupValue createPersonRelationshipToCaseLookupvalue(String suffix) {
        PersonRelationshipToCaseLookupValue personRelationshipToCaseLookupValue = new PersonRelationshipToCaseLookupValue();
        personRelationshipToCaseLookupValue.setCode("code" + suffix);
        personRelationshipToCaseLookupValue.setDescription("description" + suffix);
        return personRelationshipToCaseLookupValue;
    }
    private RelationshipToCaseLookupValueDetail createRelationshipToCaseLookupValueDetail(
        PersonRelationshipToCaseLookupValue personRelationshipToCaseLookupValue) {
        RelationshipToCaseLookupValueDetail detail = new RelationshipToCaseLookupValueDetail();
        detail.setCode(personRelationshipToCaseLookupValue.getCode());
        detail.setDescription(personRelationshipToCaseLookupValue.getDescription());
        return detail;
    }

    private StageEndLookupValue createStageEndLookupValue(String suffix) {
        StageEndLookupValue stageEndLookupValue = new StageEndLookupValue();
        stageEndLookupValue.setId(new StageEndLookupValueId());
        stageEndLookupValue.getId().setProceedingCode("code" + suffix);
        stageEndLookupValue.getId().setStageEnd("stageEnd" + suffix);
        stageEndLookupValue.setDescription("description" + suffix);
        stageEndLookupValue.setStageEndLov("lov" + suffix);
        stageEndLookupValue.setEnabled(Boolean.TRUE);
        return stageEndLookupValue;
    }

    private StageEndLookupValueDetail createStageEndLookupValueDetail(
        StageEndLookupValue stageEndLookupValue) {
        StageEndLookupValueDetail detail = new StageEndLookupValueDetail();
        detail.setProceedingCode(stageEndLookupValue.getId().getProceedingCode());
        detail.setStageEnd(stageEndLookupValue.getId().getStageEnd());
        detail.setDescription(stageEndLookupValue.getDescription());
        detail.setStageEndLov(stageEndLookupValue.getStageEndLov());
        detail.setEnabled(stageEndLookupValue.getEnabled());
        return detail;
    }

    private AwardTypeLookupValue createAwardTypeLookupValue(String suffix) {
        AwardTypeLookupValue awardTypeLookupValue = new AwardTypeLookupValue();
        awardTypeLookupValue.setCode("code" + suffix);
        awardTypeLookupValue.setAwardType("awardType" + suffix);
        awardTypeLookupValue.setDescription("description" + suffix);
        awardTypeLookupValue.setStartDateActive(LocalDateTime.now());
        awardTypeLookupValue.setEndDateActive(LocalDateTime.now());
        awardTypeLookupValue.setEnabled(Boolean.TRUE);
        return awardTypeLookupValue;
    }

    private AwardTypeLookupValueDetail createAwardTypeLookupValueDetail(
        AwardTypeLookupValue awardTypeLookupValue) {
        AwardTypeLookupValueDetail detail = new AwardTypeLookupValueDetail();
        detail.setCode(awardTypeLookupValue.getCode());
        detail.setAwardType(awardTypeLookupValue.getAwardType());
        detail.setDescription(awardTypeLookupValue.getDescription());
        detail.setStartDateActive(awardTypeLookupValue.getStartDateActive().format(
            DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        detail.setEndDateActive(awardTypeLookupValue.getEndDateActive().format(
            DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        detail.setEnabled(awardTypeLookupValue.getEnabled());
        return detail;
    }


    private OutcomeResultLookupValue createOutcomeResultLookupValue(String suffix) {
        OutcomeResultLookupValue outcomeResultLookupValue = new OutcomeResultLookupValue();
        outcomeResultLookupValue.setId(new OutcomeResultLookupValueId());
        outcomeResultLookupValue.getId().setProceedingCode("code" + suffix);
        outcomeResultLookupValue.getId().setOutcomeResult("result" + suffix);
        outcomeResultLookupValue.setOutcomeResultDescription("description" + suffix);
        outcomeResultLookupValue.setOutcomeResultLov("lov" + suffix);
        outcomeResultLookupValue.setEnabled(Boolean.TRUE);
        return outcomeResultLookupValue;
    }

    private OutcomeResultLookupValueDetail createOutcomeResultLookupValueDetail(
        OutcomeResultLookupValue outcomeResultLookupValue) {
        OutcomeResultLookupValueDetail detail = new OutcomeResultLookupValueDetail();
        detail.setProceedingCode(outcomeResultLookupValue.getId().getProceedingCode());
        detail.setOutcomeResult(outcomeResultLookupValue.getId().getOutcomeResult());
        detail.setOutcomeResultDescription(outcomeResultLookupValue.getOutcomeResultDescription());
        detail.setOutcomeResultLov(outcomeResultLookupValue.getOutcomeResultLov());
        detail.setEnabled(outcomeResultLookupValue.getEnabled());
        return detail;
    }

    private CountryLookupValue createCountryLookupValue(String suffix) {
        CountryLookupValue value = new CountryLookupValue();
        value.setCode("code" + suffix);
        value.setDescription("description" + suffix);
        return value;
    }

    private AmendmentTypeLookupValue createAmendmentTypeLookupValue(String suffix) {
        AmendmentTypeLookupValue value = new AmendmentTypeLookupValue();
        value.setApplicationTypeCode("code" + suffix);
        value.setApplicationTypeDescription("description" + suffix);
        value.setCostLimitCap("costLimit" + suffix);
        value.setDevolvedPowersInd("devolved" + suffix);
        value.setDefaultLarScopeFlag("defaultLAR" + suffix);
        return value;
    }

    private AmendmentTypeLookupValueDetail createAmendmentTypeLookupValueDetail(AmendmentTypeLookupValue value) {
        AmendmentTypeLookupValueDetail detail = new AmendmentTypeLookupValueDetail();
        detail.setApplicationTypeCode(value.getApplicationTypeCode());
        detail.setApplicationTypeDescription(value.getApplicationTypeDescription());
        detail.setCostLimitCap(value.getCostLimitCap());
        detail.setDevolvedPowersIndicator(value.getDevolvedPowersInd());
        detail.setDefaultLarScopeFlag(value.getDefaultLarScopeFlag());
        return detail;
    }

    private CommonLookupValue createCommonLookupValue(String suffix) {
        CommonLookupValue commonLookupValue = new CommonLookupValue();
        commonLookupValue.setType("type" + suffix);
        commonLookupValue.setCode("code" + suffix);
        commonLookupValue.setDescription("description" + suffix);
        commonLookupValue.setStartDateActive(LocalDateTime.now());
        commonLookupValue.setAttribute11("attribute11" + suffix);
        commonLookupValue.setAttribute12("attribute12" + suffix);
        commonLookupValue.setEnabled("true");
        commonLookupValue.setDefaultCode("defaultCode" + suffix);
        return commonLookupValue;
    }

    private CommonLookupValueDetail createCommonLookupValueDetails(CommonLookupValue commonLookupValue) {
        CommonLookupValueDetail detail = new CommonLookupValueDetail();
        detail.setType(commonLookupValue.getType());
        detail.setCode(commonLookupValue.getCode());
        detail.setDescription(commonLookupValue.getDescription());
        detail.setStartDateActive(commonLookupValue.getStartDateActive().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        detail.setAttribute11(commonLookupValue.getAttribute11());
        detail.setAttribute12(commonLookupValue.getAttribute12());
        detail.setEnabled(Boolean.parseBoolean(commonLookupValue.getEnabled()));
        detail.setDefaultCode(commonLookupValue.getDefaultCode());
        return detail;
    }

    private CaseStatusLookupValue createCaseStatusLookupValue(String suffix, Boolean copyAllowed) {
        CaseStatusLookupValue caseStatusLookupValue = new CaseStatusLookupValue();
        caseStatusLookupValue.setCode("code" + suffix);
        caseStatusLookupValue.setDescription("description" + suffix);
        caseStatusLookupValue.setCopyAllowed(copyAllowed);
        return caseStatusLookupValue;
    }

    private CaseStatusLookupValueDetail createCaseStatusLookupValueDetail(CaseStatusLookupValue caseStatusLookupValue) {
        CaseStatusLookupValueDetail detail = new CaseStatusLookupValueDetail();
        detail.setCode(caseStatusLookupValue.getCode());
        detail.setDescription(caseStatusLookupValue.getDescription());
        detail.setCopyAllowed(caseStatusLookupValue.getCopyAllowed());
        return detail;
    }


}