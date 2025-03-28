package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails;

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
 * Contains Proceeding section for the application details section of a case.
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ProceedingXml {

  @JacksonXmlProperty(localName = "ProceedingCaseID", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String proceedingCaseId;

  @JacksonXmlProperty(localName = "DateApplied", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate dateApplied;

  @JacksonXmlProperty(localName = "Status", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String status;

  @JacksonXmlProperty(localName = "LeadProceedingIndicator", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean leadProceedingIndicator;

  @JacksonXmlProperty(localName = "OutcomeCourtCaseNumber", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String outcomeCourtCaseNumber;

  @JacksonXmlProperty(localName = "AvailableFunctions", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<String> availableFunctions;

  @JacksonXmlProperty(localName = "ProceedingDetails", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ProceedingDetailsXml proceedingDetails;

}
