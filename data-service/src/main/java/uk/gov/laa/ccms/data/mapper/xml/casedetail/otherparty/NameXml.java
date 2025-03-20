package uk.gov.laa.ccms.data.mapper.xml.casedetail.otherparty;

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
public class NameXml {

  @JacksonXmlProperty(localName = "Title", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String title;
  @JacksonXmlProperty(localName = "Surname", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String surname;
  @JacksonXmlProperty(localName = "FirstName", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String firstName;
  @JacksonXmlProperty(localName = "FullName", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String fullName;
}
