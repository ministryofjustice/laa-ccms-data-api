package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award.AwardXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.priorauthority.PriorAuthorityXml;

/**
 * Contains various details regarding a case.
 *
 * @see ApplicationDetailsXml
 * @see LinkedCaseXml
 * @see AwardXml
 * @see PriorAuthorityXml
 * @see DischargeStatusXml
 * @see AvailableFunctionsXml
 * @see CaseStatusXml
 * @see RecordHistoryXml
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CaseDetailsXml {

  @JacksonXmlProperty(localName = "ApplicationDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ApplicationDetailsXml applicationDetails;

  @JacksonXmlProperty(localName = "CertificateType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String certificateType;

  @JacksonXmlProperty(localName = "CertificateDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate certificateDate;

  @JacksonXmlProperty(localName = "PreCertificateCosts",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Long preCertificateCosts;

  @JacksonXmlProperty(localName = "LegalHelpCosts",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Long legalHelpCosts;

  @JacksonXmlProperty(localName = "UndertakingAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Long undertakingAmount;

  @JacksonXmlProperty(localName = "LinkedCases",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<LinkedCaseXml> linkedCases;

  @JacksonXmlProperty(localName = "Awards",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<AwardXml> outcomeAwards;

  @JacksonXmlProperty(localName = "PriorAuthorities",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<PriorAuthorityXml> priorAuthorities;

  @JacksonXmlProperty(localName = "DischargeStatus",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private DischargeStatusXml dischargeStatus;

  @JacksonXmlProperty(localName = "AvailableFunctions",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<String> availableFunctions;

  @JacksonXmlProperty(localName = "CaseStatus",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private CaseStatusXml caseStatus;

  @JacksonXmlProperty(localName = "RecordHistory",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private RecordHistoryXml recordHistory;

}
