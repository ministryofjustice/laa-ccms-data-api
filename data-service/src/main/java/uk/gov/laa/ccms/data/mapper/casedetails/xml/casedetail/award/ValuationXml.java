package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains valuation details for various award sections.
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ValuationXml {

  @JacksonXmlProperty(localName = "Amount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String amount;

  @JacksonXmlProperty(localName = "Criteria",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String criteria;

  @JacksonXmlProperty(localName = "Date",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate date;
}
