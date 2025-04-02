package uk.gov.laa.ccms.data.mapper.casedetails.xml.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains information regarding a user.
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class UserXml {

  @JacksonXmlProperty(localName = "UserLoginID",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private Integer userLoginId;
  @JacksonXmlProperty(localName = "UserName",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String userName;
  @JacksonXmlProperty(localName = "UserType",
      namespace =  CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private String userType;


}
