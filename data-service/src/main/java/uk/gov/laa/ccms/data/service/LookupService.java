package uk.gov.laa.ccms.data.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.AwardTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValueId;
import uk.gov.laa.ccms.data.entity.PersonRelationshipToCaseLookupValue;
import uk.gov.laa.ccms.data.entity.StageEndLookupValue;
import uk.gov.laa.ccms.data.entity.StageEndLookupValueId;
import uk.gov.laa.ccms.data.mapper.LookupMapper;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupDetail;
import uk.gov.laa.ccms.data.repository.AmendmentTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.AwardTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CaseStatusLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CommonLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CountryLookupValueRepository;
import uk.gov.laa.ccms.data.repository.OutcomeResultLookupValueRepository;
import uk.gov.laa.ccms.data.repository.PersonRelationshipToCaseLookupValueRepository;
import uk.gov.laa.ccms.data.repository.StageEndLookupValueRepository;

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

  private final OutcomeResultLookupValueRepository outcomeResultLookupValueRepository;

  private final StageEndLookupValueRepository stageEndLookupValueRepository;

  private final PersonRelationshipToCaseLookupValueRepository
      personRelationshipToCaseLookupValueRepository;

  private final AwardTypeLookupValueRepository awardTypeLookupValueRepository;

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

  /**
   * Retrieves a page of outcome result values based on the provided search criteria.
   *
   * @param proceedingCode  the proceeding code
   * @param outcomeResult the outcome result
   * @param pageable pagination information
   * @return a OutcomeResultLookupDetail containing a page of outcome result values
   */
  public OutcomeResultLookupDetail getOutcomeResultLookupValues(
      String proceedingCode, String outcomeResult, Pageable pageable) {
    OutcomeResultLookupValue example = new OutcomeResultLookupValue();
    example.setId(new OutcomeResultLookupValueId());
    example.getId().setProceedingCode(proceedingCode);
    example.getId().setOutcomeResult(outcomeResult);

    return lookupMapper.toOutcomeResultLookupDetail(
        outcomeResultLookupValueRepository.findAll(Example.of(example), pageable));
  }

  /**
   * Retrieves a page of stage end values based on the provided search criteria.
   *
   * @param proceedingCode  the proceeding code
   * @param stageEnd the stage end value
   * @param pageable pagination information
   * @return a StageEndLookupDetail containing a page of stage end values
   */
  public StageEndLookupDetail getStageEndLookupValues(
      String proceedingCode, String stageEnd, Pageable pageable) {
    StageEndLookupValue example = new StageEndLookupValue();
    example.setId(new StageEndLookupValueId());
    example.getId().setProceedingCode(proceedingCode);
    example.getId().setStageEnd(stageEnd);

    return lookupMapper.toStageEndLookupDetail(
        stageEndLookupValueRepository.findAll(Example.of(example), pageable));
  }

  /**
   * Retrieves a page of person to case relationship lookup values.
   *
   * @param code the relationship code
   * @param description  the relationship description
   * @param pageable    pagination information
   * @return a RelationshipToCaseLookupDetail containing a page of person to case relationships
   */
  public RelationshipToCaseLookupDetail getPersonToCaseRelationshipLookupValues(
      String code, String description, Pageable pageable
  ) {
    PersonRelationshipToCaseLookupValue example = new PersonRelationshipToCaseLookupValue();
    example.setCode(code);
    example.setDescription(description);

    return lookupMapper.toRelationshipToCaseLookupDetail(
        personRelationshipToCaseLookupValueRepository.findAll(Example.of(example), pageable));

  }

  /**
   * Retrieves a page of award type values based on the provided search criteria.
   *
   * @param code  the award type code
   * @param awardType the award type
   * @param pageable pagination information
   * @return a AwardTypeLookupDetail containing a page of award type values
   */
  public AwardTypeLookupDetail getAwardTypeLookupValues(
      String code, String awardType, Pageable pageable) {
    AwardTypeLookupValue example = new AwardTypeLookupValue();
    example.setCode(code);
    example.setAwardType(awardType);

    return lookupMapper.toAwardTypeLookupDetail(
        awardTypeLookupValueRepository.findAll(Example.of(example), pageable));
  }

}
