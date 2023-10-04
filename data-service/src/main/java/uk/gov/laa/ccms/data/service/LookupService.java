package uk.gov.laa.ccms.data.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.mapper.LookupMapper;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.repository.AmendmentTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CaseStatusLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CommonLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CountryLookupValueRepository;

/**
 * Service class for managing common values.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LookupService extends AbstractEbsDataService {

  private final CommonLookupValueRepository commonLookupValueRepository;

  private final CaseStatusLookupValueRepository caseStatusLookupValueRepository;

  private final AmendmentTypeLookupValueRepository amendmentTypeLookupValueRepository;

  private final CountryLookupValueRepository countryLookupValueRepository;

  private final LookupMapper lookupMapper;

  /**
   * Retrieves a page of common values based on the provided
   * type, code, description and pagination information.
   * If wildcard is true, a 'like' query will be performed
   * for all three attributes.
   *
   * @param type  the type of common value
   * @param code  the code of common value
   * @param description the description of the common value
   * @param pageable pagination information
   * @return a CommonLookupDetail containing a page of common values
   */
  public CommonLookupDetail getCommonLookupValues(
          String type, String code, String description, Pageable pageable) {
    CommonLookupValue example = new CommonLookupValue();
    example.setType(type);
    example.setCode(code);
    example.setDescription(description);

    return lookupMapper.toCommonLookupDetail(
        commonLookupValueRepository.findAll(Example.of(example,
            getWildcardMatcher(example)), pageable));
  }

  /**
   * Retrieves a page of case status values based on the provided code, copyAllowed flag
   * and pagination
   * information.
   *
   * @param code  the case status code
   * @param copyAllowed whether copyCase is allowed for the status
   * @param pageable pagination information
   * @return a CaseStatusLookupDetail containing a page of case status values
   */
  public CaseStatusLookupDetail getCaseStatusLookupValues(
          String code, Boolean copyAllowed, Pageable pageable) {
    CaseStatusLookupValue example = new CaseStatusLookupValue();
    example.setCode(code);
    example.setCopyAllowed(copyAllowed);

    return lookupMapper.toCaseStatusLookupDetail(
        caseStatusLookupValueRepository.findAll(Example.of(example), pageable));
  }

  /**
   * Retrieves a page of amendment type values based on the provided application type and pagination
   * information.
   *
   * @param applicationType  the applicationTypeCode of the amendment type values
   * @param pageable pagination information
   * @return an AmendmentTypeLookupDetail containing a page of amendment type values
   */
  public AmendmentTypeLookupDetail getAmendmentTypeLookupValues(
          String applicationType, Pageable pageable) {
    AmendmentTypeLookupValue example = new AmendmentTypeLookupValue();
    example.setApplicationTypeCode(applicationType);

    return lookupMapper.toAmendmentTypeLookupDetail(
        amendmentTypeLookupValueRepository.findAll(Example.of(example), pageable));
  }

  /**
   * Retrieves a page of country values based on the provided country code and pagination
   * information.
   *
   * @param code  the country code
   * @param pageable pagination information
   * @return a CommonLookupDetail containing a page of country values
   */
  public CommonLookupDetail getCountryLookupValues(
          String code, Pageable pageable) {
    CountryLookupValue example = new CountryLookupValue();
    example.setCode(code);

    return lookupMapper.toCommonLookupDetailFromCountries(
        countryLookupValueRepository.findAll(Example.of(example), pageable));
  }

}
