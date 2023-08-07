package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupValueDetail;


@Mapper(componentModel = "spring")
public interface LookupMapper {
   CommonLookupDetail toCommonLookupDetail(Page<CommonLookupValue> page);
   CaseStatusLookupDetail toCaseStatusLookupDetail(Page<CaseStatusLookupValue> page);
   AmendmentTypeLookupDetail toAmendmentTypeLookupDetail(Page<AmendmentTypeLookupValue> page);

   @Mapping(target = "devolvedPowersIndicator", source = "devolvedPowersInd")
   @Mapping(target = "defaultLarScopeFlag", source = "defaultLARScopeFlag")
   AmendmentTypeLookupValueDetail toAmendmentTypeLookupValueDetail(AmendmentTypeLookupValue lookupValue);


}