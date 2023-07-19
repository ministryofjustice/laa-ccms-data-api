package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.model.FeeEarnerDetail;

@Mapper(componentModel = "spring")
public interface FeeEarnerMapper {
   FeeEarnerDetail toFeeEarnerDetail(Page<FeeEarner> page);

}