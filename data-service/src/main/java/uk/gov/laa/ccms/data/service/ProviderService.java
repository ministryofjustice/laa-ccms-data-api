package uk.gov.laa.ccms.data.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.mapper.ProviderMapper;
import uk.gov.laa.ccms.data.model.ProviderDetail;
import uk.gov.laa.ccms.data.repository.ProviderRepository;

/**
 * Service class responsible for managing {@link Provider} entities.
 *
 * <p>This service provides methods to retrieve and manipulate {@code Provider} entities,
 * primarily through interactions with the {@link ProviderRepository}.</p>
 *
 * @see Service
 * @see Provider
 * @see ProviderRepository
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderService {

  private final ProviderRepository providerRepository;

  private final ProviderMapper providerMapper;

  public Optional<ProviderDetail> getProvider(Integer providerId) {
    return providerRepository.findById(providerId)
        .map(providerMapper::toProviderDetail);
  }
}
