package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.model.ProviderDetail;
import uk.gov.laa.ccms.data.model.UserDetail;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDetail toUserDetail(User user);

    @Mapping(target = "offices", ignore = true)
    ProviderDetail toProviderDetail(Firm firm);

}
