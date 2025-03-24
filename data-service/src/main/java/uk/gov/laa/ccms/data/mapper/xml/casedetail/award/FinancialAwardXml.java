package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.ServiceAddressXml;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class FinancialAwardXml {

  @JacksonXmlProperty(localName = "OrderDate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate orderDate;

  @JacksonXmlProperty(localName = "Amount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String amount;

  @JacksonXmlProperty(localName = "InterimAward", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String interimAward;

  @JacksonXmlProperty(localName = "AwardedBy", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardedBy;

  @JacksonXmlProperty(localName = "OtherDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String otherDetails;

  @JacksonXmlProperty(localName = "AwardJustifications", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardJustifications;

  @JacksonXmlProperty(localName = "StatutoryChangeReason", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String statutoryChangeReason;

  @JacksonXmlProperty(localName = "OrderDateServed", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate orderDateServed;

  @JacksonXmlProperty(localName = "LiableParties", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<LiablePartyXml> liableParties;

  @JacksonXmlProperty(localName = "Recovery", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private RecoveryXml recovery;

  @JacksonXmlProperty(localName = "ServiceAddress", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ServiceAddressXml serviceAddress;

}

