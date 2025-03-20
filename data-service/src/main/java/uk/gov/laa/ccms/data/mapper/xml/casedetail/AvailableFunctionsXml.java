package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;
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
public final class AvailableFunctionsXml {

  @JacksonXmlProperty(localName = "Function", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<String> functions;
}
