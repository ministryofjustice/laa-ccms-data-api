package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.common.MessageXml;

/**
 * Case detail object, which contains both the case reference number, and an object containing
 *     all of additional information relating to this case.
 *
 * @see CaseDetailsXml
 * @author Jamie Briggs
 */
@Builder
@JacksonXmlRootElement(localName = "Case")
public record CaseDetailXml(
    @JacksonXmlProperty(localName = "CaseReferenceNumber",
        namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
    String caseReferenceNumber,

    @JacksonXmlProperty(localName = "CaseDetails",
        namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
    CaseDetailsXml caseDetails,

    @JacksonXmlProperty(localName = "Message",
        namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
    MessageXml message) {


}
