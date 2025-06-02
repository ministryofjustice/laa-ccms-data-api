package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.common.AddressXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.ContactDetailsXml;

/**
 * Contains details about a person regarding an other party.
 *
 * @see OtherPartyDetailXml
 * @see PersonXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonXml {

  @JacksonXmlProperty(localName = "Name",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private NameXml name;

  @JacksonXmlProperty(localName = "Address",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private AddressXml address;

  @JacksonXmlProperty(localName = "DateOfBirth",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate dateOfBirth;

  @JacksonXmlProperty(localName = "RelationToClient",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String relationToClient;

  @JacksonXmlProperty(localName = "RelationToCase",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String relationToCase;

  @JacksonXmlProperty(localName = "NINumber",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String niNumber;

  @JacksonXmlProperty(localName = "ContactDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ContactDetailsXml contactDetails;

  @JacksonXmlProperty(localName = "ContactName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String contactName;

  @JacksonXmlProperty(localName = "PartyLegalAidedInd",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean partyLegalAidedInd;

  @JacksonXmlProperty(localName = "CourtOrderedMeansAssessment",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean courtOrderedMeansAssessment;

  @JacksonXmlProperty(localName = "AssessedIncomeFrequency",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String assessedIncomeFrequency;

  @JacksonXmlProperty(localName = "AssessedIncome",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private BigDecimal assessedIncome;

  @JacksonXmlProperty(localName = "AssessedAssets",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private BigDecimal assessedAssets;

  @JacksonXmlProperty(localName = "AssessmentDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate assessmentDate;

  @JacksonXmlProperty(localName = "PublicFundingAppliedInd",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean publicFundingAppliedInd;

  @JacksonXmlProperty(localName = "OrganizationName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String organisationName;

  @JacksonXmlProperty(localName = "OrganizationAddress",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String organisationAddress;

  @JacksonXmlProperty(localName = "EmployersName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String employersName;

  @JacksonXmlProperty(localName = "EmploymentStatus",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String employmentStatus;

  @JacksonXmlProperty(localName = "CertificateNumber",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String certificateNumber;

  @JacksonXmlProperty(localName = "OtherInformation",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String otherInformation;



}
