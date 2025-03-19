package uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.ContactUserIDXml;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ProviderDetailsXml {

  @JacksonXmlProperty(localName = "ProviderCaseReferenceNumber", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String providerCaseReferenceNumber;

  @JacksonXmlProperty(localName = "ProviderFirmID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Integer providerFirmID;

  @JacksonXmlProperty(localName = "ProviderOfficeID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Integer providerOfficeID;

  @JacksonXmlProperty(localName = "ContactUserID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ContactUserIDXml contactUserID;


}
