package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseDetailsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.OrganisationXml;

/**
 * Contains other party details for a case.
 *
 * @see CaseDetailsXml
 * @see PersonXml
 * @see OrganisationXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class OtherPartyDetailXml {

  @JacksonXmlProperty(localName = "Person",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private PersonXml person;
  @JacksonXmlProperty(localName = "Organization",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private OrganisationXml organisation;

}
