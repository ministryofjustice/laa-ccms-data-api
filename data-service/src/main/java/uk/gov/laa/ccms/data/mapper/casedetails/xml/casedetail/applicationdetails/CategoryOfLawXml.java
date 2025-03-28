package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CostLimitationXml;

/**
 * Contains Category of Law section for the application details section of a case.
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CategoryOfLawXml {

  @JacksonXmlProperty(localName = "CategoryOfLawCode",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String categoryOfLawCode;
  @JacksonXmlProperty(localName = "CategoryOfLawDescription",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String categoryOfLawDescription;
  @JacksonXmlProperty(localName = "RequestedAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String requestedAmount;
  @JacksonXmlProperty(localName = "GrantedAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String grantedAmount;
  @JacksonXmlProperty(localName = "TotalPaidToDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String totalPaidToDate;
  @JacksonXmlProperty(localName = "CostLimitations",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<CostLimitationXml> costLimitations;
}
