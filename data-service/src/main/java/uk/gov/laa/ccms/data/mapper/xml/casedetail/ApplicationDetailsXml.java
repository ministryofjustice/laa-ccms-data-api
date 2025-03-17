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
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDetailsXml {

  @JacksonXmlProperty(localName = "Client", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ClientXml client;

  @JacksonXmlProperty(localName = "PreferredAddress", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String preferredAddress;

  @JacksonXmlProperty(localName = "CorrespondenceAddress", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private CorrespondenceAddressXml correspondenceAddress;

  @JacksonXmlProperty(localName = "ProviderDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ProviderDetailsXml providerDetails;

  @JacksonXmlProperty(localName = "CategoryOfLaw", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private CategoryOfLawXml categoryOfLaw;

  @JacksonXmlProperty(localName = "OtherParties", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<OtherPartyXml> otherParties;

  // TODO: XML External resources
  @JacksonXmlProperty(localName = "Proceedings", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<ProceedingXml> proceedings;

  // TODO: XML Means Assesments
  // TODO: XML Merits Assesments

  @JacksonXmlProperty(localName = "DateOfFirstAttendance", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate dateOfFirstAttendance;

  @JacksonXmlProperty(localName = "PurposeOfApplication", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String purposeOfApplication;

  @JacksonXmlProperty(localName = "FixedHearingDateInd", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean fixedHearingDateInd;

  @JacksonXmlProperty(localName = "HighProfileCaseInd", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean highProfileCaseInd;

  @JacksonXmlProperty(localName = "ApplicationAmendmentType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String applicationAmendmentType;

  // TODO: XML LARDetails



}
