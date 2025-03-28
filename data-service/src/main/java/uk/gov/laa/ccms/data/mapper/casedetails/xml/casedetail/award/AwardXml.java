package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseDetailsXml;

/**
 * Contains award information found in a case details.
 *
 * @author Jamie Briggs
 * @see CaseDetailsXml
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class AwardXml {

  @JacksonXmlProperty(localName = "AwardID",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardId;

  @JacksonXmlProperty(localName = "AwardType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String awardType;

  @JacksonXmlProperty(localName = "DeleteAllowed",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean deleteAllowed;

  @JacksonXmlProperty(localName = "UpdateAllowed",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private Boolean updateAllowed;

  @JacksonXmlProperty(localName = "AwardDetails",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private AwardDetailsXml awardDetails;

}
