package uk.gov.laa.ccms.data.mapper.xml.casedetail.priorauthority;

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
public final class PriorAuthorityAttributeXml {

  @JacksonXmlProperty(localName = "Name", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String name;

  @JacksonXmlProperty(localName = "Value", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String value;
}
