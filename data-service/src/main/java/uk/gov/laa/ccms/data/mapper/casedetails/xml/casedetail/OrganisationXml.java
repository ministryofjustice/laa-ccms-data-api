package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.common.AddressXml;

/**
 * Contains information regarding an Organisation which is linked to a case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class OrganisationXml {

  @JacksonXmlProperty(localName = "OrganizationName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String organisationName;

  @JacksonXmlProperty(localName = "OrganizationType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String organisationType;

  @JacksonXmlProperty(localName = "CurrentlyTrading",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String currentlyTradingFlag;

  public boolean getCurrentlyTrading() {
    return "Y".equals(currentlyTradingFlag);
  }

  @JacksonXmlProperty(localName = "RelationToClient",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String relationToClient;

  @JacksonXmlProperty(localName = "RelationToCase",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String relationToCase;

  @JacksonXmlProperty(localName = "Address",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private AddressXml address;

  @JacksonXmlProperty(localName = "ContactName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String contactName;

  @JacksonXmlProperty(localName = "OtherInformation",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String otherInformation;

}
