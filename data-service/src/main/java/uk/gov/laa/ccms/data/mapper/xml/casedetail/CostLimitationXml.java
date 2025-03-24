package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @JacksonXmlProperty(localName = "CostLimitID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String costLimitId;
  @JacksonXmlProperty(localName = "BillingProviderID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String billingProviderId;
  @JacksonXmlProperty(localName = "BillingProviderName", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String billingProviderName;
  @JacksonXmlProperty(localName = "CostCategory", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String costCategory;
  @JacksonXmlProperty(localName = "PaidToDate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private BigDecimal paidToDate;
  @JacksonXmlProperty(localName = "Amount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private BigDecimal amount;
}
