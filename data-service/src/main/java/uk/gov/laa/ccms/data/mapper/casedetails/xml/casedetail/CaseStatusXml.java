package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

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

  @JacksonXmlProperty(localName = "ActualCaseStatus",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String actualCaseStatus;
  @JacksonXmlProperty(localName = "DisplayCaseStatus",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String displayCaseStatus;
  @JacksonXmlProperty(localName = "StatusUpdateInd",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean statusUpdateInd;
}
