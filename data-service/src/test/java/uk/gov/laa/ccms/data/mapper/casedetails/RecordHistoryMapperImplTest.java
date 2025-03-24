package uk.gov.laa.ccms.data.mapper.casedetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.RecordHistoryXml;
import uk.gov.laa.ccms.data.mapper.xml.common.UserXml;
import uk.gov.laa.ccms.data.model.RecordHistory;

@DisplayName("Record history mapper implementation test")
class RecordHistoryMapperImplTest {

  RecordHistoryMapper mapper = new RecordHistoryMapperImpl();

  @Test
  @DisplayName("Should map all base values")
  void shouldMapAllBaseValues(){
    // Given
    RecordHistoryXml recordHistoryXml = RecordHistoryXml
        .builder()
        .dateCreated(LocalDateTime.of(LocalDate.of(2015, 5, 5), LocalTime.of(12, 15)))
        .dateLastUpdated(LocalDateTime.of(LocalDate.of(2025, 6, 6), LocalTime.of(13, 30)))
        .build();
    // When
    RecordHistory result = mapper.mapToRecordHistory(recordHistoryXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getDateCreated()).isEqualTo(LocalDateTime.of(LocalDate.of(2015, 5, 5), LocalTime.of(12, 15)));
      softly.assertThat(result.getDateLastUpdated()).isEqualTo(LocalDateTime.of(LocalDate.of(2025, 6, 6), LocalTime.of(13, 30)));
    });
  }

  @Test
  @DisplayName("Should map user values")
  void shouldMapUserValues(){
    // Given
    RecordHistoryXml recordHistoryXml = RecordHistoryXml.builder()
        .createdBy(UserXml.builder()
            .userLoginId(1)
            .userName("Created user name")
            .userType("Created user type")
            .build())
        .lastUpdatedBy(UserXml.builder()
            .userLoginId(2)
            .userName("Last updated user name")
            .userType("Last updated user type")
            .build())
        .build();
    // When
    RecordHistory result = mapper.mapToRecordHistory(recordHistoryXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getCreatedBy().getUserId()).isEqualTo(1);
      softly.assertThat(result.getCreatedBy().getUsername()).isEqualTo("Created user name");
      softly.assertThat(result.getCreatedBy().getUserType()).isEqualTo("Created user type");
      softly.assertThat(result.getLastUpdatedBy().getUserId()).isEqualTo(2);
      softly.assertThat(result.getLastUpdatedBy().getUsername()).isEqualTo("Last updated user name");
      softly.assertThat(result.getLastUpdatedBy().getUserType()).isEqualTo("Last updated user type");
    });
  }
}
