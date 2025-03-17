package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProceedingXml {

  @JacksonXmlProperty(localName = "OtherParties", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String proceedingCaseID;

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
