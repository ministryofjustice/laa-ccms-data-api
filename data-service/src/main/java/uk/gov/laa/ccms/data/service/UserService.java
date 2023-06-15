package uk.gov.laa.ccms.data.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUser(String id){
        return userRepository.findById(id);
    }

}
