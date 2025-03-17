package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaseDetailsXml {

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


  // TODO: XML Linked Cases Type
  // TODO: XML Awards Type
  // TODO: XML Prior Authority Type
  @JacksonXmlProperty(localName = "DischargeStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private DischargeStatusXml dischargeStatus;

  // TODO: XML Available Functions Type
  @JacksonXmlProperty(localName = "CaseStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private CaseStatusXml caseStatus;

  @JacksonXmlProperty(localName = "RecordHistory", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private RecordHistoryXml recordHistoryXml;

}
