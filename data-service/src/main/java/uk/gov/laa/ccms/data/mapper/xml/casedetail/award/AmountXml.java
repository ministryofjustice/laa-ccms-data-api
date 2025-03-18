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
public class AmountXml {

  @JacksonXmlProperty(localName = "Amount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String amount;

  @JacksonXmlProperty(localName = "PaidToLSC", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Long paidToLsc;
}
