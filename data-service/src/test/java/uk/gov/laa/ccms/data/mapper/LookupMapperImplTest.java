package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.AssessmentSummaryAttribute;
import uk.gov.laa.ccms.data.entity.AwardTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CategoryOfLawLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.entity.EvidenceDocumentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.EvidenceDocumentTypeLookupValueId;
import uk.gov.laa.ccms.data.entity.LevelOfService;
import uk.gov.laa.ccms.data.entity.LevelOfServiceId;
import uk.gov.laa.ccms.data.entity.MatterType;
import uk.gov.laa.ccms.data.entity.OrganisationRelationshipToCaseLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValueId;
import uk.gov.laa.ccms.data.entity.PersonRelationshipToCaseLookupValue;
import uk.gov.laa.ccms.data.entity.ProceedingClientInvolvementType;
import uk.gov.laa.ccms.data.entity.ProceedingClientInvolvementTypeId;
import uk.gov.laa.ccms.data.entity.StageEndLookupValue;
import uk.gov.laa.ccms.data.entity.StageEndLookupValueId;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.AssessmentSummaryAttributeLookupDetail;
import uk.gov.laa.ccms.data.model.AssessmentSummaryAttributeLookupValueDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupValueDetail;
import uk.gov.laa.ccms.data.model.CategoryOfLawLookupDetail;
import uk.gov.laa.ccms.data.model.CategoryOfLawLookupValueDetail;
import uk.gov.laa.ccms.data.model.ClientInvolvementTypeLookupDetail;
import uk.gov.laa.ccms.data.model.ClientInvolvementTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupValueDetail;
import uk.gov.laa.ccms.data.model.EvidenceDocumentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.EvidenceDocumentTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.LevelOfServiceLookupDetail;
import uk.gov.laa.ccms.data.model.LevelOfServiceLookupValueDetail;
import uk.gov.laa.ccms.data.model.MatterTypeLookupDetail;
import uk.gov.laa.ccms.data.model.MatterTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupValueDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupValueDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupDetail;
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

        // Actual AmendmentTypeLookupDetail
        RelationshipToCaseLookupDetail actualDetail = mapper.toRelationshipToCaseLookupDetail(page);

        // Assertion
        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toOrgRelationshipToCaseLookupValueDetail() {
        OrganisationRelationshipToCaseLookupValue value = createOrganisationRelationshipToCaseLookupvalue("1");

        RelationshipToCaseLookupValueDetail expectedDetail = createRelationshipToCaseLookupValueDetail(value);
        RelationshipToCaseLookupValueDetail actualDetail =
            mapper.toOrgRelationshipToCaseLookupValueDetail(value);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toOrgRelationshipToCaseLookupDetailTest() {
        List<OrganisationRelationshipToCaseLookupValue> values = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            values.add(createOrganisationRelationshipToCaseLookupvalue(String.valueOf(i)));
        }

        // Create a Page object
        Page<OrganisationRelationshipToCaseLookupValue> page = new PageImpl<>(values);
        RelationshipToCaseLookupDetail expectedDetail = new RelationshipToCaseLookupDetail();

        expectedDetail.setTotalPages(page.getTotalPages());
        expectedDetail.setTotalElements((int) page.getTotalElements());
        expectedDetail.setNumber(page.getNumber());
        expectedDetail.setSize(page.getSize());
        List<RelationshipToCaseLookupValueDetail> expectedContent = new ArrayList<>();
        for (OrganisationRelationshipToCaseLookupValue value : page) {
            expectedContent.add(createRelationshipToCaseLookupValueDetail(value));
        }
        expectedDetail.setContent(expectedContent);

        // Actual AmendmentTypeLookupDetail
        RelationshipToCaseLookupDetail actualDetail = mapper.toOrgRelationshipToCaseLookupDetail(page);

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

        // Create a Page object
        Page<AwardTypeLookupValue> page = new PageImpl<>(awardTypeLookupValues);
        AwardTypeLookupDetail expectedDetail = new AwardTypeLookupDetail();

        expectedDetail.setTotalPages(page.getTotalPages());
        expectedDetail.setTotalElements((int) page.getTotalElements());
        expectedDetail.setNumber(page.getNumber());
        expectedDetail.setSize(page.getSize());
        List<AwardTypeLookupValueDetail> expectedContent = new ArrayList<>();
        for (AwardTypeLookupValue value : page) {
            expectedContent.add(createAwardTypeLookupValueDetail(value));
        }
        expectedDetail.setContent(expectedContent);

        AwardTypeLookupDetail actualDetail =
            mapper.toAwardTypeLookupDetail(
                page);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toCategoryOfLawLookupValueDetailTest() {
        CategoryOfLawLookupValue value = createCategoryOfLawLookupValue("1");

        CategoryOfLawLookupValueDetail expectedDetail = createCategoryOfLawLookupValueDetail(value);
        CategoryOfLawLookupValueDetail actualDetail =
            mapper.toCategoryOfLawLookupValueDetail(value);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toCategoryOfLawLookupDetailTest() {
        List<CategoryOfLawLookupValue> awardTypeLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            awardTypeLookupValues.add(createCategoryOfLawLookupValue(String.valueOf(i)));
        }

        // Create a Page object
        Page<CategoryOfLawLookupValue> page = new PageImpl<>(awardTypeLookupValues);
        CategoryOfLawLookupDetail expectedDetail = new CategoryOfLawLookupDetail();

        expectedDetail.setTotalPages(page.getTotalPages());
        expectedDetail.setTotalElements((int) page.getTotalElements());
        expectedDetail.setNumber(page.getNumber());
        expectedDetail.setSize(page.getSize());
        List<CategoryOfLawLookupValueDetail> expectedContent = new ArrayList<>();
        for (CategoryOfLawLookupValue value : page) {
            expectedContent.add(createCategoryOfLawLookupValueDetail(value));
        }
        expectedDetail.setContent(expectedContent);

        CategoryOfLawLookupDetail actualDetail =
            mapper.toCategoryOfLawLookupDetail(
                page);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void matterTypeToMatterTypeLookupValueDetail_returnsCorrectDetail() {
        MatterType matterType = createMatterType("MAT1", "desc", "CAT1");
        MatterTypeLookupValueDetail expected = createMatterTypeLookupValueDetail("MAT1", "desc", "CAT1");

        MatterTypeLookupValueDetail actual = mapper.matterTypeToMatterTypeLookupValueDetail(matterType);

        assertEquals(expected, actual);
    }

    @Test
    void matterTypeListToMatterTypeLookupValueDetailList_returnsCorrectList() {
        MatterType matterType1 = createMatterType("MAT1", "desc1", "CAT1");
        MatterType matterType2 = createMatterType("MAT2", "desc2", "CAT2");

        List<MatterType> matterTypes = Arrays.asList(matterType1, matterType2);

        MatterTypeLookupValueDetail expected1 = createMatterTypeLookupValueDetail("MAT1", "desc1", "CAT1");
        MatterTypeLookupValueDetail expected2 = createMatterTypeLookupValueDetail("MAT2", "desc2", "CAT2");

        List<MatterTypeLookupValueDetail> expected = Arrays.asList(expected1, expected2);

        List<MatterTypeLookupValueDetail> actual = mapper.matterTypeListToMatterTypeLookupValueDetailList(matterTypes);

        assertEquals(expected, actual);
    }

    @Test
    void toMatterTypeLookupDetail_returnsCorrectDetail() {
        MatterType matterType1 = createMatterType("MAT1", "desc1", "CAT1");
        MatterType matterType2 = createMatterType("MAT2", "desc2", "CAT2");

        List<MatterType> matterTypes = Arrays.asList(matterType1, matterType2);
        Page<MatterType> page = new PageImpl<>(matterTypes);

        MatterTypeLookupDetail expected = new MatterTypeLookupDetail();
        expected.setTotalPages(page.getTotalPages());
        expected.setTotalElements((int) page.getTotalElements());
        expected.setNumber(page.getNumber());
        expected.setSize(page.getSize());
        expected.setContent(mapper.matterTypeListToMatterTypeLookupValueDetailList(page.getContent()));

        MatterTypeLookupDetail actual = mapper.toMatterTypeLookupDetail(page);

        assertEquals(expected, actual);
    }

    @Test
    void levelOfServiceListToLevelOfServiceLookupValueDetailList_returnsCorrectList() {
        LevelOfService levelOfService1 = createLevelOfService("MAT1", "PRO1", "CAT1");
        LevelOfService levelOfService2 = createLevelOfService("MAT2", "PRO2", "CAT2");

        List<LevelOfService> levelOfServices = Arrays.asList(levelOfService1, levelOfService2);

        LevelOfServiceLookupValueDetail expected1 = createLevelOfServiceLookupValueDetail("MAT1", "PRO1", "CAT1");
        LevelOfServiceLookupValueDetail expected2 = createLevelOfServiceLookupValueDetail("MAT2", "PRO2", "CAT2");

        List<LevelOfServiceLookupValueDetail> expected = Arrays.asList(expected1, expected2);

        List<LevelOfServiceLookupValueDetail> actual = mapper.levelOfServiceListToLevelOfServiceLookupValueDetailList(levelOfServices);

        assertEquals(expected, actual);
    }

    @Test
    void toLevelOfServicePage_returnsCorrectDetail() {
        LevelOfService levelOfService1 = createLevelOfService("MAT1", "PRO1", "CAT1");
        LevelOfService levelOfService2 = createLevelOfService("MAT2", "PRO2", "CAT2");

        List<LevelOfService> levelOfServices = Arrays.asList(levelOfService1, levelOfService2);
        Page<LevelOfService> page = new PageImpl<>(levelOfServices);

        LevelOfServiceLookupDetail expected = new LevelOfServiceLookupDetail();
        expected.setTotalPages(page.getTotalPages());
        expected.setTotalElements((int) page.getTotalElements());
        expected.setNumber(page.getNumber());
        expected.setSize(page.getSize());
        expected.setContent(mapper.levelOfServiceListToLevelOfServiceLookupValueDetailList(page.getContent()));

        LevelOfServiceLookupDetail actual = mapper.toLevelOfServicePage(page);

        assertEquals(expected, actual);
    }

    @Test
    void proceedingClientInvolvementTypeListToClientInvolvementTypeLookupValueDetailList_returnsCorrectList() {
        ProceedingClientInvolvementType proceedingClientInvolvementType1 = createProceedingClientInvolvementType("PRO1", "INV1");
        ProceedingClientInvolvementType proceedingClientInvolvementType2 = createProceedingClientInvolvementType("PRO2", "INV2");

        List<ProceedingClientInvolvementType> proceedingClientInvolvementTypes = Arrays.asList(proceedingClientInvolvementType1, proceedingClientInvolvementType2);

        ClientInvolvementTypeLookupValueDetail expected1 = createClientInvolvementTypeLookupValueDetail("PRO1", "INV1");
        ClientInvolvementTypeLookupValueDetail expected2 = createClientInvolvementTypeLookupValueDetail("PRO2", "INV2");

        List<ClientInvolvementTypeLookupValueDetail> expected = Arrays.asList(expected1, expected2);

        List<ClientInvolvementTypeLookupValueDetail> actual = mapper.proceedingClientInvolvementTypeListToClientInvolvementTypeLookupValueDetailList(proceedingClientInvolvementTypes);

        assertEquals(expected, actual);
    }

    @Test
    void toClientInvolvementTypeLookupDetail_returnsCorrectDetail() {
        ProceedingClientInvolvementType proceedingClientInvolvementType1 = createProceedingClientInvolvementType("PRO1", "INV1");
        ProceedingClientInvolvementType proceedingClientInvolvementType2 = createProceedingClientInvolvementType("PRO2", "INV2");

        List<ProceedingClientInvolvementType> proceedingClientInvolvementTypes = Arrays.asList(proceedingClientInvolvementType1, proceedingClientInvolvementType2);
        Page<ProceedingClientInvolvementType> page = new PageImpl<>(proceedingClientInvolvementTypes);

        ClientInvolvementTypeLookupDetail expected = new ClientInvolvementTypeLookupDetail();
        expected.setTotalPages(page.getTotalPages());
        expected.setTotalElements((int) page.getTotalElements());
        expected.setNumber(page.getNumber());
        expected.setSize(page.getSize());
        expected.setContent(mapper.proceedingClientInvolvementTypeListToClientInvolvementTypeLookupValueDetailList(page.getContent()));

        ClientInvolvementTypeLookupDetail actual = mapper.toClientInvolvementTypeLookupDetail(page);

        assertEquals(expected, actual);
    }

    @Test
    void toOutcomeResultLookupDetail_returnsCorrectDetail() {
        OutcomeResultLookupValue value1 = createOutcomeResultLookupValue("1");
        OutcomeResultLookupValue value2 = createOutcomeResultLookupValue("1");

        List<OutcomeResultLookupValue> values = Arrays.asList(value1, value2);
        Page<OutcomeResultLookupValue> page = new PageImpl<>(values);

        OutcomeResultLookupDetail expected = new OutcomeResultLookupDetail();
        expected.setTotalPages(page.getTotalPages());
        expected.setTotalElements((int) page.getTotalElements());
        expected.setNumber(page.getNumber());
        expected.setSize(page.getSize());
        expected.setContent(mapper.outcomeResultLookupValueListToOutcomeResultLookupValueDetailList(page.getContent()));

        OutcomeResultLookupDetail actual = mapper.toOutcomeResultLookupDetail(page);

        assertEquals(expected, actual);
    }

    @Test
    void toStageEndLookupDetail_returnsCorrectDetail() {
        StageEndLookupValue value1 = createStageEndLookupValue("1");
        StageEndLookupValue value2 = createStageEndLookupValue("1");

        List<StageEndLookupValue> values = Arrays.asList(value1, value2);
        Page<StageEndLookupValue> page = new PageImpl<>(values);

        StageEndLookupDetail expected = new StageEndLookupDetail();
        expected.setTotalPages(page.getTotalPages());
        expected.setTotalElements((int) page.getTotalElements());
        expected.setNumber(page.getNumber());
        expected.setSize(page.getSize());
        expected.setContent(mapper.stageEndLookupValueListToStageEndLookupValueDetailList(page.getContent()));

        StageEndLookupDetail actual = mapper.toStageEndLookupDetail(page);

        assertEquals(expected, actual);
    }

    @Test
    void toEvidenceDocumentTypeLookupDetail_returnsCorrectDetail() {
        EvidenceDocumentTypeLookupValue value1 = createEvidenceDocumentTypeLookupValue("1");
        EvidenceDocumentTypeLookupValue value2 = createEvidenceDocumentTypeLookupValue("2");

        List<EvidenceDocumentTypeLookupValue> values = Arrays.asList(value1, value2);
        Page<EvidenceDocumentTypeLookupValue> page = new PageImpl<>(values);

        EvidenceDocumentTypeLookupDetail expected = new EvidenceDocumentTypeLookupDetail();
        expected.setTotalPages(page.getTotalPages());
        expected.setTotalElements((int) page.getTotalElements());
        expected.setNumber(page.getNumber());
        expected.setSize(page.getSize());
        expected.setContent(List.of(
            creatEvidenceDocumentTypeLookupValueDetail(value1),
            creatEvidenceDocumentTypeLookupValueDetail(value2)));

        EvidenceDocumentTypeLookupDetail actual = mapper.toEvidenceDocumentTypeLookupDetail(page);

        assertEquals(expected, actual);
    }

    // Helper methods to create objects
    private LevelOfService createLevelOfService(String matterType, String proceedingCode, String categoryOfLawCode) {
        LevelOfService levelOfService = new LevelOfService();
        LevelOfServiceId id = new LevelOfServiceId();
        id.setMatterType(matterType);
        id.setProceedingCode(proceedingCode);
        id.setCategoryOfLawCode(categoryOfLawCode);
        levelOfService.setId(id);
        return levelOfService;
    }

    private LevelOfServiceLookupValueDetail createLevelOfServiceLookupValueDetail(String matterType, String proceedingCode, String categoryOfLawCode) {
        LevelOfServiceLookupValueDetail detail = new LevelOfServiceLookupValueDetail();
        detail.setMatterType(matterType);
        detail.setProceedingCode(proceedingCode);
        detail.setCategoryOfLawCode(categoryOfLawCode);
        return detail;
    }

    private MatterType createMatterType(String matterTypeCode, String description, String categoryOfLawCode) {
        MatterType matterType = new MatterType();
        matterType.setMatterType(matterTypeCode);
        matterType.setDescription(description);
        matterType.setCategoryOfLawCode(categoryOfLawCode);
        return matterType;
    }

    private MatterTypeLookupValueDetail createMatterTypeLookupValueDetail(String matterTypeCode, String description, String categoryOfLawCode) {
        MatterTypeLookupValueDetail detail = new MatterTypeLookupValueDetail();
        detail.setMatterType(matterTypeCode);
        detail.setDescription(description);
        detail.setCategoryOfLawCode(categoryOfLawCode);
        return detail;
    }

    private ProceedingClientInvolvementType createProceedingClientInvolvementType(String proceedingCode, String clientInvolvementType) {
        ProceedingClientInvolvementType proceedingClientInvolvementType = new ProceedingClientInvolvementType();
        ProceedingClientInvolvementTypeId id = new ProceedingClientInvolvementTypeId();
        id.setProceedingCode(proceedingCode);
        id.setClientInvolvementType(clientInvolvementType);
        proceedingClientInvolvementType.setId(id);
        return proceedingClientInvolvementType;
    }

    private ClientInvolvementTypeLookupValueDetail createClientInvolvementTypeLookupValueDetail(String proceedingCode, String clientInvolvementType) {
        ClientInvolvementTypeLookupValueDetail detail = new ClientInvolvementTypeLookupValueDetail();
        detail.setProceedingCode(proceedingCode);
        detail.setClientInvolvementType(clientInvolvementType);
        return detail;
    }

    private OrganisationRelationshipToCaseLookupValue createOrganisationRelationshipToCaseLookupvalue(String suffix) {
        OrganisationRelationshipToCaseLookupValue organisationRelationshipToCaseLookupValue = new OrganisationRelationshipToCaseLookupValue();
        organisationRelationshipToCaseLookupValue.setCode("code" + suffix);
        organisationRelationshipToCaseLookupValue.setDescription("description" + suffix);
        return organisationRelationshipToCaseLookupValue;
    }

    private RelationshipToCaseLookupValueDetail createRelationshipToCaseLookupValueDetail(
        OrganisationRelationshipToCaseLookupValue organisationRelationshipToCaseLookupValue) {
        RelationshipToCaseLookupValueDetail detail = new RelationshipToCaseLookupValueDetail();
        detail.setCode(organisationRelationshipToCaseLookupValue.getCode());
        detail.setDescription(organisationRelationshipToCaseLookupValue.getDescription());
        return detail;
    }

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

    private CategoryOfLawLookupValue createCategoryOfLawLookupValue(String suffix) {
        CategoryOfLawLookupValue awardTypeLookupValue = new CategoryOfLawLookupValue();
        awardTypeLookupValue.setCode("code" + suffix);
        awardTypeLookupValue.setMatterTypeDescription("desc" + suffix);
        awardTypeLookupValue.setCopyCostLimit(Boolean.TRUE);
        return awardTypeLookupValue;
    }

    private CategoryOfLawLookupValueDetail createCategoryOfLawLookupValueDetail(
        CategoryOfLawLookupValue categoryOfLawLookupValue) {
        CategoryOfLawLookupValueDetail detail = new CategoryOfLawLookupValueDetail();
        detail.setCode(categoryOfLawLookupValue.getCode());
        detail.setMatterTypeDescription(categoryOfLawLookupValue.getMatterTypeDescription());
        detail.setCopyCostLimit(categoryOfLawLookupValue.getCopyCostLimit());
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

    private EvidenceDocumentTypeLookupValue createEvidenceDocumentTypeLookupValue(String suffix) {
        EvidenceDocumentTypeLookupValue lookupValue =
            new EvidenceDocumentTypeLookupValue();
        lookupValue.setId(new EvidenceDocumentTypeLookupValueId(
            "type" + suffix, "code" + suffix));
        lookupValue.setDescription("description" + suffix);
        lookupValue.setStartDateActive(LocalDateTime.now());
        lookupValue.setEndDateActive(LocalDateTime.now());
        return lookupValue;
    }

    private EvidenceDocumentTypeLookupValueDetail creatEvidenceDocumentTypeLookupValueDetail(
        EvidenceDocumentTypeLookupValue lookupValue) {
        EvidenceDocumentTypeLookupValueDetail detail = new EvidenceDocumentTypeLookupValueDetail();
        detail.setType(lookupValue.getId().getType());
        detail.setCode(lookupValue.getId().getCode());
        detail.setDescription(lookupValue.getDescription());
        detail.setStartDateActive(lookupValue.getStartDateActive().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        detail.setEndDateActive(lookupValue.getEndDateActive().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return detail;
    }

    @Test
    void toAssessmentSummaryAttributeLookupDetail_returnsCorrectDetail() {
        AssessmentSummaryAttribute attribute = new AssessmentSummaryAttribute();
        attribute.setOpaEntityName("entityName");
        attribute.setOpaEntityDisplayName("entityDisplayName");
        attribute.setOpaAttributeName("attributeName");
        attribute.setOpaAttributeDisplayName("attributeDisplayName");
        attribute.setEntityLevel(2);

        List<AssessmentSummaryAttribute> attributes = Collections.singletonList(attribute);
        Page<AssessmentSummaryAttribute> page = new PageImpl<>(attributes);

        AssessmentSummaryAttributeLookupDetail expected = new AssessmentSummaryAttributeLookupDetail();
        expected.setTotalPages(page.getTotalPages());
        expected.setTotalElements((int) page.getTotalElements());
        expected.setNumber(page.getNumber());
        expected.setSize(page.getSize());
        expected.setContent(mapper.assessmentSummaryAttributeListToAssessmentSummaryAttributeLookupValueDetailList(page.getContent()));

        AssessmentSummaryAttributeLookupDetail actual = mapper.toAssessmentSummaryAttributeLookupDetail(page);

        assertEquals(expected, actual);
    }

    @Test
    void toAssessmentSummaryAttributeLookupDetail_returnsNullWhenInputIsNull() {
        AssessmentSummaryAttributeLookupDetail actual = mapper.toAssessmentSummaryAttributeLookupDetail(null);
        assertNull(actual);
    }

    @Test
    void toAssessmentSummaryAttributeLookupValueDetail_returnsCorrectDetail() {
        AssessmentSummaryAttribute attribute = new AssessmentSummaryAttribute();
        attribute.setOpaEntityName("entityName");
        attribute.setOpaEntityDisplayName("entityDisplayName");
        attribute.setOpaAttributeName("attributeName");
        attribute.setOpaAttributeDisplayName("attributeDisplayName");
        attribute.setEntityLevel(2);

        AssessmentSummaryAttributeLookupValueDetail expected = new AssessmentSummaryAttributeLookupValueDetail();
        expected.setEntityName(attribute.getOpaEntityName());
        expected.setEntityDisplayName(attribute.getOpaEntityDisplayName());
        expected.setAttributeName(attribute.getOpaAttributeName());
        expected.setAttributeDisplayName(attribute.getOpaAttributeDisplayName());
        expected.setEntityLevel(attribute.getEntityLevel());

        AssessmentSummaryAttributeLookupValueDetail actual = mapper.toAssessmentSummaryAttributeLookupValueDetail(attribute);

        assertEquals(expected, actual);
    }

    @Test
    void toAssessmentSummaryAttributeLookupValueDetail_returnsNullWhenInputIsNull() {
        AssessmentSummaryAttributeLookupValueDetail actual = mapper.toAssessmentSummaryAttributeLookupValueDetail(null);
        assertNull(actual);
    }



}