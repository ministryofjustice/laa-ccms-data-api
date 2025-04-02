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
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.CategoryOfLawXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.ClientXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.CorrespondenceAddressXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.LarDetailsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.ProceedingXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.ProviderDetailsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty.OtherPartyXml;

/**
 * Contains the application details for a case.
 *
 * @see CorrespondenceAddressXml
 * @see ProviderDetailsXml
 * @see CategoryOfLawXml
 * @see OtherPartyXml
 * @see ProceedingXml
 * @see LarDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ApplicationDetailsXml {

  @JacksonXmlProperty(localName = "Client",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ClientXml client;

  @JacksonXmlProperty(localName = "PreferredAddress",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String preferredAddress;

  @JacksonXmlProperty(localName = "CorrespondenceAddress",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private CorrespondenceAddressXml correspondenceAddress;

  @JacksonXmlProperty(localName = "ProviderDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ProviderDetailsXml providerDetails;

  @JacksonXmlProperty(localName = "CategoryOfLaw",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private CategoryOfLawXml categoryOfLaw;

  @JacksonXmlProperty(localName = "OtherParties",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<OtherPartyXml> otherParties;

  @JacksonXmlProperty(localName = "Proceedings",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<ProceedingXml> proceedings;

  @JacksonXmlProperty(localName = "PurposeOfApplication",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String purposeOfApplication;

  @JacksonXmlProperty(localName = "FixedHearingDateInd",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean fixedHearingDateIndicator;

  @JacksonXmlProperty(localName = "PurposeOfHearing",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String purposeOfHearing;

  @JacksonXmlProperty(localName = "HighProfileCaseInd",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean highProfileCaseIndicator;

  @JacksonXmlProperty(localName = "ApplicationAmendmentType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String applicationAmendmentType;

  @JacksonXmlProperty(localName = "LARDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LarDetailsXml larDetails;

  @JacksonXmlProperty(localName = "DateOfFirstAttendance",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate dateOfFirstAttendance;

  @JacksonXmlProperty(localName = "DateOfHearing",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate dateOfHearing;

  @JacksonXmlProperty(localName = "DevolvedPowersDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate devolvedPowersDate;

  @JacksonXmlProperty(localName = "CertificateType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String certificateType;
}
