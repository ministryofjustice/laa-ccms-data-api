package uk.gov.laa.ccms.data.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.User;
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
public class UserService {

  private final UserRepository userRepository;

  public Optional<User> getUser(String id) {
    return userRepository.findById(id);
  }

}
