package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains information regarding the scope limitations of a case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ScopeLimitationXml {

  @JacksonXmlProperty(localName = "ScopeLimitationID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String scopeLimitationId;
  @JacksonXmlProperty(localName = "ScopeLimitation",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String scopeLimitation;
  @JacksonXmlProperty(localName = "ScopeLimitationWording",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String scopeLimitationWording;
  @JacksonXmlProperty(localName = "DelegatedFunctionsApply",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean delegatedFunctionsApply;

}
