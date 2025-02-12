package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a client detail entity from the <b>XXCCMS_GET_CLIENT_DETAILS_V</b> database view.
 *
 * <p>This entity captures personal information about a client.</p>
 *
 * <p>The class is immutable, and its instances can be created using the builder pattern.</p>
 *
 * @author Jamie Briggs
 */
@Entity
@Table(name = "XXCCMS_GET_CLIENT_DETAILS_V", schema = "XXCCMS")
@Getter
@Builder
@Immutable
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientDetail {

  @Id
  @Column(name = "CLIENT_REFERENCE_NUMBER", nullable = false, precision = 15)
  private long clientReferenceNumber;

  @Column(name = "TITLE", length = 30)
  private String title;

  @Column(name = "FIRSTNAME", length = 150)
  private String firstName;

  @Column(name = "SURNAME", length = 150)
  private String surname;

  @Column(name = "SURNAME_AT_BIRTH", length = 150)
  private String surnameAtBirth;

  @Column(name = "DATE_OF_BIRTH")
  private LocalDate dateOfBirth;

  @Column(name = "GENDER", length = 30)
  private String gender;

  // TODO: Contact type is in XML format, is this needed?

  @Column(name = "COUNTRY_OF_ORIGIN", length = 3)
  private String countryOfOrigin;

  @Column(name = "MARITAL_STATUS", length = 30)
  private String maritalStatus;

  // TODO: Contacts is in XML format, is this needed?
  // TODO: Addresses is in XML format, is this needed?

  @Column(name = "CORRESPONDENCE_METHOD", length = 150)
  private String correspondenceMethod;

  @Column(name = "DISABILITY_TYPE", length = 150)
  private String disabilityType;

  @Column(name = "CORRESPONDENCE_LANGUAGE", length = 150)
  private String correspondenceLanguage;

  @Column(name = "ETHNIC_MONITORING", length = 150)
  private String ethnicMonitoring;

  @Column(name = "NO_FIX_ABODE", length = 150)
  private String noFixAbode;

  @Column(name = "NI_NUMBER", length = 60)
  private String nationalInsuranceNumber;

  @Column(name = "HOME_OFFICE_NUMBER", length = 150)
  private String homeOfficeNumber;

  // TODO: Case references is in XML format, is this needed?


}
