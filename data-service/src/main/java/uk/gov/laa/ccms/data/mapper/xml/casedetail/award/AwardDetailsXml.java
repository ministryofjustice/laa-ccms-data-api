package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @JacksonXmlProperty(localName = "AwardCategory", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardCategory;

  @JacksonXmlProperty(localName = "CostAward", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private CostAwardXml costAward;

  @JacksonXmlProperty(localName = "FinancialAward", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private FinancialAwardXml financialAward;

  @JacksonXmlProperty(localName = "LandAward", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LandAwardXml landAward;

  @JacksonXmlProperty(localName = "OtherAsset", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private OtherAssetXml otherAsset;

}
