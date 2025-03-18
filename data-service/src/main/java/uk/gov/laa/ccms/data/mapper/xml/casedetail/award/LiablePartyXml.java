package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiablePartyXml {

  @JacksonXmlProperty(localName = "OtherPartyID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String otherPartyId;

}
