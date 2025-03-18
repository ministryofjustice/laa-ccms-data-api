package uk.gov.laa.ccms.data.mapper.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.AwardDetailsXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.AwardXml;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaseDetailsXml {

  @JacksonXmlProperty(localName = "ApplicationDetails", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private ApplicationDetailsXml applicationDetails;

  @JacksonXmlProperty(localName = "CertificateType", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private String certificateType;

  @JacksonXmlProperty(localName = "CertificateDate", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private LocalDate certificateDate;

  @JacksonXmlProperty(localName = "PreCertificateCosts", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Long preCertificateCosts;

  @JacksonXmlProperty(localName = "LegalHelpCosts", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private Long legalHelpCosts;


  /**
   * --LINKED CASES
   *           linked_cases_xml ( v_clob, get_case_details_rec.case_party_id );
   *
   *           --OUTCOME AWARDS
   *           outcome_award_xml ( v_clob, get_case_details_rec.case_party_id );
   *
   *           --PRIOR AUTHORITIES
   *           prior_authority_xml ( v_clob,  get_case_details_rec.case_party_id, get_case_details_rec.app_or_cert_sr_id  ) ;
   *
   *           --DISCHARGE STATUS
   *           discharge_status_xml ( p_clob                               => v_clob,
   *                                             p_reason                           =>  get_case_details_rec.discharge_reason,
   *                                             p_client_continue_pvt_ind    =>  get_case_details_rec.client_continuing_privately,
   *                                             p_other_details                   =>  get_case_details_rec.other_discharge_information ) ;
   */
  @JacksonXmlProperty(localName = "LinkedCases", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<LinkedCaseXml> linkedCases;

  @JacksonXmlProperty(localName = "Awards", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private List<AwardXml> outcomeAwards;

  // TODO: XML Prior Authority Type
  @JacksonXmlProperty(localName = "DischargeStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private DischargeStatusXml dischargeStatus;

  // TODO: XML Available Functions Type
  @JacksonXmlProperty(localName = "CaseStatus", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private CaseStatusXml caseStatus;

  @JacksonXmlProperty(localName = "RecordHistory", namespace = "http://legalservices.gov.uk/CCMS/CaseManagement/CaseBIO")
  private RecordHistoryXml recordHistoryXml;

}
