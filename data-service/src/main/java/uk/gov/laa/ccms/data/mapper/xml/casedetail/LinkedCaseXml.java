package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.ClientXml;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class LinkedCaseXml {

  @JacksonXmlProperty(localName = "CaseReferenceNumber", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String caseReferenceNumber;

  @JacksonXmlProperty(localName = "ProviderReferenceNumber", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String providerReferenceNumber;

  @JacksonXmlProperty(localName = "Client", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ClientXml client;

  @JacksonXmlProperty(localName = "CategoryOfLawCode", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String categoryOfLawCode;

  @JacksonXmlProperty(localName = "CategoryOfLawDesc", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String categoryOfLawDescription;

  @JacksonXmlProperty(localName = "FeeEarnerID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String feeEarnerId;

  @JacksonXmlProperty(localName = "FeeEarnerName", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String feeEarnerName;

  @JacksonXmlProperty(localName = "PublicFundingAppliedIndicator", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean publicFundingAppliedIndicator;

  @JacksonXmlProperty(localName = "LinkType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String linkType;

  @JacksonXmlProperty(localName = "CaseStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String caseStatus;
}
