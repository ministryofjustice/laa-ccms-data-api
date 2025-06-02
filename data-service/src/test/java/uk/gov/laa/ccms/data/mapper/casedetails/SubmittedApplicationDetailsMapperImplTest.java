package uk.gov.laa.ccms.data.mapper.casedetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.ApplicationDetailsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.ContactDetailsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.common.ContactUserIdXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CostLimitationXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.OrganisationXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.ScopeLimitationXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.CategoryOfLawXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.ClientXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.CorrespondenceAddressXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.ProceedingDetailsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.ProceedingXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.ProviderDetailsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty.NameXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty.OtherPartyDetailXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty.OtherPartyXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty.PersonXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.common.AddressXml;
import uk.gov.laa.ccms.data.model.AddressDetail;
import uk.gov.laa.ccms.data.model.BaseClient;
import uk.gov.laa.ccms.data.model.CategoryOfLaw;
import uk.gov.laa.ccms.data.model.ContactDetails;
import uk.gov.laa.ccms.data.model.CostLimitation;
import uk.gov.laa.ccms.data.model.NameDetail;
import uk.gov.laa.ccms.data.model.OtherParty;
import uk.gov.laa.ccms.data.model.OtherPartyOrganisation;
import uk.gov.laa.ccms.data.model.OtherPartyPerson;
import uk.gov.laa.ccms.data.model.Proceeding;
import uk.gov.laa.ccms.data.model.ProviderDetails;
import uk.gov.laa.ccms.data.model.ScopeLimitation;
import uk.gov.laa.ccms.data.model.SubmittedApplicationDetails;

@DisplayName("Submitted application details mapper implementation test")
class SubmittedApplicationDetailsMapperImplTest {

  SubmittedApplicationDetailsMapper mapper = new SubmittedApplicationDetailsMapperImpl();

  @Test
  @DisplayName("Should map root level submitted application details")
  void shouldMapRootLevelSubmittedApplicationDetails(){
    // Given
    ApplicationDetailsXml applicationDetailsXml = ApplicationDetailsXml.builder()
        .client(getClientXml())
        .correspondenceAddress(getCorrespondenceAddress())
        .providerDetails(getProviderDetails())
        .categoryOfLaw(getCategoryOfLaw())
        .otherParties(Arrays.asList(getOtherPartyPerson(), getOtherPartyOrganisation()))
        .proceedings(Collections.singletonList(getProceeding()))
        .preferredAddress("Preferred address")
        .purposeOfApplication("Purpose of app")
        .purposeOfHearing("Purpose of hear")
        .applicationAmendmentType("Amendment type")
        .fixedHearingDateIndicator(true)
        .highProfileCaseIndicator(true)
        .dateOfFirstAttendance(LocalDate.of(2010, 1, 10))
        .dateOfHearing(LocalDate.of(2011, 2, 11))
        .devolvedPowersDate(LocalDate.of(2012, 3, 12))
        .certificateType("Cert type")
        .build();
    // When
    SubmittedApplicationDetails result = mapper
        .mapToSubmittedApplicationDetails(applicationDetailsXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      // Check objects were mapped
      softly.assertThat(result.getClient()).isNotNull();
      softly.assertThat(result.getCorrespondenceAddress()).isNotNull();
      softly.assertThat(result.getProviderDetails()).isNotNull();
      softly.assertThat(result.getCategoryOfLaw()).isNotNull();
      softly.assertThat(result.getOtherParties()).hasSize(2);
      softly.assertThat(result.getProceedings()).hasSize(1);
      // Check based values
      softly.assertThat(result.getPreferredAddress()).isEqualTo("Preferred address");
      softly.assertThat(result.getDateOfFirstAttendance()).isEqualTo(LocalDate.of(2010, 1, 10));
      softly.assertThat(result.getPurposeOfApplication()).isEqualTo("Purpose of app");
      softly.assertThat(result.getFixedHearingDateInd()).isTrue();
      softly.assertThat(result.getDateOfHearing()).isEqualTo(LocalDate.of(2011, 2, 11));
      softly.assertThat(result.getPurposeOfHearing()).isEqualTo("Purpose of hear");
      softly.assertThat(result.getHighProfileCaseInd()).isTrue();
      softly.assertThat(result.getDevolvedPowersDate()).isEqualTo(LocalDate.of(2012, 3, 12));
      softly.assertThat(result.getApplicationAmendmentType()).isEqualTo("Amendment type");
      softly.assertThat(result.getCertificateType()).isEqualTo("Cert type");
    });
  }

  @Test
  @DisplayName("Should map base client")
  void shouldMapBaseClient(){
    // Given
    ClientXml clientXml = getClientXml();
    // When
    BaseClient result = mapper.mapToClientDetails(clientXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getClientReferenceNumber()).isEqualTo("Client ref");
      softly.assertThat(result.getFirstName()).isEqualTo("First name");
      softly.assertThat(result.getSurname()).isEqualTo("Surname");
    });
  }

  @Test
  @DisplayName("Should map address detail")
  void shouldMapAddressDetail(){
    // Given
    CorrespondenceAddressXml addressXml = getCorrespondenceAddress();
    // When
    AddressDetail result = mapper.mapToAddressDetail(addressXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getAddressId()).isEqualTo("ID");
      softly.assertThat(result.getHouse()).isEqualTo("House or title");
      softly.assertThat(result.getAddressLine1()).isEqualTo("Line 1");
      softly.assertThat(result.getAddressLine2()).isEqualTo("Line 2");
      softly.assertThat(result.getAddressLine3()).isEqualTo("Line 3");
      softly.assertThat(result.getAddressLine4()).isEqualTo("Line 4");
      softly.assertThat(result.getCity()).isEqualTo("City");
      softly.assertThat(result.getCountry()).isEqualTo("Country");
      softly.assertThat(result.getCounty()).isEqualTo("County");
      softly.assertThat(result.getProvince()).isEqualTo("Province");
      softly.assertThat(result.getState()).isEqualTo("State");
      softly.assertThat(result.getPostalCode()).isEqualTo("Postal code");
    });
  }



  @Test
  @DisplayName("Should map provider details")
  void shouldMapProviderDetails(){
    // Given
    ProviderDetailsXml providerDetailsXml = getProviderDetails();
    // When
    ProviderDetails result = mapper.mapToProviderDetail(providerDetailsXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getProviderCaseReferenceNumber()).isEqualTo("Case ref");
      softly.assertThat(result.getProviderFirmId()).isEqualTo("789");
      softly.assertThat(result.getProviderOfficeId()).isEqualTo("456");
      softly.assertThat(result.getContactUserId().getLoginId()).isEqualTo("123");
      softly.assertThat(result.getContactUserId().getUsername()).isEqualTo("UserName");
      softly.assertThat(result.getFeeEarnerContactId()).isEqualTo("123456");
      softly.assertThat(result.getSupervisorContactId()).isEqualTo("654321");
    });
  }

  @Test
  @DisplayName("Should map category of law")
  void shouldMapCategoryOfLaw(){
    // Given
    CategoryOfLawXml categoryOfLawXml = getCategoryOfLaw();
    // When
    CategoryOfLaw result = mapper.mapToCategoryOfLaw(categoryOfLawXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getCategoryOfLawCode()).isEqualTo("Law code");
      softly.assertThat(result.getCategoryOfLawDescription()).isEqualTo("Law desc");
      softly.assertThat(result.getRequestedAmount()).isEqualTo(BigDecimal.valueOf(123));
      softly.assertThat(result.getGrantedAmount()).isEqualTo(BigDecimal.valueOf(115));
      softly.assertThat(result.getTotalPaidToDate()).isEqualTo(BigDecimal.valueOf(50));
      CostLimitation costLimitation = result.getCostLimitations().getFirst();
      softly.assertThat(costLimitation.getCostLimitId()).isEqualTo("10");
      softly.assertThat(costLimitation.getBillingProviderId()).isEqualTo("20");
      softly.assertThat(costLimitation.getBillingProviderName()).isEqualTo("Billing name");
      softly.assertThat(costLimitation.getCostCategory()).isEqualTo("Cost category");
      softly.assertThat(costLimitation.getPaidToDate()).isEqualTo(BigDecimal.valueOf(40));
      softly.assertThat(costLimitation.getAmount()).isEqualTo(BigDecimal.valueOf(50));
    });
  }


  @Test
  @DisplayName("Should map other parties person data")
  void shouldMapOtherPartiesPersonData() {
    // Given
    OtherPartyXml otherPartyXml = getOtherPartyPerson();
    // When
    OtherParty result = mapper.mapToOtherParty(otherPartyXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getOtherPartyId()).isEqualTo("123");
      softly.assertThat(result.getSharedInd()).isTrue();
      OtherPartyPerson person = result.getPerson();
      NameDetail name = person.getName();
      softly.assertThat(name.getTitle()).isEqualTo("Title");
      softly.assertThat(name.getFirstName()).isEqualTo("First name");
      softly.assertThat(name.getMiddleName()).isEqualTo("Middle name");
      softly.assertThat(name.getSurname()).isEqualTo("Surname");
      softly.assertThat(person.getDateOfBirth()).isEqualTo(LocalDate.of(2010, 1, 1));
      AddressDetail address = person.getAddress();
      softly.assertThat(address.getAddressId()).isEqualTo("1");
      softly.assertThat(address.getHouse()).isEqualTo("House or title");
      softly.assertThat(address.getAddressLine1()).isEqualTo("Line 1");
      softly.assertThat(address.getAddressLine2()).isEqualTo("Line 2");
      softly.assertThat(address.getAddressLine3()).isEqualTo("Line 3");
      softly.assertThat(address.getAddressLine4()).isEqualTo("Line 4");
      softly.assertThat(address.getCity()).isEqualTo("City");
      softly.assertThat(address.getCountry()).isEqualTo("Country");
      softly.assertThat(address.getCounty()).isEqualTo("County");
      softly.assertThat(address.getProvince()).isEqualTo("Province");
      softly.assertThat(address.getState()).isEqualTo("State");
      softly.assertThat(address.getPostalCode()).isEqualTo("Postal code");
      softly.assertThat(person.getRelationToClient()).isEqualTo("Relation to client");
      softly.assertThat(person.getRelationToCase()).isEqualTo("Relation to case");
      softly.assertThat(person.getNiNumber()).isEqualTo("123456");
      softly.assertThat(person.getContactName()).isEqualTo("Contact name");
      ContactDetails contactDetails = person.getContactDetails();
      softly.assertThat(contactDetails.getTelephoneHome()).isEqualTo("123");
      softly.assertThat(contactDetails.getTelephoneWork()).isEqualTo("456");
      softly.assertThat(contactDetails.getMobileNumber()).isEqualTo("789");
      softly.assertThat(contactDetails.getEmailAddress()).isEqualTo("email@email.com");
      softly.assertThat(contactDetails.getFax()).isEqualTo("246");
      softly.assertThat(person.getOrganisationName()).isEqualTo("Org Name");
      softly.assertThat(person.getEmployersName()).isEqualTo("Employers Name");
      softly.assertThat(person.getEmploymentStatus()).isEqualTo("Employment Status");
      softly.assertThat(person.getOrganisationAddress()).isEqualTo("Organization Address");
      softly.assertThat(person.getPartyLegalAidedInd()).isTrue();
      softly.assertThat(person.getCertificateNumber()).isEqualTo("76543");
      softly.assertThat(person.getCourtOrderedMeansAssessment()).isTrue();
      softly.assertThat(person.getAssessedIncomeFrequency()).isEqualTo("Income frequency");
      softly.assertThat(person.getAssessedIncome()).isEqualTo(BigDecimal.valueOf(20000));
      softly.assertThat(person.getAssessedAssets()).isEqualTo(BigDecimal.valueOf(10000));
      softly.assertThat(person.getAssessmentDate()).isEqualTo(LocalDate.of(2012, 1, 1));
      softly.assertThat(person.getPublicFundingAppliedInd()).isTrue();
      softly.assertThat(person.getOtherInformation()).isEqualTo("Other information");
    });
  }

  @Test
  @DisplayName("Should map other parties organisation data")
  void shouldMapOtherPartiesOrganisationData(){
    // Given
    OtherPartyXml otherPartyXml = getOtherPartyOrganisation();
    // When
    OtherParty result = mapper.mapToOtherParty(otherPartyXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getOtherPartyId()).isEqualTo("123");
      softly.assertThat(result.getSharedInd()).isTrue();
      OtherPartyOrganisation organisation = result.getOrganisation();
      softly.assertThat(organisation.getOrganisationName()).isEqualTo("Org Name");
      softly.assertThat(organisation.getOrganisationType()).isEqualTo("Org type");
      softly.assertThat(organisation.getCurrentlyTrading()).isTrue();
      softly.assertThat(organisation.getRelationToClient()).isEqualTo("Relation to client");
      softly.assertThat(organisation.getRelationToCase()).isEqualTo("Relation to case");
      softly.assertThat(organisation.getContactName()).isEqualTo("Contact name");
      softly.assertThat(organisation.getOtherInformation()).isEqualTo("Other information");
      AddressDetail address = organisation.getAddress();
      softly.assertThat(address.getAddressId()).isEqualTo("1");
      softly.assertThat(address.getHouse()).isEqualTo("House or title");
      softly.assertThat(address.getAddressLine1()).isEqualTo("Line 1");
      softly.assertThat(address.getAddressLine2()).isEqualTo("Line 2");
      softly.assertThat(address.getAddressLine3()).isEqualTo("Line 3");
      softly.assertThat(address.getAddressLine4()).isEqualTo("Line 4");
      softly.assertThat(address.getCity()).isEqualTo("City");
      softly.assertThat(address.getCountry()).isEqualTo("Country");
      softly.assertThat(address.getCounty()).isEqualTo("County");
      softly.assertThat(address.getProvince()).isEqualTo("Province");
      softly.assertThat(address.getState()).isEqualTo("State");
      softly.assertThat(address.getPostalCode()).isEqualTo("Postal code");
    });
  }

  @Test
  @DisplayName("Should map proceedings")
  void shouldMapProceedings(){
    // Given
    ProceedingXml proceedingXml = getProceeding();
    // When
    Proceeding result = mapper.mapToProceedingDetail(proceedingXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getProceedingCaseId()).isEqualTo("12345");
      softly.assertThat(result.getDateApplied()).isEqualTo(LocalDate.of(2020, 5, 1));
      softly.assertThat(result.getStatus()).isEqualTo("Status");
      softly.assertThat(result.getLeadProceedingIndicator()).isTrue();
      softly.assertThat(result.getOutcomeCourtCaseNumber()).isEqualTo("67890");
      softly.assertThat(result.getAvailableFunctions())
          .containsExactlyInAnyOrder("Function 1", "Function 2");
      softly.assertThat(result.getProceedingType()).isEqualTo("Proceeding type");
      softly.assertThat(result.getProceedingDescription()).isEqualTo("Proceeding description");
      softly.assertThat(result.getDateCostsValid()).isEqualTo(LocalDate.of(2021, 6, 2));
      softly.assertThat(result.getOrderType()).isEqualTo("Order type");
      softly.assertThat(result.getMatterType()).isEqualTo("Matter type");
      softly.assertThat(result.getLevelOfService()).isEqualTo("Level of service");
      softly.assertThat(result.getStage()).isEqualTo("Stage");
      softly.assertThat(result.getClientInvolvementType()).isEqualTo("Client involvement type");
      ScopeLimitation scopeLimitation = result.getScopeLimitations().getFirst();
      softly.assertThat(scopeLimitation.getScopeLimitationId()).isEqualTo("Scope limitation id");
      softly.assertThat(scopeLimitation.getScopeLimitation()).isEqualTo("Scope limitation");
      softly.assertThat(scopeLimitation.getScopeLimitationWording()).isEqualTo("Scope limitation wording");
      softly.assertThat(scopeLimitation.getDelegatedFunctionsApply()).isTrue();
    });
  }

  private static ProceedingXml getProceeding() {
    return ProceedingXml.builder()
        .proceedingCaseId("12345")
        .dateApplied(LocalDate.of(2020, 5, 1))
        .status("Status")
        .leadProceedingIndicator(true)
        .outcomeCourtCaseNumber("67890")
        .availableFunctions(Arrays.asList("Function 1", "Function 2"))
        .proceedingDetails(ProceedingDetailsXml.builder()
            .proceedingType("Proceeding type")
            .proceedingDescription("Proceeding description")
            .dateCostsValid(LocalDate.of(2021, 6, 2))
            .orderType("Order type")
            .matterType("Matter type")
            .levelOfService("Level of service")
            .stage("Stage")
            .clientInvolvementType("Client involvement type")
            .scopeLimitations(Collections.singletonList(ScopeLimitationXml.builder()
                .scopeLimitationId("Scope limitation id")
                .scopeLimitation("Scope limitation")
                .scopeLimitationWording("Scope limitation wording")
                .delegatedFunctionsApply(true)
                .build()))
            .build())
        .build();
  }


  private static ClientXml getClientXml() {
    return ClientXml.builder()
        .clientReferenceNumber("Client ref")
        .firstName("First name")
        .surname("Surname")
        .build();
  }

  private static CorrespondenceAddressXml getCorrespondenceAddress() {
    return CorrespondenceAddressXml.builder()
        .addressId("ID")
        .houseOrTitle("House or title")
        .addressLineOne("Line 1")
        .addressLineTwo("Line 2")
        .addressLineThree("Line 3")
        .addressLineFour("Line 4")
        .city("City")
        .country("Country")
        .county("County")
        .state("State")
        .province("Province")
        .postalCode("Postal code")
        .build();
  }

  private static CategoryOfLawXml getCategoryOfLaw() {
    return CategoryOfLawXml.builder()
        .categoryOfLawCode("Law code")
        .categoryOfLawDescription("Law desc")
        .requestedAmount("123")
        .grantedAmount("115")
        .totalPaidToDate("50")
        .costLimitations(Collections.singletonList(CostLimitationXml.builder()
            .costLimitId("10")
            .billingProviderId("20")
            .billingProviderName("Billing name")
            .costCategory("Cost category")
            .paidToDate(BigDecimal.valueOf(40))
            .amount(BigDecimal.valueOf(50))
            .build()))
        .build();
  }

  private static OtherPartyXml getOtherPartyPerson() {
    return OtherPartyXml.builder()
        .otherPartyId("123")
        .sharedIndicator(true)
        .otherPartyDetail(OtherPartyDetailXml.builder()
            .person(getPerson())
            .build())
        .build();
  }

  private static ProviderDetailsXml getProviderDetails() {
    return ProviderDetailsXml.builder()
        .providerCaseReferenceNumber("Case ref")
        .providerFirmId(789)
        .providerOfficeId(456)
        .contactUserId(ContactUserIdXml.builder()
            .userLoginId(123)
            .userName("UserName")
            .build())
        .feeEarnerContactId("123456")
        .supervisorContactId("654321")
        .build();
  }


  private static PersonXml getPerson() {
    return PersonXml.builder()
        .name(NameXml.builder()
            .title("Title")
            .firstName("First name")
            .middleName("Middle name")
            .surname("Surname")
            .build())
        .address(AddressXml.builder().addressId("1")
            .house("House or title")
            .addressLine1("Line 1")
            .addressLine2("Line 2")
            .addressLine3("Line 3")
            .addressLine4("Line 4")
            .city("City")
            .country("Country")
            .county("County")
            .state("State")
            .province("Province")
            .postalCode("Postal code")
            .build())
        .contactDetails(new ContactDetailsXml("123", "456",
            "789","email@email.com","246"))
        .dateOfBirth(LocalDate.of(2010, 1, 1))
        .relationToClient("Relation to client")
        .relationToCase("Relation to case")
        .contactName("Contact name")
        .partyLegalAidedInd(true)
        .courtOrderedMeansAssessment(true)
        .assessedIncome(BigDecimal.valueOf(20000))
        .assessedIncomeFrequency("Income frequency")
        .assessedAssets(BigDecimal.valueOf(10000))
        .assessmentDate(LocalDate.of(2012, 1, 1))
        .publicFundingAppliedInd(true)
        .niNumber("123456")
        .organisationName("Org Name")
        .organisationAddress("Organization Address")
        .employersName("Employers Name")
        .employmentStatus("Employment Status")
        .certificateNumber("76543")
        .courtOrderedMeansAssessment(true)
        .otherInformation("Other information")
        .build();
  }


  private static OtherPartyXml getOtherPartyOrganisation() {
    return OtherPartyXml.builder()
        .otherPartyId("123")
        .sharedIndicator(true)
        .otherPartyDetail(OtherPartyDetailXml.builder()
            .organisation(OrganisationXml.builder()
                .organisationName("Org Name")
                .organisationType("Org type")
                .currentlyTradingFlag("Y")
                .relationToClient("Relation to client")
                .relationToCase("Relation to case")
                .contactName("Contact name")
                .otherInformation("Other information")
                .address(AddressXml.builder()
                    .addressId("1")
                    .house("House or title")
                    .addressLine1("Line 1")
                    .addressLine2("Line 2")
                    .addressLine3("Line 3")
                    .addressLine4("Line 4")
                    .city("City")
                    .country("Country")
                    .county("County")
                    .province("Province")
                    .state("State")
                    .postalCode("Postal code")
                    .build())
                .build())
            .build())
        .build();
  }
}