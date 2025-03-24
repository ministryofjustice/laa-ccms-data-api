package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains offered amount details for award recovery details.
 *
 * @see RecoveryXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class OfferedAmountXml {

  @JacksonXmlProperty(localName = "Amount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String amount;

  @JacksonXmlProperty(localName = "ConditionsOfOffer", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String conditionsOfOffer;
}
