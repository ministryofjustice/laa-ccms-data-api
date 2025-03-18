package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class OtherPartyDetailXml {

  @JacksonXmlProperty(localName = "Organization", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private OrganizationXml organization;

}
