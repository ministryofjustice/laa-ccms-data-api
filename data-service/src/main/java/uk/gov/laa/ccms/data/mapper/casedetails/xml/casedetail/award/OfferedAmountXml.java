package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains offered amount details for award recovery details.
 *
 * @see RecoveryXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class OfferedAmountXml {

  @JacksonXmlProperty(localName = "Amount", 
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String amount;

  @JacksonXmlProperty(localName = "ConditionsOfOffer",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String conditionsOfOffer;
}
