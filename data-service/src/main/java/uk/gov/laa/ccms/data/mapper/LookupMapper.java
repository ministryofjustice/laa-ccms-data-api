package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.AwardTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValue;
import uk.gov.laa.ccms.data.entity.PersonRelationshipToCaseLookupValue;
import uk.gov.laa.ccms.data.entity.StageEndLookupValue;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupValueDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupValueDetail;
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

  AwardTypeLookupDetail toAwardTypeLookupDetail(
      Page<AwardTypeLookupValue> awardTypeLookupValues);

  AwardTypeLookupValueDetail toAwardTypeLookupValueDetail(
      AwardTypeLookupValue awardTypeLookupValue);
}