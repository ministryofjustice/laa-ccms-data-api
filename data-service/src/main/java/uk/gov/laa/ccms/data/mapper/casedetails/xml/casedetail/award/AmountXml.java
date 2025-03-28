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
 * Amount details used for award details.
 *
 * @see AwardXml
 * @see RecoveryXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class AmountXml {

  @JacksonXmlProperty(localName = "Amount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String amount;

  @JacksonXmlProperty(localName = "PaidToLSC",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Long paidToLsc;

  @JacksonXmlProperty(localName = "DateReceived",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate dateReceived;
}
