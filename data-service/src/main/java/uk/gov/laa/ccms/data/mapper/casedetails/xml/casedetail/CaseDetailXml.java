package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Case detail object, which contains both the case reference number, and an object containing
 *     all of additional information relating to this case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CaseDetailXml {

  @JacksonXmlProperty(localName = "CaseReferenceNumber",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String caseReferenceNumber;

  @JacksonXmlProperty(localName = "CaseDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private CaseDetailsXml caseDetails;

  @JacksonXmlProperty(localName = "Message")
  private MessageXml message;

}
