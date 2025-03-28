package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

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

  @JacksonXmlProperty(localName = "ClientReferenceNumber",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String clientReferenceNumber;

  @JacksonXmlProperty(localName = "FirstName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String firstName;

  @JacksonXmlProperty(localName = "Surname",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String surname;


}
