package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaseInqRSXml {

  @JacksonXmlProperty(localName = "Case", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/Case/1.0/CaseBIM")
  private CaseDetailXml caseDetail;


}
