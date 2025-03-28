package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.ClientXml;

/**
 * Contains information regarding a case which is linked to another case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class LinkedCaseXml {

  @JacksonXmlProperty(localName = "CaseReferenceNumber",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String caseReferenceNumber;

  @JacksonXmlProperty(localName = "ProviderReferenceNumber",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String providerReferenceNumber;

  @JacksonXmlProperty(localName = "Client",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ClientXml client;

  @JacksonXmlProperty(localName = "CategoryOfLawCode",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String categoryOfLawCode;

  @JacksonXmlProperty(localName = "CategoryOfLawDesc",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String categoryOfLawDescription;

  @JacksonXmlProperty(localName = "FeeEarnerID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String feeEarnerId;

  @JacksonXmlProperty(localName = "FeeEarnerName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String feeEarnerName;

  @JacksonXmlProperty(localName = "PublicFundingAppliedIndicator",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean publicFundingAppliedIndicator;

  @JacksonXmlProperty(localName = "LinkType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String linkType;

  @JacksonXmlProperty(localName = "CaseStatus",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String caseStatus;
}
