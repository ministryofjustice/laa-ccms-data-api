package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a case search entity from the <b>XXCCMS_CASE_SEARCH_V</b> database view.
 *
 * <p>This entity captures various attributes associated with a legal case,
 * such as case references, provider information,
 * case status, and associated client information.</p>
 *
 * <p>The class is immutable, and it's instances can be created using the builder pattern.</p>
 *
 */
@Entity
@Table(name = "XXCCMS_CASE_SEARCH_V", schema = "XXCCMS")
@Getter
@Builder
@Immutable
@AllArgsConstructor
@RequiredArgsConstructor
public class CaseSearch {

  @Id
  @Column(name = "CASE_PARTY_ID", nullable = false, precision = 15, scale = 0)
  private long casePartyId;

  @Column(name = "APP_OR_CERT_SR_ID")
  private Long appOrCertSrId;

  @Column(name = "LSC_CASE_REFERENCE", nullable = false, length = 360)
  private String lscCaseReference;

  @Column(name = "CIS_CASE_REFERENCE", length = 150)
  private String cisCaseReference;

  @Column(name = "CLIENT_PARTY_ID", precision = 15, scale = 0)
  private Long clientPartyId;

  @Column(name = "PERSON_FIRST_NAME", length = 150)
  private String personFirstName;

  @Column(name = "PERSON_LAST_NAME", length = 150)
  private String personLastName;

  @Column(name = "PROVIDER_CASE_REFERENCE", length = 150)
  private String providerCaseReference;

  @Column(name = "PROVIDER_FIRM_PARTY_ID", precision = 15, scale = 0)
  private Long providerFirmPartyId;

  @Column(name = "PROVIDER_OFFICE_PARTY_ID", precision = 15, scale = 0)
  private Long providerOfficePartyId;

  @Column(name = "FEE_EARNER_PARTY_ID", precision = 15, scale = 0)
  private Long feeEarnerPartyId;

  @Column(name = "FEE_EARNER", length = 360)
  private String feeEarner;

  @Column(name = "CATEGORY_OF_LAW", length = 150)
  private String categoryOfLaw;

  @Column(name = "CATEGORY_OF_LAW_DESC", length = 80)
  private String categoryOfLawDescription;

  @Column(name = "ACTUAL_CASE_STATUS", length = 30)
  private String actualCaseStatus;

  @Column(name = "DISPLAY_CASE_STATUS", length = 150)
  private String displayCaseStatus;
}
