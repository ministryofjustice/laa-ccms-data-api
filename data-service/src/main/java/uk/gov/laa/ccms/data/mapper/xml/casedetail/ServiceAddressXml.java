package uk.gov.laa.ccms.data.mapper.xml.casedetail;

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
public final class ServiceAddressXml {

  @JacksonXmlProperty(localName = "AddressLine1", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String addressLine1;

  @JacksonXmlProperty(localName = "AddressLine2", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String addressLine2;

  @JacksonXmlProperty(localName = "AddressLine3", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String addressLine3;

}
