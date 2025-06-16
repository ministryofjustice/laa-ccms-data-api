package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains Outcome section for the proceeding details in the application details section of a case.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class OutcomeXml {

  @JacksonXmlProperty(localName = "IssueDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate issueDate;

  @JacksonXmlProperty(localName = "FinalWorkDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate finalWorkDate;

  @JacksonXmlProperty(localName = "StageEnd",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String stageEnd;

  @JacksonXmlProperty(localName = "ResolutionMethod",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String resolutionMethod;

  @JacksonXmlProperty(localName = "Result",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String result;

  @JacksonXmlProperty(localName = "AdditionalResultInfo",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String additionalResultInfo;

  @JacksonXmlProperty(localName = "AltDisputeResolution",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String altDisputeResolution;

  @JacksonXmlProperty(localName = "AltAcceptanceReason",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String altAcceptanceReason;

  @JacksonXmlProperty(localName = "CourtCode",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String courtCode;

  @JacksonXmlProperty(localName = "WiderBenefits",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String widerBenefits;

  @JacksonXmlProperty(localName = "OutcomeCourtCaseNumber",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String outcomeCourtCaseNumber;
}
