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
 * Contains other asset information for award details.
 *
 * @see AwardDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class OtherAssetXml {

  @JacksonXmlProperty(localName = "OrderDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate orderDate;

  @JacksonXmlProperty(localName = "Description",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String description;

  @JacksonXmlProperty(localName = "Valuation",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ValuationXml valuation;

  @JacksonXmlProperty(localName = "AwardedAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardedAmount;

  @JacksonXmlProperty(localName = "AwardedPercentage",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardedPercentage;

  @JacksonXmlProperty(localName = "RecoveredAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String recoveredAmount;

  @JacksonXmlProperty(localName = "RecoveredPercentage",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String recoveredPercentage;

  @JacksonXmlProperty(localName = "DisputedAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String disputedAmount;

  @JacksonXmlProperty(localName = "DisputedPercentage",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String disputedPercentage;

  @JacksonXmlProperty(localName = "AwardedBy",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardedBy;

  @JacksonXmlProperty(localName = "Recovery",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String recovery;

  @JacksonXmlProperty(localName = "NoRecoveryDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String noRecoveryDetails;

  @JacksonXmlProperty(localName = "HeldBy",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<OtherPartyXml> heldBy;

  @JacksonXmlProperty(localName = "TimeRelatedAward",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private TimeRelatedAwardXml timeRelatedAward;

  @JacksonXmlProperty(localName = "StatChargeExemptReason",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String statChargeExemptReason;
}
