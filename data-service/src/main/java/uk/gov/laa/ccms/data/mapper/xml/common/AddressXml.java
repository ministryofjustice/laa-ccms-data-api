package uk.gov.laa.ccms.data.mapper.xml.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains information regarding an address.
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class AddressXml {

  @JacksonXmlProperty(localName = "AddressID",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String addressId;
  @JacksonXmlProperty(localName = "HouseOrTitle",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String houseOrTitle;
  @JacksonXmlProperty(localName = "AddressLine1",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String addressLine1;
  @JacksonXmlProperty(localName = "AddressLine2",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String addressLine2;
  @JacksonXmlProperty(localName = "AddressLine3",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String addressLine3;
  @JacksonXmlProperty(localName = "AddressLine4",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String addressLine4;
  
  @JacksonXmlProperty(localName = "City",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String city;
  @JacksonXmlProperty(localName = "Country",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String country;
  @JacksonXmlProperty(localName = "County",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String county;
  @JacksonXmlProperty(localName = "State",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String state;
  @JacksonXmlProperty(localName = "Province",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String province;
  @JacksonXmlProperty(localName = "PostalCode",
      namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String postalCode;
  
}
