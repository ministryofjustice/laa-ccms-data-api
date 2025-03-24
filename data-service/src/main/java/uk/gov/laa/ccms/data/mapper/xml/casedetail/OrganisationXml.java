package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.common.AddressXml;

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

  @JacksonXmlProperty(localName = "OrganizationName", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String organisationName;

  @JacksonXmlProperty(localName = "OrganizationType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String organisationType;

  @JacksonXmlProperty(localName = "CurrentlyTrading", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String currentlyTradingFlag;

  public boolean getCurrentlyTrading() {
    return "Y".equals(currentlyTradingFlag);
  }

  @JacksonXmlProperty(localName = "RelationToClient", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String relationToClient;

  @JacksonXmlProperty(localName = "RelationToCase", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String relationToCase;

  @JacksonXmlProperty(localName = "Address", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private AddressXml address;

  @JacksonXmlProperty(localName = "ContactName", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String contactName;

  @JacksonXmlProperty(localName = "OtherInformation", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String otherInformation;

}
