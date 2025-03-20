package uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CorrespondenceAddressXml {

  @JacksonXmlProperty(localName = "AddressId", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String addressId;
  @JacksonXmlProperty(localName = "HouseOrTitle", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String houseOrTitle;
  @JacksonXmlProperty(localName = "AddressLineOne", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String addressLineOne;
  @JacksonXmlProperty(localName = "AddressLineTwo", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String addressLineTwo;
  @JacksonXmlProperty(localName = "AddressLineThree", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String addressLineThree;
  @JacksonXmlProperty(localName = "AddressLineFour", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String addressLineFour;
  @JacksonXmlProperty(localName = "City", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String city;
  @JacksonXmlProperty(localName = "Country", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String country;
  @JacksonXmlProperty(localName = "County", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String county;
  @JacksonXmlProperty(localName = "State", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String state;
  @JacksonXmlProperty(localName = "Province", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String province;
  @JacksonXmlProperty(localName = "PostalCode", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String postalCode;

}
