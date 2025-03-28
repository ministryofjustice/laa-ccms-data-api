package uk.gov.laa.ccms.data.mapper.casedetails;

import java.math.BigDecimal;
import java.util.Collections;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.priorauthority.PriorAuthorityAttributeXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.priorauthority.PriorAuthorityXml;
import uk.gov.laa.ccms.data.model.PriorAuthority;
import uk.gov.laa.ccms.data.model.PriorAuthorityAttribute;

@DisplayName("Case detail prior authority mapper implementation test")
class PriorAuthorityMapperImplTest {

  PriorAuthorityMapper mapper = new PriorAuthorityMapperImpl();

  @Test
  @DisplayName("Should map prior authority values")
  void shouldMapPriorAuthorityValues() {
    // Given
    PriorAuthorityXml priorAuthorityXml = getPriorAuthorityXml();
    // When
    PriorAuthority result = mapper.mapToPriorAuthority(priorAuthorityXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getPriorAuthorityType()).isEqualTo("Prior authority type");
      softly.assertThat(result.getDescription()).isEqualTo("Description");
      softly.assertThat(result.getReasonForRequest()).isEqualTo("Reason for request");
      softly.assertThat(result.getRequestAmount()).isEqualTo(BigDecimal.valueOf(100L));
      softly.assertThat(result.getDecisionStatus()).isEqualTo("Decision status");
      softly.assertThat(result.getAssessedAmount()).isEqualTo(BigDecimal.valueOf(200L));
      softly.assertThat(result.getDetails()).size().isEqualTo(1);
    });
  }


  @Test
  @DisplayName("Should map prior authority attribute")
  void shouldMapPriorAuthorityAttribute(){
    // Given
    PriorAuthorityAttributeXml authorityAttributeXml =
        getAttributeXml();
    // When
    PriorAuthorityAttribute result
        = mapper.mapToPriorAuthorityAttribute(authorityAttributeXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getName()).isEqualTo("Name");
      softly.assertThat(result.getValue()).isEqualTo("Value");
    });
  }

  @Test
  @DisplayName("Should not map empty string")
  void shouldNotMapEmptyString(){
    // Given
    PriorAuthorityXml priorAuthorityXml = PriorAuthorityXml.builder()
        .assessedAmount("")
        .requestAmount("")
        .build();
    // When
    PriorAuthority result = mapper.mapToPriorAuthority(priorAuthorityXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getAssessedAmount()).isNull();
      softly.assertThat(result.getRequestAmount()).isNull();
    });
  }

  private static PriorAuthorityXml getPriorAuthorityXml() {
    return PriorAuthorityXml.builder()
        .priorAuthorityType("Prior authority type")
        .description("Description")
        .reasonForRequest("Reason for request")
        .requestAmount("100")
        .decisionStatus("Decision status")
        .assessedAmount("200")
        .details(Collections.singletonList(getAttributeXml()))
        .build();
  }

  private static PriorAuthorityAttributeXml getAttributeXml() {
    return PriorAuthorityAttributeXml.builder().name("Name").value("Value").build();
  }

}
