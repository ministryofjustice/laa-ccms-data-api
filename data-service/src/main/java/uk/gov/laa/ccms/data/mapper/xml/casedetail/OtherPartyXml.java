package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class OtherPartyXml {

  @JacksonXmlProperty(localName = "OtherPartyID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String otherPartyId;

  @JacksonXmlProperty(localName = "SharedInd", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean sharedIndicator;

  @JacksonXmlProperty(localName = "OtherPartyDetail", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private OtherPartyDetailXml otherPartyDetail;



}
