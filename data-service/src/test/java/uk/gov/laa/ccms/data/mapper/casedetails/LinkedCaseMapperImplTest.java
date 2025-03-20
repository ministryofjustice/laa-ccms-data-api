package uk.gov.laa.ccms.data.mapper.casedetails;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.LinkedCaseXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.ClientXml;
import uk.gov.laa.ccms.data.model.BaseClient;
import uk.gov.laa.ccms.data.model.ClientDetails;
import uk.gov.laa.ccms.data.model.LinkedCase;

@DisplayName("Linked case mapper implementation test")
class LinkedCaseMapperImplTest {

  LinkedCaseMapper mapper = new LinkedCaseMapperImpl();

  @Test
  @DisplayName("Should map root level linked case values")
  void shouldMapRootLevelLinkedCaseValues() {
    // Given
    LinkedCaseXml linkedCaseXml = LinkedCaseXml.builder()
        .caseReferenceNumber("12345")
        .providerReferenceNumber("67890")
        .categoryOfLawCode("Category of Law Code")
        .categoryOfLawDescription("Category of Law Description")
        .feeEarnerId("Fee Earner Id")
        .feeEarnerName("Fee Earner Name")
        .publicFundingAppliedIndicator(true)
        .linkType("Link Type")
        .caseStatus("Status")
        .client(getClientXml())
        .build();
    // When
    LinkedCase result = mapper.mapToLinkedCase(linkedCaseXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getClient()).isNotNull();
      softly.assertThat(result.getCaseReferenceNumber()).isEqualTo("12345");
      softly.assertThat(result.getProviderReferenceNumber()).isEqualTo("67890");
      softly.assertThat(result.getCategoryOfLawCode()).isEqualTo("Category of Law Code");
      softly.assertThat(result.getCategoryOfLawDesc()).isEqualTo("Category of Law Description");
      softly.assertThat(result.getFeeEarnerId()).isEqualTo("Fee Earner Id");
      softly.assertThat(result.getPublicFundingAppliedInd()).isTrue();
      softly.assertThat(result.getLinkType()).isEqualTo("Link Type");
      softly.assertThat(result.getCaseStatus()).isEqualTo("Status");
    });

  }

  @Test
  @DisplayName("Should map client details")
  void shouldMapClientDetails(){
    // Given
    ClientXml clientXml = getClientXml();
    // When
    BaseClient result = mapper.mapToBaseClient(clientXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getClientReferenceNumber()).isEqualTo("12345");
      softly.assertThat(result.getFirstName()).isEqualTo("First Name");
      softly.assertThat(result.getSurname()).isEqualTo("Surname");
    });

  }

  private static ClientXml getClientXml() {
    return ClientXml.builder()
        .clientReferenceNumber("12345")
        .firstName("First Name")
        .surname("Surname")
        .build();
  }
}
