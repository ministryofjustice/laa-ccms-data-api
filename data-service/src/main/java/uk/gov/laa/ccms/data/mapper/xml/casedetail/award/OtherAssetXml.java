package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;


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
public final class OtherAssetXml {

  @JacksonXmlProperty(localName = "OrderDate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate orderDate;

  @JacksonXmlProperty(localName = "Description", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String description;

  @JacksonXmlProperty(localName = "Valuation", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ValuationXml valuation;

  @JacksonXmlProperty(localName = "AwardedAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardedAmount;

  @JacksonXmlProperty(localName = "AwardedPercentage", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardedPercentage;

  @JacksonXmlProperty(localName = "RecoveredAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String recoveredAmount;

  @JacksonXmlProperty(localName = "RecoveredPercentage", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String recoveredPercentage;

  @JacksonXmlProperty(localName = "DisputedAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String disputedAmount;

  @JacksonXmlProperty(localName = "DisputedPercentage", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String disputedPercentage;

  @JacksonXmlProperty(localName = "AwardedBy", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardedBy;

  @JacksonXmlProperty(localName = "Recovery", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String recovery;

  @JacksonXmlProperty(localName = "HeldBy", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<OtherPartyXml> heldBy;

  @JacksonXmlProperty(localName = "TimeRelatedAward", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private TimeRelatedAwardXml timeRelatedAward;
}
