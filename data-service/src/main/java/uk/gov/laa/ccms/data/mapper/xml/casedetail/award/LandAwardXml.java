package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.PropertyAddressXml;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class LandAwardXml {

  @JacksonXmlProperty(localName = "OrderDate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate orderDate;

  @JacksonXmlProperty(localName = "Description", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String description;

  @JacksonXmlProperty(localName = "TitleNo", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String titleNo;

  @JacksonXmlProperty(localName = "PropertyAddress", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private PropertyAddressXml propertyAddress;

  @JacksonXmlProperty(localName = "Valuation", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ValuationXml valuation;

  @JacksonXmlProperty(localName = "DisputedPercentage", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String disputedPercentage;

  @JacksonXmlProperty(localName = "AwardedPercentage", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardedPercentage;

  @JacksonXmlProperty(localName = "MortgageAmountDue", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String mortgageAmountDue;

  @JacksonXmlProperty(localName = "Equity", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String equity;

  @JacksonXmlProperty(localName = "AwardedBy", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardedBy;

  @JacksonXmlProperty(localName = "Recovery", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String recovery;

  @JacksonXmlProperty(localName = "OtherProprietors", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<OtherPartyXml> otherProprietors;

  @JacksonXmlProperty(localName = "TimeRelatedAward", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private TimeRelatedAwardXml timeRelatedAward;

}
