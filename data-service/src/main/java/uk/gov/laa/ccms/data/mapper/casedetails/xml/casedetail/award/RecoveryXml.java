package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains recovery details for various award sections.
 *
 * @see AwardDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class RecoveryXml {

  @JacksonXmlProperty(localName = "AwardValue",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardValue;

  @JacksonXmlProperty(localName = "RecoveredAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private RecoveredAmountXml recoveredAmount;

  @JacksonXmlProperty(localName = "UnRecoveredAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String unRecoveredAmount;

  @JacksonXmlProperty(localName = "LeaveOfCourtReqdInd",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean leaveOfCourtRequiredIndicator;

  @JacksonXmlProperty(localName = "OfferedAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private OfferedAmountXml offeredAmount;

  @JacksonXmlProperty(localName = "Amount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String amount;
}
