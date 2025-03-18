package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAddressXml {

  @JacksonXmlProperty(localName = "AddressLine1", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate addressLineOne;

  @JacksonXmlProperty(localName = "AddressLine2", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate addressLineTwo;

  @JacksonXmlProperty(localName = "AddressLine3", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate addressLineThree;

}
