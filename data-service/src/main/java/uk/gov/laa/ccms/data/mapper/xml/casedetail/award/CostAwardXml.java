package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  private String preCertificateAwardLSC;

  @JacksonXmlProperty(localName = "PreCertificateAwardOth", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String preCertificateAwardOth;

  @JacksonXmlProperty(localName = "CertificateCostRateLSC", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String certificateCostRateLSC;

  @JacksonXmlProperty(localName = "CertificateCostRateMarket", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String certificateCostRateMarket;

  @JacksonXmlProperty(localName = "AwardedBy", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardedBy;

  @JacksonXmlProperty(localName = "InterestAwardedRate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String interestAwardedRate;

  @JacksonXmlProperty(localName = "LiableParties", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<LiablePartyXml> liableParties;

  @JacksonXmlProperty(localName = "Recovery", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<RecoveryXml> recovery;

}
