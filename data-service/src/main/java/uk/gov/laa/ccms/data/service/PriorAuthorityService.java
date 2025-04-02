package uk.gov.laa.ccms.data.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.PriorAuthorityType;
import uk.gov.laa.ccms.data.mapper.PriorAuthorityDetailMapper;
import uk.gov.laa.ccms.data.model.PriorAuthorityTypeDetails;
import uk.gov.laa.ccms.data.repository.PriorAuthorityRepository;
import uk.gov.laa.ccms.data.repository.ProviderRepository;

/**
 * Service class responsible for managing {@link uk.gov.laa.ccms.data.entity.PriorAuthorityType}
 * entities.
 *
 * <p>This service provides methods to retrieve and manipulate {@code Provider} entities,
 * primarily through interactions with the {@link ProviderRepository}.</p>
 *
 * @see Service
 * @see PriorAuthorityType
 * @see PriorAuthorityRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PriorAuthorityService extends AbstractEbsDataService {

  private final PriorAuthorityRepository priorAuthorityRepository;

  private final PriorAuthorityDetailMapper priorAuthorityDetailMapper;

  /**
   * Get a List of PriorAuthorityTypes for the provided code and valueRequired values.
   *
   * @param code - the prior authority type code.
   * @param valueRequired - the value required flag.
   * @param pageable - the pageable settings
   * @return PriorAuthorityTypeDetails containing a Page of PriorAuthorityType objects.
   */
  public PriorAuthorityTypeDetails getPriorAuthorityTypes(String code, Boolean valueRequired,
      Pageable pageable) {
    PriorAuthorityType example = new PriorAuthorityType();
    example.setCode(code);
    example.setValueRequired(valueRequired);

    return priorAuthorityDetailMapper.toPriorAuthorityTypeDetails(
        priorAuthorityRepository.findAll(Example.of(example), pageable));
  }
}
