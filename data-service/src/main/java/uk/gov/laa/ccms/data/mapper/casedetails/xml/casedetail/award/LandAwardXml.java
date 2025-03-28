package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty.OtherPartyXml;

/**
 * Contains land award information found in award details.
 *
 * @see AwardDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class LandAwardXml {

  @JacksonXmlProperty(localName = "OrderDate", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate orderDate;

  @JacksonXmlProperty(localName = "Description", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String description;

  @JacksonXmlProperty(localName = "TitleNo",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String titleNo;

  @JacksonXmlProperty(localName = "PropertyAddress",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ServiceAddressXml propertyAddress;

  @JacksonXmlProperty(localName = "Valuation",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ValuationXml valuation;

  @JacksonXmlProperty(localName = "DisputedPercentage",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String disputedPercentage;

  @JacksonXmlProperty(localName = "AwardedPercentage", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardedPercentage;

  @JacksonXmlProperty(localName = "MortgageAmountDue",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String mortgageAmountDue;

  @JacksonXmlProperty(localName = "Equity", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String equity;

  @JacksonXmlProperty(localName = "AwardedBy", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardedBy;

  @JacksonXmlProperty(localName = "Recovery", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String recovery;

  @JacksonXmlProperty(localName = "NoRecoveryDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String noRecoveryDetails;

  @JacksonXmlProperty(localName = "StatChargeExemptReason",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String statChargeExemptReason;

  @JacksonXmlProperty(localName = "LandChargeRegistration",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String landChargeRegistration;

  @JacksonXmlProperty(localName = "RegistrationRef",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String registrationRef;

  @JacksonXmlProperty(localName = "OtherProprietors",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<OtherPartyXml> otherProprietors;

  @JacksonXmlProperty(localName = "TimeRelatedAward",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private TimeRelatedAwardXml timeRelatedAward;

}
