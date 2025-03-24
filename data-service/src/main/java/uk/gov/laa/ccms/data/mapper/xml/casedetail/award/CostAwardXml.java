package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.ServiceAddressXml;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CostAwardXml {

  @JacksonXmlProperty(localName = "OrderDate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate orderDate;

  @JacksonXmlProperty(localName = "CourtAssessmentStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String courtAssessmentStatus;

  @JacksonXmlProperty(localName = "PreCertificateAwardLSC", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String preCertificateAwardLsc;

  @JacksonXmlProperty(localName = "PreCertificateAwardOth", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String preCertificateAwardOth;

  @JacksonXmlProperty(localName = "CertificateCostRateLSC", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String certificateCostRateLsc;

  @JacksonXmlProperty(localName = "CertificateCostRateMarket", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String certificateCostRateMarket;

  @JacksonXmlProperty(localName = "AwardedBy", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardedBy;

  @JacksonXmlProperty(localName = "InterestAwardedRate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String interestAwardedRate;

  @JacksonXmlProperty(localName = "InterestAwardedStartDate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate interestAwardedStartDate;

  @JacksonXmlProperty(localName = "OtherDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String otherDetails;

  @JacksonXmlProperty(localName = "LiableParties", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<LiablePartyXml> liableParties;

  @JacksonXmlProperty(localName = "OrderDateServed", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate orderDateServed;

  @JacksonXmlProperty(localName = "Recovery", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private RecoveryXml recovery;

  @JacksonXmlProperty(localName = "ServiceAddress", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ServiceAddressXml serviceAddress;

}
