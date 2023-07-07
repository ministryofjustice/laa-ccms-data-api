package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.model.ProviderDetails;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.model.UserDetails;
import uk.gov.laa.ccms.data.model.CommonLookupValueListDetails;

@Mapper
public interface UserMapper {

    UserDetails toUserDetails(User user);

    @Mapping(target = "offices", ignore = true)
    ProviderDetails toProviderDetails(Firm firm);

}
