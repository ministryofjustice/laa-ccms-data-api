package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains address information for the application details section of case.
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CorrespondenceAddressXml {

  @JacksonXmlProperty(localName = "AddressId",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String addressId;
  @JacksonXmlProperty(localName = "HouseOrTitle",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String houseOrTitle;
  @JacksonXmlProperty(localName = "CareOfName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String careOfName;
  @JacksonXmlProperty(localName = "AddressLineOne",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String addressLineOne;
  @JacksonXmlProperty(localName = "AddressLineTwo",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String addressLineTwo;
  @JacksonXmlProperty(localName = "AddressLineThree",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String addressLineThree;
  @JacksonXmlProperty(localName = "AddressLineFour",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String addressLineFour;
  @JacksonXmlProperty(localName = "City",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String city;
  @JacksonXmlProperty(localName = "Country",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String country;
  @JacksonXmlProperty(localName = "County",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String county;
  @JacksonXmlProperty(localName = "State",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String state;
  @JacksonXmlProperty(localName = "Province",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String province;
  @JacksonXmlProperty(localName = "PostalCode",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String postalCode;

}
