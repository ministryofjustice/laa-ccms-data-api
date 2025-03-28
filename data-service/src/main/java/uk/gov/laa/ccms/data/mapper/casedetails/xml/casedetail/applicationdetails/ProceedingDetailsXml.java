package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.ScopeLimitationXml;

/**
 * Contains Proceeding details section for a proceeding.
 *
 * @author Jamie Briggs
 * @see ProceedingXml
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ProceedingDetailsXml {

  @JacksonXmlProperty(localName = "ProceedingType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String proceedingType;

  @JacksonXmlProperty(localName = "ProceedingDescription",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String proceedingDescription;

  @JacksonXmlProperty(localName = "DateCostsValid",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private LocalDate dateCostsValid;

  @JacksonXmlProperty(localName = "OrderType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String orderType;

  @JacksonXmlProperty(localName = "MatterType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String matterType;

  @JacksonXmlProperty(localName = "LevelOfService",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String levelOfService;

  @JacksonXmlProperty(localName = "Stage",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String stage;

  @JacksonXmlProperty(localName = "ClientInvolvementType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String clientInvolvementType;

  @JacksonXmlProperty(localName = "ScopeLimitations",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<ScopeLimitationXml> scopeLimitations;
}
