package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.common.UserXml;

/**
 * Contains information regarding the record history of a case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class RecordHistoryXml {

  @JacksonXmlProperty(localName = "CreatedBy",
      namespace = CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private UserXml createdBy;
  @JacksonXmlProperty(localName = "DateCreated",
      namespace = CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private LocalDateTime dateCreated;
  @JacksonXmlProperty(localName = "LastUpdatedBy",
      namespace = CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private UserXml lastUpdatedBy;
  @JacksonXmlProperty(localName = "DateLastUpdated",
      namespace = CaseDetailXmlNamespaces.COMMON_NAMESPACE)
  private LocalDateTime dateLastUpdated;
}
