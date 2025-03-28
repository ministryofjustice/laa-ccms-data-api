package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.CaseDetailXmlNamespaces;

/**
 * Contains address information regarding the service address of an award.
 *
 * @see AwardXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ServiceAddressXml {

  @JacksonXmlProperty(localName = "AddressLine1",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String addressLine1;

  @JacksonXmlProperty(localName = "AddressLine2",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String addressLine2;

  @JacksonXmlProperty(localName = "AddressLine3",
      namespace = CaseDetailXmlNamespaces.CASE_NAMESPACE)
  private String addressLine3;

}
