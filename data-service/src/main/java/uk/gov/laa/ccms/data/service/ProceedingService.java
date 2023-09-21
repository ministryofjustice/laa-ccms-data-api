package uk.gov.laa.ccms.data.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.Proceeding;
import uk.gov.laa.ccms.data.mapper.ProceedingMapper;
import uk.gov.laa.ccms.data.model.ProceedingDetail;
import uk.gov.laa.ccms.data.model.ProceedingDetails;
import uk.gov.laa.ccms.data.repository.ProceedingRepository;

/**
 * Service class responsible for managing {@link Proceeding} entities.
 *
 * <p>This service provides methods to retrieve and manipulate {@code Proceeding} entities,
 * primarily through interactions with the {@link ProceedingRepository}.</p>
 *
 * @see Service
 * @see Proceeding
 * @see ProceedingRepository
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProceedingService {

  private final ProceedingRepository proceedingRepository;

  private final ProceedingMapper proceedingMapper;

  /**
   * Get Proceedings which match the provided category of law, matter type,
   * amendment only, and enabled values.
   *
   * @param categoryOfLawCode - the category of law code
   * @param matterType - the matter type
   * @param enabled - whether the proceeding is enabled
   * @param amendmentOnly - whether the proceeding is amendment-only
   * @param pageable - the pageable settings
   * @return a ProceedingDetails containing zero or more Proceedings that match the criteria
   */
  public ProceedingDetails getProceedings(String categoryOfLawCode, String matterType,
      Boolean amendmentOnly, Boolean enabled, Pageable pageable) {
    Proceeding proceeding = new Proceeding();
    proceeding.setCategoryOfLawCode(categoryOfLawCode);
    proceeding.setMatterType(matterType);
    proceeding.setAmendmentOnly(amendmentOnly);
    proceeding.setEnabled(enabled);

    return proceedingMapper.toProceedingDetails(
        proceedingRepository.findAll(Example.of(proceeding), pageable));
  }

  public Optional<ProceedingDetail> getProceeding(String code) {
    return proceedingRepository.findById(code)
        .map(proceedingMapper::toProceedingDetail);
  }
}
