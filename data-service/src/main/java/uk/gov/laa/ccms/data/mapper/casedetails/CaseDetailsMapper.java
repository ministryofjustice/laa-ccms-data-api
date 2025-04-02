package uk.gov.laa.ccms.data.mapper.casedetails;

import java.util.Collections;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseDetailXml;
import uk.gov.laa.ccms.data.model.CaseDetail;

/**
 * Mapper interface for transforming XML case details objects to their associated domain classes.
 * This interface utilizes MapStruct for mapping properties.
 *
 * <p>Also utilizes other mappers to map the complete case detail object such as:</p>
 * <ul>
 *   <li>{@link SubmittedApplicationDetailsMapper}</li>
 *   <li>{@link LinkedCaseMapper}</li>
 *   <li>{@link AwardMapper}</li>
 *   <li>{@link PriorAuthorityMapper}</li>
 *   <li>{@link RecordHistoryMapper}</li>
 * </ul>
 *
 *
 * @author Jamie Briggs
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses =
      {SubmittedApplicationDetailsMapper.class, LinkedCaseMapper.class, AwardMapper.class,
    PriorAuthorityMapper.class, RecordHistoryMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CaseDetailsMapper {

  @Mapping(target = "certificateType", source = "caseDetails.certificateType")
  @Mapping(target = "certificateDate", source = "caseDetails.certificateDate")
  @Mapping(target = "undertakingAmount", source = "caseDetails.undertakingAmount")
  @Mapping(target = "preCertificateCosts", source = "caseDetails.preCertificateCosts")
  @Mapping(target = "legalHelpCosts", source = "caseDetails.legalHelpCosts")
  @Mapping(target = "applicationDetails", source = "caseDetails.applicationDetails")
  @Mapping(target = "linkedCases", source = "caseDetails.linkedCases")
  @Mapping(target = "awards", source = "caseDetails.outcomeAwards")
  @Mapping(target = "priorAuthorities", source = "caseDetails.priorAuthorities")
  @Mapping(target = "dischargeStatus", source = "caseDetails.dischargeStatus")
  @Mapping(target = "availableFunctions", source = "caseDetails.availableFunctions")
  @Mapping(target = "caseStatus", source = "caseDetails.caseStatus")
  @Mapping(target = "recordHistory", source = "caseDetails.recordHistory")
  @Mapping(target = "caseDocs", ignore = true)
  CaseDetail mapToCaseDetail(CaseDetailXml caseXml);



}
