package uk.gov.laa.ccms.data.mapper.xml.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class UserXml {

  @JacksonXmlProperty(localName = "UserLoginID", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String userLoginId;
  @JacksonXmlProperty(localName = "UserName", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private String UserName;


}
