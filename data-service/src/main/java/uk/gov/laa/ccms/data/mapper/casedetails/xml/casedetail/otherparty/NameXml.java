package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains name details for other party section.
 *
 * @see OtherPartyXml
 * @see OtherPartyDetailXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NameXml {

  @JacksonXmlProperty(localName = "Title",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String title;
  @JacksonXmlProperty(localName = "Surname",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String surname;
  @JacksonXmlProperty(localName = "FirstName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String firstName;
  @JacksonXmlProperty(localName = "FullName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String fullName;
}
