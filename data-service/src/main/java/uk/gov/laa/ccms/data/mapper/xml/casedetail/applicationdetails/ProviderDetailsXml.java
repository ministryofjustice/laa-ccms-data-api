package uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.ContactUserIdXml;

/**
 * Contains details regarding a provider for the application details of a case.
 *
 * @see uk.gov.laa.ccms.data.mapper.xml.casedetail.CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ProviderDetailsXml {

  @JacksonXmlProperty(localName = "ProviderCaseReferenceNumber",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String providerCaseReferenceNumber;

  @JacksonXmlProperty(localName = "ProviderFirmID",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Integer providerFirmId;

  @JacksonXmlProperty(localName = "ProviderOfficeID",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Integer providerOfficeId;

  @JacksonXmlProperty(localName = "ContactUserID",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ContactUserIdXml contactUserId;


}
