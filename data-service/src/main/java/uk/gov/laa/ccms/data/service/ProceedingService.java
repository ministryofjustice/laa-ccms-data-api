package uk.gov.laa.ccms.data.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.BooleanConverter;
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
public class ProceedingService extends AbstractEbsDataService {

  private final ProceedingRepository proceedingRepository;

  private final ProceedingMapper proceedingMapper;


  /**
   * Get Proceedings which match the provided category of law, matter type,
   * amendment only, and enabled values.
   *
   * @param categoryOfLawCode The code representing the category of law.
   * @param matterType The type of matter.
   * @param amendmentOnly A flag indicating whether the proceeding is amendment-only.
   * @param enabled A flag indicating whether the proceeding is enabled.
   * @param pageable The pagination information.
   * @return A {@link ProceedingDetails} object containing zero or more {@link Proceeding} entities
   *         that match the criteria.
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

  /**
   * Retrieves a list of lead {@link Proceeding} entities that match the provided criteria.
   *
   * @param categoryOfLaw The category of law.
   * @param matterType The type of matter.
   * @param amendmentOnly A flag indicating whether the proceeding is amendment-only.
   * @param enabled A flag indicating whether the proceeding is enabled.
   * @param applicationType The type of application.
   * @param larScopeFlag The scope of the LAR.
   * @param pageable The pagination information.
   * @return A {@link ProceedingDetails} object containing zero or more lead {@link Proceeding}
   *         entities that match the criteria.
   */
  public ProceedingDetails getLeadProceedings(
      String categoryOfLaw,
      String matterType,
      Boolean amendmentOnly,
      Boolean enabled,
      String applicationType,
      Boolean larScopeFlag,
      Pageable pageable) {

    BooleanConverter booleanConverter = new BooleanConverter();

    return proceedingMapper.toProceedingDetails(
        proceedingRepository.findAllLeadProceedings(
            categoryOfLaw,
            matterType,
            booleanConverter.convertToDatabaseColumn(amendmentOnly),
            booleanConverter.convertToDatabaseColumn(enabled),
            booleanConverter.convertToDatabaseColumn(larScopeFlag),
            applicationType,
            pageable));
  }

  public Optional<ProceedingDetail> getProceeding(String code) {
    return proceedingRepository.findById(code)
        .map(proceedingMapper::toProceedingDetail);
  }
}
