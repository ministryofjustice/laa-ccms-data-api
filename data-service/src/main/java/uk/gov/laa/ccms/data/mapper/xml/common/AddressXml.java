package uk.gov.laa.ccms.data.mapper.xml.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class AddressXml {

  @JacksonXmlProperty(localName = "AddressID", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String addressId;
  @JacksonXmlProperty(localName = "AddressLine1", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String addressLineOne;
  @JacksonXmlProperty(localName = "AddressLine2", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String addressLineTwo;
  
  @JacksonXmlProperty(localName = "City", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String city;
  @JacksonXmlProperty(localName = "Country", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String country;
  @JacksonXmlProperty(localName = "County", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String county;
  @JacksonXmlProperty(localName = "PostalCode", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String postalCode;
  
}
