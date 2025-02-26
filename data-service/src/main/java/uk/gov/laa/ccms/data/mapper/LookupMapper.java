package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.AssessmentSummaryAttribute;
import uk.gov.laa.ccms.data.entity.AssessmentSummaryEntity;
import uk.gov.laa.ccms.data.entity.AwardTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CategoryOfLawLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.entity.Declaration;
import uk.gov.laa.ccms.data.entity.EvidenceDocumentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.LevelOfService;
import uk.gov.laa.ccms.data.entity.MatterType;
import uk.gov.laa.ccms.data.entity.OrganisationRelationshipToCaseLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValue;
import uk.gov.laa.ccms.data.entity.PersonRelationshipToCaseLookupValue;
import uk.gov.laa.ccms.data.entity.ProceedingClientInvolvementType;
import uk.gov.laa.ccms.data.entity.ProviderRequestData;
import uk.gov.laa.ccms.data.entity.ProviderRequestType;
import uk.gov.laa.ccms.data.entity.StageEndLookupValue;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.AssessmentSummaryAttributeLookupValueDetail;
import uk.gov.laa.ccms.data.model.AssessmentSummaryEntityLookupDetail;
import uk.gov.laa.ccms.data.model.AssessmentSummaryEntityLookupValueDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CategoryOfLawLookupDetail;
import uk.gov.laa.ccms.data.model.CategoryOfLawLookupValueDetail;
import uk.gov.laa.ccms.data.model.ClientInvolvementTypeLookupDetail;
import uk.gov.laa.ccms.data.model.ClientInvolvementTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupValueDetail;
import uk.gov.laa.ccms.data.model.DeclarationLookupDetail;
import uk.gov.laa.ccms.data.model.DeclarationLookupValueDetail;
import uk.gov.laa.ccms.data.model.EvidenceDocumentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.EvidenceDocumentTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.LevelOfServiceLookupDetail;
import uk.gov.laa.ccms.data.model.LevelOfServiceLookupValueDetail;
import uk.gov.laa.ccms.data.model.MatterTypeLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupValueDetail;
import uk.gov.laa.ccms.data.model.ProviderRequestDataLookupValueDetail;
import uk.gov.laa.ccms.data.model.ProviderRequestTypeLookupDetail;
import uk.gov.laa.ccms.data.model.ProviderRequestTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupValueDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupValueDetail;

/**
 * Mapper interface for converting between various lookup entities and their corresponding DTO
 * representations.
 *
 * @see Mapper
 * @see CommonLookupValue
 * @see CaseStatusLookupValue
 * @see AmendmentTypeLookupValue
 * @see CommonLookupDetail
 * @see CaseStatusLookupDetail
 * @see AmendmentTypeLookupDetail
 */
@Mapper(componentModel = "spring")
public interface LookupMapper {
  CommonLookupDetail toCommonLookupDetail(Page<CommonLookupValue> page);

  CommonLookupDetail toCommonLookupDetailFromCountries(Page<CountryLookupValue> page);

  MatterTypeLookupDetail toMatterTypeLookupDetail(Page<MatterType> page);

  LevelOfServiceLookupDetail toLevelOfServicePage(Page<LevelOfService> page);

  @Mapping(target = "proceedingCode", source = "id.proceedingCode")
  @Mapping(target = "matterType", source = "id.matterType")
  @Mapping(target = "categoryOfLawCode", source = "id.categoryOfLawCode")
  @Mapping(target = "levelOfServiceCode", source = "id.levelOfServiceCode")
  LevelOfServiceLookupValueDetail toLevelOfServiceLookupValueDetail(LevelOfService levelOfService);

  ClientInvolvementTypeLookupDetail toClientInvolvementTypeLookupDetail(
      Page<ProceedingClientInvolvementType> page);

  @Mapping(target = "proceedingCode", source = "id.proceedingCode")
  @Mapping(target = "clientInvolvementType", source = "id.clientInvolvementType")
  ClientInvolvementTypeLookupValueDetail toClientInvolvementTypeLookupValueDetail(
      ProceedingClientInvolvementType proceedingClientInvolvementType);

  @Mapping(target = "type", ignore = true)
  @Mapping(target = "startDateActive", ignore = true)
  @Mapping(target = "attribute11", ignore = true)
  @Mapping(target = "attribute12", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  @Mapping(target = "defaultCode", ignore = true)
  CommonLookupValueDetail toCommonLookupValueDetail(CountryLookupValue lookupValue);

  CaseStatusLookupDetail toCaseStatusLookupDetail(Page<CaseStatusLookupValue> page);

  AmendmentTypeLookupDetail toAmendmentTypeLookupDetail(Page<AmendmentTypeLookupValue> page);

  @Mapping(target = "devolvedPowersIndicator", source = "devolvedPowersInd")
  AmendmentTypeLookupValueDetail toAmendmentTypeLookupValueDetail(
          AmendmentTypeLookupValue lookupValue);

  OutcomeResultLookupDetail toOutcomeResultLookupDetail(
      Page<OutcomeResultLookupValue> lookupValues);

  @Mapping(target = "proceedingCode", source = "id.proceedingCode")
  @Mapping(target = "outcomeResult", source = "id.outcomeResult")
  OutcomeResultLookupValueDetail toOutcomeResultLookupValueDetail(
      OutcomeResultLookupValue outcomeResultLookupValue);

  StageEndLookupDetail toStageEndLookupDetail(
      Page<StageEndLookupValue> lookupValues);

  @Mapping(target = "proceedingCode", source = "id.proceedingCode")
  @Mapping(target = "stageEnd", source = "id.stageEnd")
  StageEndLookupValueDetail toStageEndLookupValueDetail(
      StageEndLookupValue stageEndLookupValue);

  RelationshipToCaseLookupDetail toRelationshipToCaseLookupDetail(
      Page<PersonRelationshipToCaseLookupValue> lookupValues);

  RelationshipToCaseLookupValueDetail toRelationshipToCaseLookupValueDetail(
      PersonRelationshipToCaseLookupValue personRelationshipToCaseLookupValue);


  RelationshipToCaseLookupDetail toOrgRelationshipToCaseLookupDetail(
      Page<OrganisationRelationshipToCaseLookupValue> lookupValues);

  @Mapping(target = "dateOfBirthMandatory", ignore = true)
  RelationshipToCaseLookupValueDetail toOrgRelationshipToCaseLookupValueDetail(
      OrganisationRelationshipToCaseLookupValue organisationRelationshipToCaseLookupValue);

  AwardTypeLookupDetail toAwardTypeLookupDetail(
      Page<AwardTypeLookupValue> awardTypeLookupValues);

  AwardTypeLookupValueDetail toAwardTypeLookupValueDetail(
      AwardTypeLookupValue awardTypeLookupValue);

  CategoryOfLawLookupDetail toCategoryOfLawLookupDetail(
      Page<CategoryOfLawLookupValue> categoryOfLawLookupValues);

  CategoryOfLawLookupValueDetail toCategoryOfLawLookupValueDetail(
      CategoryOfLawLookupValue categoryOfLawLookupValue);

  EvidenceDocumentTypeLookupDetail toEvidenceDocumentTypeLookupDetail(
      Page<EvidenceDocumentTypeLookupValue> evidenceDocumentTypeLookupValues);

  @Mapping(target = "type", source = "id.type")
  @Mapping(target = "code", source = "id.code")
  EvidenceDocumentTypeLookupValueDetail toEvidenceDocumentTypeLookupValueDetail(
      EvidenceDocumentTypeLookupValue evidenceDocumentTypeLookupValue);

  AssessmentSummaryEntityLookupDetail toAssessmentSummaryEntityLookupDetail(
      Page<AssessmentSummaryEntity> assessmentSummaryEntities);

  @Mapping(target = "name", source = "opaEntityName")
  @Mapping(target = "displayName", source = "opaEntityDisplayName")
  AssessmentSummaryEntityLookupValueDetail toAssessmentSummaryEntityLookupValueDetail(
      AssessmentSummaryEntity assessmentSummaryEntity);

  @Mapping(target = "name", source = "opaAttributeName")
  @Mapping(target = "displayName", source = "opaAttributeDisplayName")
  AssessmentSummaryAttributeLookupValueDetail toAssessmentSummaryAttributeLookupValueDetail(
      AssessmentSummaryAttribute assessmentSummaryAttribute);

  DeclarationLookupDetail toDeclarationLookupDetail(
      Page<Declaration> declarations);

  @Mapping(target = "type", source = "declarationType")
  @Mapping(target = "number", source = "declarationNumber")
  @Mapping(target = "text", source = "declarationText")
  DeclarationLookupValueDetail toDeclarationLookupValueDetail(
      Declaration declaration);

  ProviderRequestTypeLookupDetail toProviderRequestTypeLookupDetail(
      Page<ProviderRequestType> providerRequestTypes);

  @Mapping(source = "caseRelated", target = "isCaseRelated")
  @Mapping(source = "additionalInformationPrompt", target = "additionalInformationPrompt")
  @Mapping(source = "claimUploadEnabled", target = "isClaimUploadEnabled")
  @Mapping(source = "providerRequestData", target = "dataItems")
  @Mapping(target = "isAdditionalInformationPromptRequired",
          expression = "java(!providerRequestType.getClaimUploadEnabled() "
                  + "&& providerRequestType.getProviderRequestData().isEmpty())")
  ProviderRequestTypeLookupValueDetail toProviderRequestTypeLookupValueDetail(
          ProviderRequestType providerRequestType);

  @Mapping(source = "id.dataItemCode", target = "code")
  @Mapping(source = "dataItemLabel", target = "label")
  @Mapping(source = "dataItemType", target = "type")
  @Mapping(source = "dataItemSeq", target = "sequence")
  ProviderRequestDataLookupValueDetail toProviderRequestDataLookupValueDetail(
      ProviderRequestData providerRequestData);




}