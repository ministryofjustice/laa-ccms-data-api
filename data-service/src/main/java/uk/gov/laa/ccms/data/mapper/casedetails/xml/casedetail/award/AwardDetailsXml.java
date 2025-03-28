package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains award details for an award.
 *
 * @see AwardXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class AwardDetailsXml {

  @JacksonXmlProperty(localName = "AwardCategory",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardCategory;

  @JacksonXmlProperty(localName = "CostAward",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private CostAwardXml costAward;

  @JacksonXmlProperty(localName = "FinancialAward",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private FinancialAwardXml financialAward;

  @JacksonXmlProperty(localName = "LandAward",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LandAwardXml landAward;

  @JacksonXmlProperty(localName = "OtherAsset",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private OtherAssetXml otherAsset;

}
