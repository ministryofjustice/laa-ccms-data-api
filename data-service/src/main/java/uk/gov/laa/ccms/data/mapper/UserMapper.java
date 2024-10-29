package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.model.BaseProvider;
import uk.gov.laa.ccms.data.model.BaseUser;
import uk.gov.laa.ccms.data.model.ProviderDetail;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.model.UserDetails;

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

  UserDetails toUserDetails(Page<User> users);

  BaseUser toBaseUser(User user);

  @Mapping(target = "isPrimary", ignore = true)
  BaseProvider toBaseProvider(Provider provider);

  @Mapping(target = "offices", ignore = true)
  BaseProvider toBaseProvider(Firm firm);
}
