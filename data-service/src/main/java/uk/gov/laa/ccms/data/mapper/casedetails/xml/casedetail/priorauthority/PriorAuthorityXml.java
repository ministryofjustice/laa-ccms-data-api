package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.priorauthority;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseDetailsXml;

/**
 * Contains details about a prior authority for a case detail.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PriorAuthorityXml {

  @JacksonXmlProperty(localName = "PriorAuthorityType",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String priorAuthorityType;

  @JacksonXmlProperty(localName = "Description",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String description;

  @JacksonXmlProperty(localName = "ReasonForRequest",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String reasonForRequest;

  @JacksonXmlProperty(localName = "RequestAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String requestAmount;

  @JacksonXmlProperty(localName = "DecisionStatus",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String decisionStatus;

  @JacksonXmlProperty(localName = "AssessedAmount",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String assessedAmount;

  @JacksonXmlProperty(localName = "Details",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private List<PriorAuthorityAttributeXml> details;

}
