package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains the discharge information regarding a case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class DischargeStatusXml {

  @JacksonXmlProperty(localName = "Reason",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String reason;

  @JacksonXmlProperty(localName = "ClientContinuePvtInd",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean clientContinuePvtInd;

  @JacksonXmlProperty(localName = "OtherDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String otherDetails;
}
