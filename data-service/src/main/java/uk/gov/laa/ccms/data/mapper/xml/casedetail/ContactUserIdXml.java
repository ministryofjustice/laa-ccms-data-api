package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @JacksonXmlProperty(localName = "UserLoginID", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private Integer userLoginId;

  @JacksonXmlProperty(localName = "UserName", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String userName;
}
