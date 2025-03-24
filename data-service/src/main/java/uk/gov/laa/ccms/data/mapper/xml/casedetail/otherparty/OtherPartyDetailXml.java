package uk.gov.laa.ccms.data.mapper.xml.casedetail.otherparty;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.OrganisationXml;

/**
 * Contains other party details for a case.
 *
 * @see uk.gov.laa.ccms.data.mapper.xml.casedetail.CaseDetailsXml
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

  @JacksonXmlProperty(localName = "Person", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private PersonXml person;
  @JacksonXmlProperty(localName = "Organization", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private OrganisationXml organisation;

}
