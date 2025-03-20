package uk.gov.laa.ccms.data.mapper.casedetails;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.CaseDetailXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.CaseDetailsXml;
import uk.gov.laa.ccms.data.model.CaseDetail;

@DisplayName("Case details mapper implementation test")
class CaseDetailsMapperImplTest {

  CaseDetailsMapper mapper = new CaseDetailsMapperImpl();

  @Test
  @DisplayName("Should map root level case detail values")
  void shouldMapRootLevelCaseDetailValues() {
    // Given
    CaseDetailXml caseDetailXml = CaseDetailXml.builder()
        .caseReferenceNumber("12345")
        .caseDetails(CaseDetailsXml.builder()
            .certificateType("Type")
            .certificateDate(LocalDate.of(2000, 1, 1))
            .preCertificateCosts(50L)
            .legalHelpCosts(100L)
            //.undertakingAmount(150L)
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

}
