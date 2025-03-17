package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProceedingDetailsXml {

  @JacksonXmlProperty(localName = "ProceedingType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String proceedingType;

  @JacksonXmlProperty(localName = "ProceedingDescription", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String proceedingDescription;

  @JacksonXmlProperty(localName = "DateCostsValid", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String dateCostsValid;

  @JacksonXmlProperty(localName = "OrderType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String orderType;

  @JacksonXmlProperty(localName = "MatterType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String matterType;

  @JacksonXmlProperty(localName = "LevelOfService", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String levelOfService;

  @JacksonXmlProperty(localName = "Stage", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String stage;

  @JacksonXmlProperty(localName = "ClientInvolvementType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String clientInvolvementType;

  @JacksonXmlProperty(localName = "ScopeLimitations", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<ScopeLimitationXml> scopeLimitations;
}
