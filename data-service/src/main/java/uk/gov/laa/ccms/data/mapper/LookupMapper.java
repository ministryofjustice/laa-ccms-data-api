package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupValueDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;

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

  CaseStatusLookupDetail toCaseStatusLookupDetail(Page<CaseStatusLookupValue> page);

  AmendmentTypeLookupDetail toAmendmentTypeLookupDetail(Page<AmendmentTypeLookupValue> page);

  @Mapping(target = "devolvedPowersIndicator", source = "devolvedPowersInd")
  AmendmentTypeLookupValueDetail toAmendmentTypeLookupValueDetail(
          AmendmentTypeLookupValue lookupValue);


}