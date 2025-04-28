package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Message object containing response details.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageXml {

  @JacksonXmlProperty(localName = "Status")
  private String status;

  @JacksonXmlProperty(localName = "Code")
  private Integer code;

  @JacksonXmlProperty(localName = "Text")
  private String text;

}
