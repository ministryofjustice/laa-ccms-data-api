package uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains LAR scope flag information for the application details section of a case.
 *
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class LarDetailsXml {

  @JacksonXmlProperty(localName = "LARScopeFlag",
      namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean larScopeFlag;
}
