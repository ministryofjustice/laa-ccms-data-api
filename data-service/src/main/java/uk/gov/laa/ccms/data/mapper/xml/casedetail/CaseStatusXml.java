package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaseStatusXml {

  @JacksonXmlProperty(localName = "ActualCaseStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String actualCaseStatus;
  @JacksonXmlProperty(localName = "DisplayCaseStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String displayCaseStatus;
  @JacksonXmlProperty(localName = "StatusUpdateInd", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean statusUpdateInd;
}
