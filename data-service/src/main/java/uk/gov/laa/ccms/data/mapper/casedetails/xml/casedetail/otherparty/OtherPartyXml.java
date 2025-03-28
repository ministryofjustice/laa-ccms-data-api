package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseDetailsXml;

/**
 * Contains other party details for a case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class OtherPartyXml {

  @JacksonXmlProperty(localName = "OtherPartyID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String otherPartyId;

  @JacksonXmlProperty(localName = "SharedInd",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean sharedIndicator;

  @JacksonXmlProperty(localName = "OtherPartyDetail",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private OtherPartyDetailXml otherPartyDetail;



}
