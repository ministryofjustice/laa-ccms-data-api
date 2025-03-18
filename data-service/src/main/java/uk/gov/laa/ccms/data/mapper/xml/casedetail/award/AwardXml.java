package uk.gov.laa.ccms.data.mapper.xml.casedetail.award;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AwardXml {

  @JacksonXmlProperty(localName = "AwardID", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardId;

  @JacksonXmlProperty(localName = "AwardType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String awardType;

  @JacksonXmlProperty(localName = "DeleteAllowed", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean deleteAllowed;

  @JacksonXmlProperty(localName = "UpdateAllowed", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Boolean updateAllowed;

  @JacksonXmlProperty(localName = "AwardDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private AwardDetailsXml awardDetails;

}
