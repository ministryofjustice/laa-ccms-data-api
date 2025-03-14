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
public class ApplicationDetailsXml {

  @JacksonXmlProperty(localName = "Client", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ClientXml client;

  @JacksonXmlProperty(localName = "PreferredAddress", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String preferredAddress;

  @JacksonXmlProperty(localName = "ProviderDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ProviderDetailsXml providerDetails;


}
