package uk.gov.laa.ccms.data.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.mapper.UserMapper;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.model.UserDetails;
import uk.gov.laa.ccms.data.repository.UserRepository;


/**
 * Service class for managing user-related operations.
 *
 * <p>This service provides methods to interact with user-related data and encapsulates
 * the logic required to access the underlying {@link UserRepository}.</p>
 *
 * @see User
 * @see UserRepository
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService extends AbstractEbsDataService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  /**
   * Get a single User based on its id.
   *
   * @param id = the id of the User.
   * @return Optional UserDetail.
   */

  @Transactional(readOnly = true)
  public Optional<UserDetail> getUser(String id) {

    return userRepository.findById(id)
        .map(userMapper::toUserDetail);
  }

  /**
   * Returns true If user exists.
   *
   * @param loginId = the id of the User.
   * @return boolean
   */
  public boolean existsUserById(String loginId) {
    return userRepository.existsUserByLoginId(loginId);
  }

  /**
   * Get a UserDetails containing a page of BaseUser objects, based on the supplied
   * search criteria.
   *
   * @param providerId - the related providerId for the User.
   * @param pageable - the pageable settings.
   * @return UserDetails containing a page of BaseUser.
   */
  public UserDetails getUsers(Integer providerId, Pageable pageable) {
    Page<User> users = userRepository.findByFirmsId(providerId, pageable);
    return userMapper.toUserDetails(users);
  }
}
