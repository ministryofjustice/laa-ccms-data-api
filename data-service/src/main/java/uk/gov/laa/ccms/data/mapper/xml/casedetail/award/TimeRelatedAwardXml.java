package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


  @JacksonXmlProperty(localName = "AwardType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardType;

  @JacksonXmlProperty(localName = "Description", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String description;

  @JacksonXmlProperty(localName = "Amount", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String amount;

  @JacksonXmlProperty(localName = "AwardTriggeringEvent", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardTriggeringEvent;

  @JacksonXmlProperty(localName = "AwardDate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate awardDate;

  @JacksonXmlProperty(localName = "OtherDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String otherDetails;

}
