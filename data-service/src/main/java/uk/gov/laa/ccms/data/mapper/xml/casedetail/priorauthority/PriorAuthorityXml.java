package uk.gov.laa.ccms.data.mapper.xml.casedetail.priorauthority;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PriorAuthorityXml {

  @JacksonXmlProperty(localName = "PriorAuthorityType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String priorAuthorityType;

  @JacksonXmlProperty(localName = "Description", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String description;

  @JacksonXmlProperty(localName = "ReasonForRequest", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String reasonForRequest;

  @JacksonXmlProperty(localName = "RequestAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String requestAmount;

  @JacksonXmlProperty(localName = "DecisionStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String decisionStatus;

  @JacksonXmlProperty(localName = "AssessedAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String assessedAmount;

  @JacksonXmlProperty(localName = "Details", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<PriorAuthorityAttributeXml> details;

}
