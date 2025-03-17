package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryOfLawXml {

  @JacksonXmlProperty(localName = "CategoryOfLawCode", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String categoryOfLawCode;
  @JacksonXmlProperty(localName = "CategoryOfLawDescription", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String categoryOfLawDescription;
  @JacksonXmlProperty(localName = "RequestedAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String requestedAmount;
  @JacksonXmlProperty(localName = "GrantedAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String grantedAmount;
  @JacksonXmlProperty(localName = "TotalPaidToDate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String totalPaidToDate;

  // TODO: XML Cost limitations
}
