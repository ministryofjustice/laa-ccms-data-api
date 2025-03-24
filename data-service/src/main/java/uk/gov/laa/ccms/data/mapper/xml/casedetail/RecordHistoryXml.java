package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.common.UserXml;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class RecordHistoryXml {

  @JacksonXmlProperty(localName = "CreatedBy", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private UserXml createdBy;
  @JacksonXmlProperty(localName = "DateCreated", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private LocalDateTime dateCreated;
  @JacksonXmlProperty(localName = "LastUpdatedBy", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private UserXml lastUpdatedBy;
  @JacksonXmlProperty(localName = "DateLastUpdated", namespace = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common")
  private LocalDateTime dateLastUpdated;
}
