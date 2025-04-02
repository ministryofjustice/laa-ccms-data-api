package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains time related award details for various award sections of a case.
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class TimeRelatedAwardXml {


  @JacksonXmlProperty(localName = "AwardType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardType;

  @JacksonXmlProperty(localName = "Description",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String description;

  @JacksonXmlProperty(localName = "Amount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String amount;

  @JacksonXmlProperty(localName = "AwardTriggeringEvent",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardTriggeringEvent;

  @JacksonXmlProperty(localName = "AwardDate",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate awardDate;

  @JacksonXmlProperty(localName = "OtherDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String otherDetails;

}
