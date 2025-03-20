package uk.gov.laa.ccms.data.mapper.casedetails;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.CaseDetailXml;
import uk.gov.laa.ccms.data.model.CaseDetail;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses =
    {SubmittedApplicationDetailsMapper.class, LinkedCaseMapper.class})
public interface CaseDetailsMapper {

  @Mapping(target = "certificateType", source = "caseDetails.certificateType")
  @Mapping(target = "certificateDate", source = "caseDetails.certificateDate")
  @Mapping(target = "preCertificateCosts", source = "caseDetails.preCertificateCosts")
  @Mapping(target = "legalHelpCosts", source = "caseDetails.legalHelpCosts")
  @Mapping(target = "applicationDetails", source = "caseDetails.applicationDetails")
  CaseDetail mapToCaseDetail(CaseDetailXml caseInqRSXml);

}
