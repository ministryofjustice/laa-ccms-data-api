package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.model.BaseProvider;
import uk.gov.laa.ccms.data.model.ProviderDetail;
import uk.gov.laa.ccms.data.model.UserDetail;

/**
 * Mapper interface for transforming entities to model objects related to user management.
 *
 * @see User
 * @see UserDetail
 * @see Firm
 * @see ProviderDetail
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDetail toUserDetail(User user);

  BaseProvider toBaseProvider(Firm firm);
}
