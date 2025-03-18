package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ScopeLimitationXml {

  @JacksonXmlProperty(localName = "ScopeLimitationID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String scopeLimitationId;
  @JacksonXmlProperty(localName = "ScopeLimitations", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String scopeLimitation;
  @JacksonXmlProperty(localName = "ScopeLimitationWording", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String scopeLimitationWording;
  @JacksonXmlProperty(localName = "DelegatedFunctionsApply", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean delegatedFunctionsApply;

}
