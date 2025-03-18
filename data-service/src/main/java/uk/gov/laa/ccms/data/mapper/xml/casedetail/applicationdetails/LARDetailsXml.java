package uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class LARDetailsXml {

  @JacksonXmlProperty(localName = "LARScopeFlag", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean larScopeFlag;
}
