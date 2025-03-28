package uk.gov.laa.ccms.data.mapper.casedetails.xml.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

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
      namespace = CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String addressId;
  @JacksonXmlProperty(localName = "HouseOrTitle",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String houseOrTitle;
  @JacksonXmlProperty(localName = "AddressLine1",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String addressLine1;
  @JacksonXmlProperty(localName = "AddressLine2",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String addressLine2;
  @JacksonXmlProperty(localName = "AddressLine3",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String addressLine3;
  @JacksonXmlProperty(localName = "AddressLine4",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String addressLine4;
  
  @JacksonXmlProperty(localName = "City",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String city;
  @JacksonXmlProperty(localName = "Country",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String country;
  @JacksonXmlProperty(localName = "County",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String county;
  @JacksonXmlProperty(localName = "State",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String state;
  @JacksonXmlProperty(localName = "Province",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String province;
  @JacksonXmlProperty(localName = "PostalCode",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String postalCode;
  
}
