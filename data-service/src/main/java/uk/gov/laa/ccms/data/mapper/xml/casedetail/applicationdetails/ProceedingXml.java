package uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @JacksonXmlProperty(localName = "ProceedingCaseID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String proceedingCaseId;

  @JacksonXmlProperty(localName = "DateApplied", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate dateApplied;

  @JacksonXmlProperty(localName = "Status", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String status;

  @JacksonXmlProperty(localName = "LeadProceedingIndicator", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean leadProceedingIndicator;

  @JacksonXmlProperty(localName = "OutcomeCourtCaseNumber", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String outcomeCourtCaseNumber;

  @JacksonXmlProperty(localName = "AvailableFunctions", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<String> availableFunctions;

  @JacksonXmlProperty(localName = "ProceedingDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ProceedingDetailsXml proceedingDetails;

}
