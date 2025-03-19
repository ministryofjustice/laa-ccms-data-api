package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ValuationXml {

  @JacksonXmlProperty(localName = "Amount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String amount;

  @JacksonXmlProperty(localName = "Criteria", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String criteria;

  @JacksonXmlProperty(localName = "Date", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String date;
}
