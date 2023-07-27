package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;

@Mapper(componentModel = "spring")
public interface LookupMapper {
   CommonLookupDetail toCommonLookupDetail(Page<CommonLookupValue> page);

   CaseStatusLookupDetail toCaseStatusLookupDetail(Page<CaseStatusLookupValue> page);
}