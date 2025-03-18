package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class RecoveryXml {

  @JacksonXmlProperty(localName = "AwardValue", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardValue;

  @JacksonXmlProperty(localName = "RecoveredAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private RecoveredAmountXml recoveredAmount;

  @JacksonXmlProperty(localName = "UnRecoveredAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String unrecoveredAmount;

  @JacksonXmlProperty(localName = "LeaveOfCourtReqdInd", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean leaveOfCourtRequiredIndicator;

  @JacksonXmlProperty(localName = "OfferedAmount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private AmountXml offeredAmount;

  @JacksonXmlProperty(localName = "Amount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String amount;
}
