package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains recovered amount details for the recovery section.
 *
 * @see RecoveryXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class RecoveredAmountXml {

  @JacksonXmlProperty(localName = "Solicitor",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private AmountXml solicitor;
  @JacksonXmlProperty(localName = "Court",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private AmountXml court;
  @JacksonXmlProperty(localName = "Client",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private AmountXml client;
}
