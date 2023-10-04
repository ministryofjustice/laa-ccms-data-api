package uk.gov.laa.ccms.data.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.ScopeLimitation;
import uk.gov.laa.ccms.data.mapper.ScopeLimitationMapper;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetail;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetails;
import uk.gov.laa.ccms.data.repository.ScopeLimitationRepository;

/**
 * Service class responsible for managing {@link ScopeLimitation} entities.
 *
 * <p>This service provides methods to retrieve and manipulate {@code ScopeLimitation} entities,
 * primarily through interactions with the {@link ScopeLimitationRepository}.</p>
 *
 * @see Service
 * @see ScopeLimitation
 * @see ScopeLimitationRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ScopeLimitationService extends AbstractEbsDataService {

  private final ScopeLimitationRepository scopeLimitationRepository;

  private final ScopeLimitationMapper scopeLimitationMapper;

  /**
   * Retrieve a page of ScopeLimitationDetail based on the supplied example value.
   *
   * @param scopeLimitationDetail - the example scope limitation
   * @param pageable - the pageable settings
   * @return ScopeLimitationDetails containing a page of ScopeLimitationDetail
   */
  public ScopeLimitationDetails getScopeLimitations(
      final ScopeLimitationDetail scopeLimitationDetail, Pageable pageable) {
    ScopeLimitation scopeLimitation =
        scopeLimitationMapper.toScopeLimitation(scopeLimitationDetail);

    return scopeLimitationMapper.toScopeLimitationDetails(
        scopeLimitationRepository.findAll(Example.of(scopeLimitation), pageable));
  }
}
