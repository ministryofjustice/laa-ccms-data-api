package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AwardDetailsXml {

  @JacksonXmlProperty(localName = "CostAward", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private CostAwardXml costAward;

  @JacksonXmlProperty(localName = "FinancialAward", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private FinancialAwardXml financialAward;

  // TODO: XML LandAward
  // TODO: XML OtherAsset


}
