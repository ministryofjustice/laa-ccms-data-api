package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.CaseDetailXml;
import uk.gov.laa.ccms.data.model.CaseDetail;

@Mapper(componentModel = "spring")
public interface CaseDetailsMapper {

  @Mapping(target = "certificateType", source = "caseDetails.certificateType")
  @Mapping(target = "certificateDate", source = "caseDetails.certificateDate")
  @Mapping(target = "preCertificateCosts", source = "caseDetails.preCertificateCosts")
  @Mapping(target = "legalHelpCosts", source = "caseDetails.legalHelpCosts")
  CaseDetail mapToCaseDetail(CaseDetailXml caseInqRSXml);

}
