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
public class RecoveredAmountXml {

  @JacksonXmlProperty(localName = "Solicitor", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private AmountXml solicitor;
  @JacksonXmlProperty(localName = "Court", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private AmountXml court;
  @JacksonXmlProperty(localName = "Client", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private AmountXml client;
}
