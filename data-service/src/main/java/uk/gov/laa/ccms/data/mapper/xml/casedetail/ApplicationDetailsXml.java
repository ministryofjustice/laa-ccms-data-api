package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.CategoryOfLawXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.ClientXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.CorrespondenceAddressXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.LARDetailsXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.ProceedingXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.ProviderDetailsXml;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class ApplicationDetailsXml {

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

  @JacksonXmlProperty(localName = "Proceedings", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<ProceedingXml> proceedings;

  @JacksonXmlProperty(localName = "PurposeOfApplication", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String purposeOfApplication;

  @JacksonXmlProperty(localName = "FixedHearingDateInd", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean fixedHearingDateIndicator;

  @JacksonXmlProperty(localName = "PurposeOfHearing", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String purposeOfHearing;

  @JacksonXmlProperty(localName = "HighProfileCaseInd", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean highProfileCaseIndicator;

  @JacksonXmlProperty(localName = "ApplicationAmendmentType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String applicationAmendmentType;

  @JacksonXmlProperty(localName = "LARDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LARDetailsXml larDetails;

}
