package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseDetailsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.common.ContactUserIdXml;

/**
 * Contains details regarding a provider for the application details of a case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ProviderDetailsXml {

  @JacksonXmlProperty(localName = "ProviderCaseReferenceNumber",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String providerCaseReferenceNumber;

  @JacksonXmlProperty(localName = "ProviderFirmID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Integer providerFirmId;

  @JacksonXmlProperty(localName = "ProviderOfficeID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Integer providerOfficeId;

  @JacksonXmlProperty(localName = "ContactUserID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ContactUserIdXml contactUserId;

  @JacksonXmlProperty(localName = "FeeEarnerContactID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String feeEarnerContactId;

  @JacksonXmlProperty(localName = "SupervisorContactID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String supervisorContactId;


}
