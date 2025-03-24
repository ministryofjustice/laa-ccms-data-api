package uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains Client section information for the application details section of a case.
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
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
