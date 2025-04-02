package uk.gov.laa.ccms.data.mapper.casedetails.xml.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseDetailsXml;

/**
 * Contains the contact information regarding a case.
 *
 * @author Jamie Briggs
 * @see CaseDetailsXml
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ContactUserIdXml {

  @JacksonXmlProperty(localName = "UserLoginID",
      namespace = CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private Integer userLoginId;

  @JacksonXmlProperty(localName = "UserName",
      namespace = CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String userName;
}
