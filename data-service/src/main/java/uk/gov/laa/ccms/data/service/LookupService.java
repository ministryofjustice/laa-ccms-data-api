package uk.gov.laa.ccms.data.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.AwardTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CategoryOfLawLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CountryLookupValue;
import uk.gov.laa.ccms.data.entity.EvidenceDocumentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.EvidenceDocumentTypeLookupValueId;
import uk.gov.laa.ccms.data.entity.LevelOfService;
import uk.gov.laa.ccms.data.entity.LevelOfServiceId;
import uk.gov.laa.ccms.data.entity.MatterType;
import uk.gov.laa.ccms.data.entity.OrganisationRelationshipToCaseLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValue;
import uk.gov.laa.ccms.data.entity.OutcomeResultLookupValueId;
import uk.gov.laa.ccms.data.entity.PersonRelationshipToCaseLookupValue;
import uk.gov.laa.ccms.data.entity.ProceedingClientInvolvementType;
import uk.gov.laa.ccms.data.entity.ProceedingClientInvolvementTypeId;
import uk.gov.laa.ccms.data.entity.StageEndLookupValue;
import uk.gov.laa.ccms.data.entity.StageEndLookupValueId;
import uk.gov.laa.ccms.data.mapper.LookupMapper;
import uk.gov.laa.ccms.data.model.AmendmentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.AssessmentSummaryAttributeLookupDetail;
import uk.gov.laa.ccms.data.model.AwardTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.model.CategoryOfLawLookupDetail;
import uk.gov.laa.ccms.data.model.ClientInvolvementTypeLookupDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.EvidenceDocumentTypeLookupDetail;
import uk.gov.laa.ccms.data.model.LevelOfServiceLookupDetail;
import uk.gov.laa.ccms.data.model.MatterTypeLookupDetail;
import uk.gov.laa.ccms.data.model.OutcomeResultLookupDetail;
import uk.gov.laa.ccms.data.model.RelationshipToCaseLookupDetail;
import uk.gov.laa.ccms.data.model.StageEndLookupDetail;
import uk.gov.laa.ccms.data.repository.AmendmentTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.AssessmentSummaryAttributesRepository;
import uk.gov.laa.ccms.data.repository.AwardTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CaseStatusLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CategoryOfLawLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CommonLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CountryLookupValueRepository;
import uk.gov.laa.ccms.data.repository.EvidenceDocumentTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.LevelOfServiceRepository;
import uk.gov.laa.ccms.data.repository.MatterTypeRepository;
import uk.gov.laa.ccms.data.repository.OrganisationRelationshipToCaseLookupValueRepository;
import uk.gov.laa.ccms.data.repository.OutcomeResultLookupValueRepository;
import uk.gov.laa.ccms.data.repository.PersonRelationshipToCaseLookupValueRepository;
import uk.gov.laa.ccms.data.repository.ProceedingClientInvolvementTypeRepository;
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

  private final OrganisationRelationshipToCaseLookupValueRepository
      organisationRelationshipToCaseLookupValueRepository;

  private final AwardTypeLookupValueRepository awardTypeLookupValueRepository;

  private final CategoryOfLawLookupValueRepository categoryOfLawLookupValueRepository;

  private final LookupMapper lookupMapper;

  private final MatterTypeRepository matterTypeRepository;

  private final LevelOfServiceRepository levelOfServiceRepository;

  private final ProceedingClientInvolvementTypeRepository proceedingClientInvolvementTypeRepository;

  private final EvidenceDocumentTypeLookupValueRepository evidenceDocumentTypeLookupValueRepository;

  private final AssessmentSummaryAttributesRepository assessmentSummaryAttributesRepository;


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
      final String type,
      final String code,
      final String description,
      final Pageable pageable) {
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
      final String code,
      final Boolean copyAllowed,
      final Pageable pageable) {
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
      final String applicationType,
      final Pageable pageable) {
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
      final String code,
      final Pageable pageable) {
    CountryLookupValue example = new CountryLookupValue();
    example.setCode(code);

    return lookupMapper.toCommonLookupDetailFromCountries(
        countryLookupValueRepository.findAll(Example.of(example), pageable));
  }

  /**
   * Retrieves a page of MatterType values based on the provided description, matter type, category
   * of law, and pagination information.
   *
   * @param description The description of the MatterType.
   * @param matterType The type of matter.
   * @param categoryOfLaw The category of law.
   * @param pageable The pagination information.
   * @return A {@link MatterTypeLookupDetail} object containing a page of {@link MatterType}
   *         entities that match the criteria.
   */
  public MatterTypeLookupDetail getMatterTypeLookupValues(
      final String description,
      final String matterType,
      final String categoryOfLaw,
      final Pageable pageable) {
    MatterType example = new MatterType();
    example.setMatterType(matterType);
    example.setDescription(description);
    example.setCategoryOfLawCode(categoryOfLaw);

    return lookupMapper.toMatterTypeLookupDetail(
        matterTypeRepository.findAll(Example.of(example), pageable));
  }

  /**
   * Retrieves a page of ProceedingClientInvolvementType values based on the provided proceeding
   * code, client involvement type, and pagination information.
   *
   * @param proceedingCode The code of the proceeding.
   * @param clientInvolvementType The type of client involvement.
   * @param pageable The pagination information.
   * @return A {@link ClientInvolvementTypeLookupDetail} object containing a page of
   *         {@link ProceedingClientInvolvementType} entities that match the criteria.
   */
  public ClientInvolvementTypeLookupDetail getClientInvolvementTypeLookupValues(
      final String proceedingCode,
      final String clientInvolvementType,
      final Pageable pageable) {
    ProceedingClientInvolvementType example = new ProceedingClientInvolvementType();
    example.setId(new ProceedingClientInvolvementTypeId());
    example.getId().setProceedingCode(proceedingCode);
    example.getId().setClientInvolvementType(clientInvolvementType);

    return lookupMapper.toClientInvolvementTypeLookupDetail(
        proceedingClientInvolvementTypeRepository.findAll(Example.of(example), pageable));
  }


  /**
   * Retrieves a page of LevelOfService values based on the provided proceeding code, matter type,
   * category of law, and pagination information.
   *
   * @param proceedingCode The code of the proceeding.
   * @param matterType The type of matter.
   * @param categoryOfLaw The category of law.
   * @param pageable The pagination information.
   * @return A {@link LevelOfServiceLookupDetail} object containing a page of
   *         {@link LevelOfService} entities that match the criteria.
   */
  public LevelOfServiceLookupDetail getLevelOfServiceLookupValues(
      final String proceedingCode,
      final String matterType,
      final String categoryOfLaw,
      final Pageable pageable) {

    LevelOfServiceId exampleId = new LevelOfServiceId();
    exampleId.setMatterType(matterType);
    exampleId.setProceedingCode(proceedingCode);
    exampleId.setCategoryOfLawCode(categoryOfLaw);

    LevelOfService example = new LevelOfService();
    example.setId(exampleId);

    levelOfServiceRepository.findAll(Example.of(example), pageable);

    return lookupMapper.toLevelOfServicePage(
        levelOfServiceRepository.findAll(Example.of(example), pageable));

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
      final String proceedingCode,
      final String outcomeResult,
      final Pageable pageable) {
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
      final String proceedingCode,
      final String stageEnd,
      final Pageable pageable) {
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
      final String code,
      final String description,
      final Pageable pageable) {
    PersonRelationshipToCaseLookupValue example = new PersonRelationshipToCaseLookupValue();
    example.setCode(code);
    example.setDescription(description);

    return lookupMapper.toRelationshipToCaseLookupDetail(
        personRelationshipToCaseLookupValueRepository.findAll(Example.of(example), pageable));

  }

  /**
   * Retrieves a page of organisation to case relationship lookup values.
   *
   * @param code the relationship code
   * @param description  the relationship description
   * @param pageable    pagination information
   * @return a RelationshipToCaseLookupDetail containing a page of organisation to case
   *         relationships
   */
  public RelationshipToCaseLookupDetail getOrganisationToCaseRelationshipLookupValues(
      final String code,
      final String description,
      final Pageable pageable) {
    OrganisationRelationshipToCaseLookupValue example
        = new OrganisationRelationshipToCaseLookupValue();
    example.setCode(code);
    example.setDescription(description);

    return lookupMapper.toOrgRelationshipToCaseLookupDetail(
        organisationRelationshipToCaseLookupValueRepository.findAll(Example.of(example), pageable));
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
      final String code,
      final String awardType,
      final Pageable pageable) {
    AwardTypeLookupValue example = new AwardTypeLookupValue();
    example.setCode(code);
    example.setAwardType(awardType);

    return lookupMapper.toAwardTypeLookupDetail(
        awardTypeLookupValueRepository.findAll(Example.of(example), pageable));
  }

  /**
   * Retrieves a page of category of law lookup values based on the provided search criteria.
   *
   * @param code  the category of law code
   * @param matterTypeDescription the matter type description
   * @param copyCostLimit the copy cost limit value
   * @param pageable pagination information
   * @return a CategoryOfLawLookupDetail containing a page of category of law values
   */
  public CategoryOfLawLookupDetail getCategoryOfLawLookupValues(
      final String code,
      final String matterTypeDescription,
      final Boolean copyCostLimit,
      final Pageable pageable) {
    CategoryOfLawLookupValue example = new CategoryOfLawLookupValue();
    example.setCode(code);
    example.setMatterTypeDescription(matterTypeDescription);
    example.setCopyCostLimit(copyCostLimit);

    return lookupMapper.toCategoryOfLawLookupDetail(
        categoryOfLawLookupValueRepository.findAll(Example.of(example), pageable));
  }

  /**
   * Retrieves a page of evidence document type lookup values based on the provided search criteria.
   *
   * @param type  the lookup type
   * @param code the evidence document type code
   * @param pageable pagination information
   * @return a EvidenceDocumentTypeLookupDetail containing a page of evidence document type values.
   */
  public EvidenceDocumentTypeLookupDetail getEvidenceDocumentTypeLookupValues(
      final String type,
      final String code,
      final Pageable pageable) {
    EvidenceDocumentTypeLookupValue example = new EvidenceDocumentTypeLookupValue();
    example.setId(new EvidenceDocumentTypeLookupValueId(type, code));

    return lookupMapper.toEvidenceDocumentTypeLookupDetail(
        evidenceDocumentTypeLookupValueRepository.findAll(Example.of(example), pageable));
  }

  /**
   * Retrieves the assessment summary attributes based on the provided summary type and
   * pagination details.
   *
   * @param summaryType the type of summary to filter by
   * @param pageable    the pagination information
   * @return the detailed summary attributes lookup
   */
  public AssessmentSummaryAttributeLookupDetail getAssessmentSummaryAttributes(
      String summaryType,
      final Pageable pageable) {

    return lookupMapper.toAssessmentSummaryAttributeLookupDetail(
        assessmentSummaryAttributesRepository.findAllSummaryAttributes(
            Optional.ofNullable(summaryType)
                .map(String::toUpperCase)
                .orElse(null), pageable));
  }

}
