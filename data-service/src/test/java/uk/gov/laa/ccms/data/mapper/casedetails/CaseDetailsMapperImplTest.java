package uk.gov.laa.ccms.data.mapper.casedetails;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseDetailXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseDetailsXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.CaseStatusXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.DischargeStatusXml;
import uk.gov.laa.ccms.data.model.CaseDetail;
import uk.gov.laa.ccms.data.model.CaseStatus;

@DisplayName("Case details mapper implementation test")
class CaseDetailsMapperImplTest {

  CaseDetailsMapper mapper = new CaseDetailsMapperImpl(
      new SubmittedApplicationDetailsMapperImpl(),
      new LinkedCaseMapperImpl(),
      new AwardMapperImpl(),
      new PriorAuthorityMapperImpl(),
      new RecordHistoryMapperImpl()
  );

  @Test
  @DisplayName("Should map root level case detail values")
  void shouldMapRootLevelCaseDetailValues() {
    // Given
    CaseDetailXml caseDetailXml = CaseDetailXml.builder()
        .caseReferenceNumber("12345")
        .caseDetails(CaseDetailsXml.builder()
            .certificateType("Type")
            .certificateDate(LocalDate.of(2000, 1, 1))
            .preCertificateCosts(new BigDecimal(50))
            .legalHelpCosts(new BigDecimal(100))
            .undertakingAmount(150L)
            .build())
        .build();
    // When
    CaseDetail result = mapper.mapToCaseDetail(caseDetailXml);
    // Then
    assertEquals("12345", result.getCaseReferenceNumber());
    assertEquals("Type", result.getCertificateType());
    assertEquals(LocalDate.of(2000, 1, 1), result.getCertificateDate());
    assertEquals(BigDecimal.valueOf(50L), result.getPreCertificateCosts());
    assertEquals(BigDecimal.valueOf(100L), result.getLegalHelpCosts());
    assertEquals(BigDecimal.valueOf(150L), result.getUndertakingAmount());
  }

  @Test
  @DisplayName("Should map discharge values")
  void shouldMapDischargeValues() {
    // Given
    CaseDetailXml caseDetailXml = CaseDetailXml.builder()
        .caseDetails(CaseDetailsXml.builder()
            .dischargeStatus(DischargeStatusXml.builder()
                .reason("Reason")
                .clientContinuePvtInd(true)
                .otherDetails("Other details")
                .build())
            .build())
        .build();
    // When
    CaseDetail result = mapper.mapToCaseDetail(caseDetailXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getDischargeStatus().getReason()).isEqualTo("Reason");
      softly.assertThat(result.getDischargeStatus().getClientContinuePvtInd()).isTrue();
      softly.assertThat(result.getDischargeStatus().getOtherDetails()).isEqualTo("Other details");
    });
  }

  @Test
  @DisplayName("Should map available function values")
  void shouldMapAvailableFunctionValues() {
    // Given
    CaseDetailXml caseDetailXml = CaseDetailXml.builder()
        .caseDetails(CaseDetailsXml.builder()
            .availableFunctions(Arrays.asList("Func 1", "Func 2"))
            .build())
        .build();
    // When
    CaseDetail result = mapper.mapToCaseDetail(caseDetailXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getAvailableFunctions()).isNotEmpty();
      softly.assertThat(result.getAvailableFunctions())
          .containsExactlyInAnyOrder("Func 1", "Func 2");
    });
  }

  @Test
  @DisplayName("Should map case status values")
  void shouldMapCaseStatusValues(){
    // Given
    CaseDetailXml caseDetailXml = CaseDetailXml.builder()
        .caseDetails(CaseDetailsXml.builder()
            .caseStatus(CaseStatusXml.builder()
                .actualCaseStatus("Actual case status")
                .displayCaseStatus("Display case status")
                .statusUpdateInd(true)
                .build())
            .build()).build();
    // When
    CaseDetail result = mapper.mapToCaseDetail(caseDetailXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      CaseStatus caseStatus = result.getCaseStatus();
      softly.assertThat(caseStatus).isNotNull();
      softly.assertThat(caseStatus.getActualCaseStatus()).isEqualTo("Actual case status");
      softly.assertThat(caseStatus.getDisplayCaseStatus()).isEqualTo("Display case status");
      softly.assertThat(caseStatus.getStatusUpdateInd()).isTrue();
    });
  }
}
