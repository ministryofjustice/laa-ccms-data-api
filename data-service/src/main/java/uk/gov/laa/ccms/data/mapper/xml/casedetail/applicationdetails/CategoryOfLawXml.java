package uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.CostLimitationXml;

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
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String categoryOfLawCode;
  @JacksonXmlProperty(localName = "CategoryOfLawDescription",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String categoryOfLawDescription;
  @JacksonXmlProperty(localName = "RequestedAmount",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String requestedAmount;
  @JacksonXmlProperty(localName = "GrantedAmount",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String grantedAmount;
  @JacksonXmlProperty(localName = "TotalPaidToDate",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String totalPaidToDate;
  @JacksonXmlProperty(localName = "CostLimitations",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<CostLimitationXml> costLimitations;
}
