package uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class ClientXml {

  @JacksonXmlProperty(localName = "ClientReferenceNumber", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String clientReferenceNumber;

  @JacksonXmlProperty(localName = "FirstName", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String firstName;

  @JacksonXmlProperty(localName = "Surname", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String surname;


}
