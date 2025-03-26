package uk.gov.laa.ccms.data.mapper.xml.casedetail.otherparty;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.common.AddressXml;

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
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private NameXml name;

  @JacksonXmlProperty(localName = "Address",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private AddressXml address;

  @JacksonXmlProperty(localName = "DateOfBirth",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate dateOfBirth;

  @JacksonXmlProperty(localName = "RelationToClient",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String relationToClient;

  @JacksonXmlProperty(localName = "RelationToCase",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String relationToCase;

  @JacksonXmlProperty(localName = "ContactName",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String contactName;

  @JacksonXmlProperty(localName = "PartyLegalAidedInd",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean partyLegalAidedInd;

  @JacksonXmlProperty(localName = "CourtOrderedMeansAssesment",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean courtOrderedMeansAssessment;

  @JacksonXmlProperty(localName = "AssessedIncomeFrequency",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String assessedIncomeFrequency;

  @JacksonXmlProperty(localName = "AssessedIncome",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private BigDecimal assessedIncome;

  @JacksonXmlProperty(localName = "AssessedAsstes",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private BigDecimal assessedAssets;

  @JacksonXmlProperty(localName = "AssessmentDate",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate assessmentDate;

  @JacksonXmlProperty(localName = "PublicFundingAppliedInd",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean publicFundingAppliedInd;

  @JacksonXmlProperty(localName = "NINumber",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String niNumber;

  @JacksonXmlProperty(localName = "OrganizationName",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String organisationName;

  @JacksonXmlProperty(localName = "OrganizationAddress",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String organisationAddress;

  @JacksonXmlProperty(localName = "EmployersName",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String employersName;

  @JacksonXmlProperty(localName = "EmploymentStatus",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String employmentStatus;

  @JacksonXmlProperty(localName = "CertificateNumber",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String certificateNumber;

  @JacksonXmlProperty(localName = "OtherInformation",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String otherInformation;



}
