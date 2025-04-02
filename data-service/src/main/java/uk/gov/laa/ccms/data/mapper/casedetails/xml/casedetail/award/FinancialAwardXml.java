package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains financial award information found in award details.
 *
 * @see AwardDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class FinancialAwardXml {

  @JacksonXmlProperty(localName = "OrderDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate orderDate;

  @JacksonXmlProperty(localName = "Amount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String amount;

  @JacksonXmlProperty(localName = "InterimAward",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String interimAward;

  @JacksonXmlProperty(localName = "AwardedBy",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardedBy;

  @JacksonXmlProperty(localName = "OtherDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String otherDetails;

  @JacksonXmlProperty(localName = "AwardJustifications",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardJustifications;

  @JacksonXmlProperty(localName = "StatutoryChangeReason",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String statutoryChangeReason;

  @JacksonXmlProperty(localName = "OrderDateServed",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate orderDateServed;

  @JacksonXmlProperty(localName = "LiableParties",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<LiablePartyXml> liableParties;

  @JacksonXmlProperty(localName = "Recovery",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private RecoveryXml recovery;

  @JacksonXmlProperty(localName = "ServiceAddress",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private ServiceAddressXml serviceAddress;

}

