package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains cost award information found in award details.
 *
 * @see AwardDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CostAwardXml {

  @JacksonXmlProperty(localName = "OrderDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate orderDate;

  @JacksonXmlProperty(localName = "CourtAssessmentStatus",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String courtAssessmentStatus;

  @JacksonXmlProperty(localName = "PreCertificateAwardLSC",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String preCertificateAwardLsc;

  @JacksonXmlProperty(localName = "PreCertificateAwardOth",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String preCertificateAwardOth;

  @JacksonXmlProperty(localName = "CertificateCostRateLSC",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String certificateCostRateLsc;

  @JacksonXmlProperty(localName = "CertificateCostRateMarket",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String certificateCostRateMarket;

  @JacksonXmlProperty(localName = "AwardedBy",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardedBy;

  @JacksonXmlProperty(localName = "InterestAwardedRate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String interestAwardedRate;

  @JacksonXmlProperty(localName = "InterestAwardedStartDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate interestAwardedStartDate;

  @JacksonXmlProperty(localName = "OtherDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String otherDetails;

  @JacksonXmlProperty(localName = "LiableParties",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<LiablePartyXml> liableParties;

  @JacksonXmlProperty(localName = "OrderDateServed",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate orderDateServed;

  @JacksonXmlProperty(localName = "Recovery",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private RecoveryXml recovery;

  @JacksonXmlProperty(localName = "ServiceAddress",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ServiceAddressXml serviceAddress;

}
