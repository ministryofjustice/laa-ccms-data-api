package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.common.AddressXml;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class OrganizationXml {

  @JacksonXmlProperty(localName = "OrganizationName", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String organizationName;

  @JacksonXmlProperty(localName = "OrganizationType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String organizationType;

  @JacksonXmlProperty(localName = "CurrentlyTrading", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String currentlyTrading;

  @JacksonXmlProperty(localName = "RelationToClient", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String relationToClient;

  @JacksonXmlProperty(localName = "RelationToCase", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String relationToCase;

  @JacksonXmlProperty(localName = "Address", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private AddressXml address;

  @JacksonXmlProperty(localName = "ContactName", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String contactName;

}
