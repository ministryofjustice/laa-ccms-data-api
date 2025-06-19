package uk.gov.laa.ccms.data.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileReader;
import java.io.StringReader;
import java.sql.Array;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.gov.laa.ccms.data.exception.EbsApiRuntimeException;
import uk.gov.laa.ccms.data.model.AssessmentType;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetail;

@ExtendWith(MockitoExtension.class)
@DisplayName("Case assessment repository test")
class CaseAssessmentRepositoryTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private CaseAssessmentRepository repository;

  @Test
  @DisplayName("Should return details")
  void shouldReturnDetails() throws Exception {
    // Given
    Struct structMock = mock(Struct.class);
    Array arrayMock = mock(Array.class);
    Clob clobMock = mock(Clob.class);
    Object[] structAttributes = new Object[] {
        "EntityName", "InstanceLabel", "AttributeName", clobMock, "AttributeType", "true"
    };
    when(structMock.getAttributes()).thenReturn(structAttributes);
    Object[] arrayData = new Object[] { structMock };
    when(arrayMock.getArray()).thenReturn(arrayData);
    when(clobMock.getCharacterStream()).thenReturn(new StringReader("Value"));
    when(jdbcTemplate.queryForObject(anyString(), eq(Array.class), anyString(), anyString()))
        .thenReturn(arrayMock);

    // When
    List<CaseAssessmentDetail> result = repository.getCaseAssessmentDetails(
        "CASE123", AssessmentType.MEANS);

    // Then
    assertThat(result).isNotNull();
    assertThat(result.size()).isEqualTo(1);
    CaseAssessmentDetail caseAssessmentDetail = result.getFirst();
    assertThat(caseAssessmentDetail.getEntityName()).isEqualTo("EntityName");
    assertThat(caseAssessmentDetail.getInstanceLabel()).isEqualTo("InstanceLabel");
    assertThat(caseAssessmentDetail.getAttributeName()).isEqualTo("AttributeName");
    assertThat(caseAssessmentDetail.getAttributeValue()).isEqualTo("Value");
    assertThat(caseAssessmentDetail.getAttributeType()).isEqualTo("AttributeType");
    assertThat(caseAssessmentDetail.getAttributeUserDefinedIndicator()).isTrue();
  }

  @Test
  @DisplayName("Should throw exception when can't return array")
  void shouldThrowExceptionWhenCantReturnArray() throws Exception {
    // Given
    Array arrayMock = mock(Array.class);
    when(arrayMock.getArray()).thenThrow(new SQLException());
    when(jdbcTemplate.queryForObject(anyString(), eq(Array.class), anyString(), anyString()))
        .thenReturn(arrayMock);

    // When/Then
    assertThrows(EbsApiRuntimeException.class, () ->
        repository.getCaseAssessmentDetails("CASE123", AssessmentType.MEANS));
  }


}