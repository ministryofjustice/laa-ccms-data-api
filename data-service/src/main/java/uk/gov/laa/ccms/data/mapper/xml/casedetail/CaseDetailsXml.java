package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.AwardXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.priorauthority.PriorAuthorityXml;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CaseDetailsXml {

  @JacksonXmlProperty(localName = "ApplicationDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ApplicationDetailsXml applicationDetails;

  @JacksonXmlProperty(localName = "CertificateType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String certificateType;

  @JacksonXmlProperty(localName = "CertificateDate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate certificateDate;

  @JacksonXmlProperty(localName = "PreCertificateCosts", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Long preCertificateCosts;

  @JacksonXmlProperty(localName = "LegalHelpCosts", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Long legalHelpCosts;

  @JacksonXmlProperty(localName = "UndertakingAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Long undertakingAmount;

  @JacksonXmlProperty(localName = "LinkedCases", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<LinkedCaseXml> linkedCases;

  @JacksonXmlProperty(localName = "Awards", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<AwardXml> outcomeAwards;

  @JacksonXmlProperty(localName = "PriorAuthorities", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<PriorAuthorityXml> priorAuthorities;

  @JacksonXmlProperty(localName = "DischargeStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private DischargeStatusXml dischargeStatus;

  @JacksonXmlProperty(localName = "AvailableFunctions", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private AvailableFunctionsXml availableFunctions;

  @JacksonXmlProperty(localName = "CaseStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private CaseStatusXml caseStatus;

  @JacksonXmlProperty(localName = "RecordHistory", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private RecordHistoryXml recordHistory;

}
