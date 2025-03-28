package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.priorauthority;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains details about an attribute associated with a prior authority.
 *
 * @see PriorAuthorityXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PriorAuthorityAttributeXml {

  @JacksonXmlProperty(localName = "Name",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String name;

  @JacksonXmlProperty(localName = "Value",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String value;
}
