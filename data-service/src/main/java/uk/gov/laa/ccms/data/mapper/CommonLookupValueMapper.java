package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;

@Mapper
public interface CommonLookupValueMapper {
   CommonLookupDetail toCommonLookupDetail(Page<CommonLookupValue> page);

}