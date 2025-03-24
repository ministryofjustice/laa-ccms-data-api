package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class DischargeStatusXml {

  @JacksonXmlProperty(localName = "Reason", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String reason;

  @JacksonXmlProperty(localName = "ClientContinuePvtInd", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean clientContinuePvtInd;

  @JacksonXmlProperty(localName = "OtherDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String otherDetails;
}
