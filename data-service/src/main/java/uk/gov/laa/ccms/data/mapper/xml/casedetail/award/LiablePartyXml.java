package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains liable party ID used for various award sections.
 *
 * @see AwardDetailsXml
 * @author Jamie Briggs
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class LiablePartyXml {

  @JacksonXmlProperty(localName = "OtherPartyID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String otherPartyId;

}
