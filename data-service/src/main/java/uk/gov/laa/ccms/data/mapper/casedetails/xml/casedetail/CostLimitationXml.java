package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains details about the cost limitations of a case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CostLimitationXml {

  @JacksonXmlProperty(localName = "CostLimitID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String costLimitId;
  @JacksonXmlProperty(localName = "BillingProviderID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String billingProviderId;
  @JacksonXmlProperty(localName = "BillingProviderName",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String billingProviderName;
  @JacksonXmlProperty(localName = "CostCategory",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String costCategory;
  @JacksonXmlProperty(localName = "PaidToDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private BigDecimal paidToDate;
  @JacksonXmlProperty(localName = "Amount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private BigDecimal amount;
}
