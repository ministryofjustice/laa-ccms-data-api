package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Parent XML object for case details.
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CaseInqRsXml {

  @JacksonXmlProperty(localName = "Case", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/Case/1.0/CaseBIM")
  private CaseDetailXml caseDetail;


}
