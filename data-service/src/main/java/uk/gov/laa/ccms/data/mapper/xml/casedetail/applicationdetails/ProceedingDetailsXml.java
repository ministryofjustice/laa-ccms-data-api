package uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.ScopeLimitationXml;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ProceedingDetailsXml {

  @JacksonXmlProperty(localName = "ProceedingType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String proceedingType;

  @JacksonXmlProperty(localName = "ProceedingDescription", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String proceedingDescription;

  @JacksonXmlProperty(localName = "DateCostsValid", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate dateCostsValid;

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
