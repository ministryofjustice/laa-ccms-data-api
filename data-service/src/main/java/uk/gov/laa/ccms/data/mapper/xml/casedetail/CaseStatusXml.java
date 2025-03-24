package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains information regarding the status of a case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CaseStatusXml {

  @JacksonXmlProperty(localName = "ActualCaseStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String actualCaseStatus;
  @JacksonXmlProperty(localName = "DisplayCaseStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String displayCaseStatus;
  @JacksonXmlProperty(localName = "StatusUpdateInd", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean statusUpdateInd;
}
