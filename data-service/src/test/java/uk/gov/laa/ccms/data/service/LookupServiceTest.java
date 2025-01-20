package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.AssessmentSummaryEntity;
import uk.gov.laa.ccms.data.entity.AwardTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CategoryOfLawLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.entity.Declaration;
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
import uk.gov.laa.ccms.data.entity.ProviderRequestType;
import uk.gov.laa.ccms.data.entity.StageEndLookupValue;
import uk.gov.laa.ccms.data.entity.StageEndLookupValueId;
import uk.gov.laa.ccms.data.mapper.LookupMapper;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AssessmentSummaryEntityLookupDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CategoryOfLawLookupDetail;
import uk.gov.laa.ccms.data.model.ClientInvolvementTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.DeclarationLookupDetail;
import uk.gov.laa.ccms.data.model.EvidenceDocumentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.LevelOfServiceLookupDetail;
import uk.gov.laa.ccms.data.model.MatterTypeLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupDetail;
import uk.gov.laa.ccms.data.model.ProviderRequestTypeLookupDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupDetail;
import uk.gov.laa.ccms.data.repository.AmendmentTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.AssessmentSummaryAttributesRepository;
import uk.gov.laa.ccms.data.repository.AwardTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CaseStatusLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CategoryOfLawLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CommonLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CountryLookupValueRepository;
import uk.gov.laa.ccms.data.repository.DeclarationRepository;
import uk.gov.laa.ccms.data.repository.EvidenceDocumentTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.LevelOfServiceRepository;
import uk.gov.laa.ccms.data.repository.MatterTypeRepository;
import uk.gov.laa.ccms.data.repository.OrganisationRelationshipToCaseLookupValueRepository;
import uk.gov.laa.ccms.data.repository.OutcomeResultLookupValueRepository;
import uk.gov.laa.ccms.data.repository.PersonRelationshipToCaseLookupValueRepository;
import uk.gov.laa.ccms.data.repository.ProceedingClientInvolvementTypeRepository;
import uk.gov.laa.ccms.data.repository.ProviderRequestTypeRepository;
import uk.gov.laa.ccms.data.repository.StageEndLookupValueRepository;

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
    private OutcomeResultLookupValueRepository outcomeResultLookupValueRepository;

    @Mock
    private StageEndLookupValueRepository stageEndLookupValueRepository;

    @Mock
    private PersonRelationshipToCaseLookupValueRepository
        personRelationshipToCaseLookupValueRepository;

    @Mock
    private OrganisationRelationshipToCaseLookupValueRepository
        organisationRelationshipToCaseLookupValueRepository;

    @Mock
    private AwardTypeLookupValueRepository awardTypeLookupValueRepository;

    @Mock
    private CategoryOfLawLookupValueRepository categoryOfLawLookupValueRepository;

    @Mock
    private MatterTypeRepository matterTypeRepository;

    @Mock
    private ProceedingClientInvolvementTypeRepository proceedingClientInvolvementTypeRepository;

    @Mock
    private LevelOfServiceRepository levelOfServiceRepository;

    @Mock
    private EvidenceDocumentTypeLookupValueRepository evidenceDocumentTypeLookupValueRepository;

    @Mock
    private AssessmentSummaryAttributesRepository assessmentSummaryAttributesRepository;

    @Mock
    private ProviderRequestTypeRepository providerRequestTypeRepository;

    @Mock
    private DeclarationRepository declarationRepository;

    @Mock
    private LookupMapper lookupMapper;

    @Mock
    private Root<AssessmentSummaryEntity> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder builder;

    @InjectMocks
    private LookupService lookupService;

    @Test
    void getCommonValues_returnsPageOfCommonValues() {
        CommonLookupValue commonLookupValue = new CommonLookupValue();
        commonLookupValue.setCode("code");
        commonLookupValue.setType("type");
        commonLookupValue.setDescription("desc");
        Example<CommonLookupValue> example = Example.of(commonLookupValue);
        Pageable pageable = Pageable.ofSize(10).withPage(0);
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
        Pageable pageable = Pageable.ofSize(10).withPage(0);
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
        Pageable pageable = Pageable.ofSize(10).withPage(0);
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
        Pageable pageable = Pageable.ofSize(10).withPage(0);
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
        Pageable pageable = Pageable.ofSize(10).withPage(0);
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
        Pageable pageable = Pageable.ofSize(10).withPage(0);
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
        Pageable pageable = Pageable.ofSize(10).withPage(0);
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

    @Test
    void getOutcomeResultValues_returnsPageOfValues() {
        OutcomeResultLookupValue outcomeResultLookupValue = new OutcomeResultLookupValue();
        outcomeResultLookupValue.setId(new OutcomeResultLookupValueId());
        outcomeResultLookupValue.getId().setProceedingCode("code");
        outcomeResultLookupValue.getId().setOutcomeResult("result");
        Example<OutcomeResultLookupValue> example = Example.of(outcomeResultLookupValue);
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<OutcomeResultLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(outcomeResultLookupValue));
        OutcomeResultLookupDetail expectedResponse = new OutcomeResultLookupDetail();

        when(outcomeResultLookupValueRepository.findAll(example, pageable))
            .thenReturn(expectedPage);
        when(lookupMapper.toOutcomeResultLookupDetail(expectedPage)).thenReturn(
            expectedResponse);

        OutcomeResultLookupDetail actualResponse = lookupService.getOutcomeResultLookupValues(
            outcomeResultLookupValue.getId().getProceedingCode(),
            outcomeResultLookupValue.getId().getOutcomeResult(),
            pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getStageEndValues_returnsPageOfValues() {
        StageEndLookupValue stageEndLookupValue = new StageEndLookupValue();
        stageEndLookupValue.setId(new StageEndLookupValueId());
        stageEndLookupValue.getId().setProceedingCode("code");
        stageEndLookupValue.getId().setStageEnd("stageEnd");
        Example<StageEndLookupValue> example = Example.of(stageEndLookupValue);
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<StageEndLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(stageEndLookupValue));
        StageEndLookupDetail expectedResponse = new StageEndLookupDetail();

        when(stageEndLookupValueRepository.findAll(example, pageable))
            .thenReturn(expectedPage);
        when(lookupMapper.toStageEndLookupDetail(expectedPage)).thenReturn(
            expectedResponse);

        StageEndLookupDetail actualResponse = lookupService.getStageEndLookupValues(
            stageEndLookupValue.getId().getProceedingCode(),
            stageEndLookupValue.getId().getStageEnd(),
            pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getPersonToCaseRelationshipValues_returnsPageOfValues() {
        PersonRelationshipToCaseLookupValue personRelationshipToCaseLookupValue = new PersonRelationshipToCaseLookupValue();
        personRelationshipToCaseLookupValue.setCode("code");
        personRelationshipToCaseLookupValue.setDescription("description");
        Example<PersonRelationshipToCaseLookupValue> example = Example.of(personRelationshipToCaseLookupValue);
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<PersonRelationshipToCaseLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(personRelationshipToCaseLookupValue));
        RelationshipToCaseLookupDetail expectedResponse = new RelationshipToCaseLookupDetail();

        when(personRelationshipToCaseLookupValueRepository.findAll(example, pageable))
            .thenReturn(expectedPage);
        when(lookupMapper.toRelationshipToCaseLookupDetail(expectedPage))
            .thenReturn(expectedResponse);

        RelationshipToCaseLookupDetail actualResponse = lookupService.getPersonToCaseRelationshipLookupValues(
            personRelationshipToCaseLookupValue.getCode(),
            personRelationshipToCaseLookupValue.getDescription(),
            pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getOrganisationToCaseRelationshipValues_returnsPageOfValues() {
        OrganisationRelationshipToCaseLookupValue organisationRelationshipToCaseLookupValue = new OrganisationRelationshipToCaseLookupValue();
        organisationRelationshipToCaseLookupValue.setCode("code");
        organisationRelationshipToCaseLookupValue.setDescription("description");
        Example<OrganisationRelationshipToCaseLookupValue> example = Example.of(organisationRelationshipToCaseLookupValue);
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<OrganisationRelationshipToCaseLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(organisationRelationshipToCaseLookupValue));
        RelationshipToCaseLookupDetail expectedResponse = new RelationshipToCaseLookupDetail();

        when(organisationRelationshipToCaseLookupValueRepository.findAll(example, pageable))
            .thenReturn(expectedPage);
        when(lookupMapper.toOrgRelationshipToCaseLookupDetail(expectedPage))
            .thenReturn(expectedResponse);

        RelationshipToCaseLookupDetail actualResponse = lookupService.getOrganisationToCaseRelationshipLookupValues(
            organisationRelationshipToCaseLookupValue.getCode(),
            organisationRelationshipToCaseLookupValue.getDescription(),
            pageable);

        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    void getAwardTypeValues_returnsPageOfValues() {
        AwardTypeLookupValue awardTypeLookupValue = new AwardTypeLookupValue();
        awardTypeLookupValue.setCode("code");
        awardTypeLookupValue.setAwardType("awardType");
        Example<AwardTypeLookupValue> example = Example.of(awardTypeLookupValue);
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<AwardTypeLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(awardTypeLookupValue));
        AwardTypeLookupDetail expectedResponse = new AwardTypeLookupDetail();

        when(awardTypeLookupValueRepository.findAll(example, pageable))
            .thenReturn(expectedPage);
        when(lookupMapper.toAwardTypeLookupDetail(expectedPage)).thenReturn(
            expectedResponse);

        AwardTypeLookupDetail actualResponse = lookupService.getAwardTypeLookupValues(
            awardTypeLookupValue.getCode(),
            awardTypeLookupValue.getAwardType(),
            pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getCategoryOfLawValues_returnsPageOfValues() {
        CategoryOfLawLookupValue categoryOfLawLookupValue = new CategoryOfLawLookupValue();
        categoryOfLawLookupValue.setCode("code");
        categoryOfLawLookupValue.setMatterTypeDescription("desc");
        categoryOfLawLookupValue.setCopyCostLimit(Boolean.TRUE);

        Example<CategoryOfLawLookupValue> example = Example.of(categoryOfLawLookupValue);
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<CategoryOfLawLookupValue> expectedPage = new PageImpl<>(
            Collections.singletonList(categoryOfLawLookupValue));
        CategoryOfLawLookupDetail expectedResponse = new CategoryOfLawLookupDetail();

        when(categoryOfLawLookupValueRepository.findAll(example, pageable))
            .thenReturn(expectedPage);
        when(lookupMapper.toCategoryOfLawLookupDetail(expectedPage)).thenReturn(
            expectedResponse);

        CategoryOfLawLookupDetail actualResponse = lookupService.getCategoryOfLawLookupValues(
            categoryOfLawLookupValue.getCode(),
            categoryOfLawLookupValue.getMatterTypeDescription(),
            categoryOfLawLookupValue.getCopyCostLimit(),
            pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getMatterTypeLookupValues_returnsPageOfMatterTypeValues() {
        String description = "desc";
        String matterType = "MAT1";
        String categoryOfLaw = "CAT1";
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        MatterType matterTypeEntity = new MatterType();
        matterTypeEntity.setDescription(description);
        matterTypeEntity.setMatterType(matterType);
        matterTypeEntity.setCategoryOfLawCode(categoryOfLaw);

        Page<MatterType> expectedPage = new PageImpl<>(
            Collections.singletonList(matterTypeEntity));
        MatterTypeLookupDetail expectedResponse = new MatterTypeLookupDetail();

        when(matterTypeRepository.findAll(Example.of(matterTypeEntity), pageable)).thenReturn(expectedPage);
        when(lookupMapper.toMatterTypeLookupDetail(expectedPage)).thenReturn(expectedResponse);

        MatterTypeLookupDetail actualResponse = lookupService.getMatterTypeLookupValues(
            description, matterType, categoryOfLaw, pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getClientInvolvementTypeLookupValues_returnsPageOfClientInvolvementTypeValues() {
        String proceedingCode = "PRO1";
        String clientInvolvementType = "INV1";
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        ProceedingClientInvolvementType proceedingClientInvolvementTypeEntity = new ProceedingClientInvolvementType();
        proceedingClientInvolvementTypeEntity.setId(new ProceedingClientInvolvementTypeId());
        proceedingClientInvolvementTypeEntity.getId().setProceedingCode(proceedingCode);
        proceedingClientInvolvementTypeEntity.getId().setClientInvolvementType(clientInvolvementType);

        Page<ProceedingClientInvolvementType> expectedPage = new PageImpl<>(
            Collections.singletonList(proceedingClientInvolvementTypeEntity));
        ClientInvolvementTypeLookupDetail expectedResponse = new ClientInvolvementTypeLookupDetail();

        when(proceedingClientInvolvementTypeRepository.findAll(Example.of(proceedingClientInvolvementTypeEntity), pageable)).thenReturn(expectedPage);
        when(lookupMapper.toClientInvolvementTypeLookupDetail(expectedPage)).thenReturn(expectedResponse);

        ClientInvolvementTypeLookupDetail actualResponse = lookupService.getClientInvolvementTypeLookupValues(
            proceedingCode, clientInvolvementType, pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getLevelOfServiceLookupValues_returnsPageOfLevelOfServiceValues() {
        String proceedingCode = "PRO1";
        String matterType = "MAT1";
        String categoryOfLaw = "CAT1";
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        LevelOfServiceId levelOfServiceId = new LevelOfServiceId();
        levelOfServiceId.setMatterType(matterType);
        levelOfServiceId.setProceedingCode(proceedingCode);
        levelOfServiceId.setCategoryOfLawCode(categoryOfLaw);

        LevelOfService levelOfServiceEntity = new LevelOfService();
        levelOfServiceEntity.setId(levelOfServiceId);

        Page<LevelOfService> expectedPage = new PageImpl<>(
            Collections.singletonList(levelOfServiceEntity));
        LevelOfServiceLookupDetail expectedResponse = new LevelOfServiceLookupDetail();

        when(levelOfServiceRepository.findAll(Example.of(levelOfServiceEntity), pageable)).thenReturn(expectedPage);
        when(lookupMapper.toLevelOfServicePage(expectedPage)).thenReturn(expectedResponse);

        LevelOfServiceLookupDetail actualResponse = lookupService.getLevelOfServiceLookupValues(
            proceedingCode, matterType, categoryOfLaw, pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getEvidenceDocumentTypeLookupValues_returnsPageOEvidenceDocumentTypeValues() {
        String type = "atype";
        String code = "code1";
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        EvidenceDocumentTypeLookupValueId lookupValueId =
            new EvidenceDocumentTypeLookupValueId();
        lookupValueId.setType(type);
        lookupValueId.setCode(code);

        EvidenceDocumentTypeLookupValue lookupValue = new EvidenceDocumentTypeLookupValue();
        lookupValue.setId(lookupValueId);

        Page<EvidenceDocumentTypeLookupValue> expectedPage = new PageImpl<>(List.of(lookupValue));
        EvidenceDocumentTypeLookupDetail expectedResponse = new EvidenceDocumentTypeLookupDetail();

        when(evidenceDocumentTypeLookupValueRepository.findAll(
            Example.of(lookupValue), pageable)).thenReturn(expectedPage);
        when(lookupMapper.toEvidenceDocumentTypeLookupDetail(expectedPage)).thenReturn(expectedResponse);

        EvidenceDocumentTypeLookupDetail actualResponse =
            lookupService.getEvidenceDocumentTypeLookupValues(
                type, code, pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @SuppressWarnings("unchecked")
    @Test
    void getAssessmentSummaryAttributes_withParentSummaryType_returnsFilteredResults() {
        String summaryType = "PARENT";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("entityDisplaySequence").ascending());

        List<AssessmentSummaryEntity> entities = new ArrayList<>();
        entities.add(createAssessmentSummaryEntity(1));
        Page<AssessmentSummaryEntity> expectedPage = new PageImpl<>(entities);

        AssessmentSummaryEntityLookupDetail expectedResponse = new AssessmentSummaryEntityLookupDetail();

        when(assessmentSummaryAttributesRepository.findAll(any(Specification.class), any(PageRequest.class)))
            .thenReturn(expectedPage);
        when(lookupMapper.toAssessmentSummaryEntityLookupDetail(expectedPage))
            .thenReturn(expectedResponse);

        AssessmentSummaryEntityLookupDetail actualResponse = lookupService.getAssessmentSummaryAttributes(summaryType, pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @SuppressWarnings("unchecked")
    @Test
    void getAssessmentSummaryAttributes_withChildSummaryType_returnsFilteredResults() {
        String summaryType = "CHILD";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("entityDisplaySequence").ascending());

        List<AssessmentSummaryEntity> entities = new ArrayList<>();
        entities.add(createAssessmentSummaryEntity(3));
        Page<AssessmentSummaryEntity> expectedPage = new PageImpl<>(entities);

        AssessmentSummaryEntityLookupDetail expectedResponse = new AssessmentSummaryEntityLookupDetail();

        when(assessmentSummaryAttributesRepository.findAll(any(Specification.class), any(PageRequest.class)))
            .thenReturn(expectedPage);
        when(lookupMapper.toAssessmentSummaryEntityLookupDetail(expectedPage))
            .thenReturn(expectedResponse);

        AssessmentSummaryEntityLookupDetail actualResponse = lookupService.getAssessmentSummaryAttributes(summaryType, pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @SuppressWarnings("unchecked")
    @Test
    void getAssessmentSummaryAttributes_withNullSummaryType_returnsAllResults() {
        String summaryType = null;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("entityDisplaySequence").ascending());

        List<AssessmentSummaryEntity> entities = new ArrayList<>();
        entities.add(createAssessmentSummaryEntity(1));
        entities.add(createAssessmentSummaryEntity(3));
        Page<AssessmentSummaryEntity> expectedPage = new PageImpl<>(entities);

        AssessmentSummaryEntityLookupDetail expectedResponse = new AssessmentSummaryEntityLookupDetail();

        when(assessmentSummaryAttributesRepository.findAll(any(Specification.class), any(PageRequest.class)))
            .thenReturn(expectedPage);
        when(lookupMapper.toAssessmentSummaryEntityLookupDetail(expectedPage))
            .thenReturn(expectedResponse);

        AssessmentSummaryEntityLookupDetail actualResponse = lookupService.getAssessmentSummaryAttributes(summaryType, pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    // Helper methods to create objects
    private AssessmentSummaryEntity createAssessmentSummaryEntity(int entityLevel) {
        AssessmentSummaryEntity entity = new AssessmentSummaryEntity();
        entity.setEntityLevel(entityLevel);
        entity.setOpaEntityName("entityName" + entityLevel);
        entity.setOpaEntityDisplayName("entityDisplayName" + entityLevel);
        entity.setEntityDisplaySequence(String.valueOf(entityLevel));
        return entity;
    }

    @Test
    @DisplayName("getDeclarationLookupValues returns page of declaration values")
    void getDeclarationLookupValues_returnsPageOfDeclarationValues() {
        String declarationType = "type1";
        String billType = "bill1";
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        Declaration declaration = new Declaration();
        declaration.setDeclarationType(declarationType);
        declaration.setBillType(billType);

        Page<Declaration> expectedPage = new PageImpl<>(Collections.singletonList(declaration));
        DeclarationLookupDetail expectedResponse = new DeclarationLookupDetail();

        when(declarationRepository.findAll(Example.of(declaration), pageable)).thenReturn(expectedPage);
        when(lookupMapper.toDeclarationLookupDetail(expectedPage)).thenReturn(expectedResponse);

        DeclarationLookupDetail actualResponse = lookupService.getDeclarationLookupValues(declarationType, billType, pageable);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("getProviderRequestTypeLookupValues returns page of provider request type values")
    void getProviderRequestTypeLookupValues_returnsPageOfProviderRequestTypeValues() {
        Boolean isCaseRelated = Boolean.TRUE;
        String type = "providerType";
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        ProviderRequestType example = new ProviderRequestType();
        example.setCaseRelated(isCaseRelated);
        example.setType(type);

        Page<ProviderRequestType> expectedPage = new PageImpl<>(Collections.singletonList(example));
        ProviderRequestTypeLookupDetail expectedResponse = new ProviderRequestTypeLookupDetail();

        when(providerRequestTypeRepository.findAll(Example.of(example), pageable))
            .thenReturn(new PageImpl<>(Collections.singletonList(example)));
        when(lookupMapper.toProviderRequestTypeLookupDetail(expectedPage))
            .thenReturn(expectedResponse);

        ProviderRequestTypeLookupDetail actualResponse = lookupService.getProviderRequestTypeLookupValues(
            isCaseRelated, type, pageable);

        assertEquals(expectedResponse, actualResponse);
    }

}
