package uk.gov.laa.ccms.data.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.OracleIntegrationTestInterface;
import uk.gov.laa.ccms.data.entity.ClientDetail;

@SpringBootTest
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_CLASS, scripts = {
    "/sql/get_client_details_create_schema.sql"
})
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_CLASS, scripts = {
    "/sql/get_client_details_drop_schema.sql"
})
@DisplayName("Client search repository integration tests")
public class ClientDetailRepositoryIntegrationTest implements OracleIntegrationTestInterface {

  @Autowired
  private ClientDetailRepository repository;

  @ParameterizedTest
  @NullAndEmptySource
  @DisplayName("Should return two client details")
  void shouldReturnTwoClientDetails(String emptyStringInput) {
    // Given
    // When
    Page<ClientDetail> result = repository.findAll(emptyStringInput, emptyStringInput,
        null,
        emptyStringInput, emptyStringInput, emptyStringInput, emptyStringInput,
        PageRequest.of(0, 10));
    // Then
    assertEquals(2L, result.getTotalElements());
  }

  @Test
  @DisplayName("Should return single client detail")
  void shouldReturnSingleClientDetail(){
    // Given
    // When
    Page<ClientDetail> result = repository.findAll(null, null, null,
        null, null, null, null,
        PageRequest.of(0, 1));
    // Then
    assertEquals(1L, result.getContent().size());
    assertEquals(2, result.getTotalPages());
    assertEquals(2L, result.getTotalElements());
  }

  @Test
  @DisplayName("Should return single client filter equals first name")
  void shouldReturnSingleClientFilterEqualsFirstName(){
    // Given
    String firstName = "john";
    // When
    Page<ClientDetail> result = repository.findAll(firstName,
        null, null, null, null,
        null, null, PageRequest.of(0, 10));
    // Then
    assertEquals(1L, result.getContent().size());
    assertEquals("John", result.getContent().getFirst().getFirstName());
  }

  @Test
  @DisplayName("Should return multiple clients filter like first name")
  void shouldReturnMultipleClientFilterLikeFirstName(){
    // Given
    String firstName = "j";
    // When
    Page<ClientDetail> result = repository.findAll(firstName,
        null, null, null, null,
        null, null, PageRequest.of(0, 10));
    // Then
    assertEquals(2L, result.getContent().size());
    assertEquals("John", result.getContent().getFirst().getFirstName());
    assertEquals("Jane", result.getContent().get(1).getFirstName());
  }

  @Test
  @DisplayName("Should return single client filter equals surname at birth")
  void shouldReturnSingleClientFilterEqualsSurnameAtBirth(){
    // Given
    String surname = "smithson";
    // When
    Page<ClientDetail> result = repository.findAll(null,
        surname, null, null, null,
        null, null, PageRequest.of(0, 10));
    // Then
    assertEquals(1L, result.getContent().size());
    assertEquals("Doe", result.getContent().getFirst().getSurname());
    assertEquals("Smithson", result.getContent().getFirst().getSurnameAtBirth());
  }

  @Test
  @DisplayName("Should return mulitple clients filter like surname at birth")
  void shouldReturnMultipleClientsFilterLikeSurnameAtBirth(){
    // Given
    String surname = "smith";
    // When
    Page<ClientDetail> result = repository.findAll(null,
        surname, null, null, null,
        null, null, PageRequest.of(0, 10));
    // Then
    assertEquals(2L, result.getContent().size());
    assertEquals("Smithson", result.getContent().getFirst().getSurnameAtBirth());
    assertEquals("Smith", result.getContent().get(1).getSurnameAtBirth());
  }

  @Test
  @DisplayName("Should return single client filer equals date of birth")
  void shouldReturnSingleClientFilterEqualsDateOfBirth(){
    // Given
    LocalDate dateOfBirth = LocalDate.of(1985, 06, 15);
    // When
    Page<ClientDetail> result = repository.findAll(null, null, dateOfBirth,
        null, null, null, null,
        PageRequest.of(0, 10));
    // Then
    assertEquals(1L, result.getContent().size());
    assertEquals(dateOfBirth, result.getContent().getFirst().getDateOfBirth());
  }

  @Test
  @DisplayName("Should return single client equals gender")
  void shouldReturnSingleClientFilterEqualsGender(){
    // Given
    String gender = "male";
    // When
    Page<ClientDetail> result = repository.findAll(null, null, null,
        gender, null, null, null,
        PageRequest.of(0, 10));
    // Then
    assertEquals(1L, result.getContent().size());
    assertEquals("Male", result.getContent().getFirst().getGender());
  }

  @Test
  @DisplayName("Should return single client filter equals gender alt")
  void shouldReturnSingleClientFilterEqualsGenderAlt(){
    // Given
    String gender = "FEMALE";
    // When
    Page<ClientDetail> result = repository.findAll(null, null,
        null, gender, null, null,
        null, PageRequest.of(0, 10));
    // Then
    assertEquals(1L, result.getContent().size());
    assertEquals("Female", result.getContent().getFirst().getGender());
  }

  @Test
  @DisplayName("Should return single client filter equals client reference number")
  void shouldReturnSingleClientFilterEqualsClientReferenceNumber(){
    // Given
    String clientReferenceNumber = "100000000000001";
    // When
    Page<ClientDetail> result = repository.findAll(null, null,
        null, null, clientReferenceNumber,
        null, null, PageRequest.of(0, 10));
    // Then
    assertEquals(1L, result.getContent().size());
    assertEquals(100000000000001L, result.getContent().getFirst()
        .getClientReferenceNumber());
  }

  @Test
  @DisplayName("Should return multiple client filter like client reference number")
  void shouldReturnMultipleClientFilterLikeClientReferenceNumber(){
    // Given
    String clientReferenceNumber = "10000000000";
    // When
    Page<ClientDetail> result = repository.findAll(null, null,
        null, null, clientReferenceNumber,
        null, null, PageRequest.of(0, 10));
    // Then
    assertEquals(2L, result.getContent().size());
    assertEquals(100000000000001L, result.getContent().getFirst()
        .getClientReferenceNumber());
    assertEquals(100000000000002L, result.getContent().get(1)
        .getClientReferenceNumber());
  }

  @Test
  @DisplayName("Should return single client filter equals home office reference number")
  void shouldReturnSingleClientFilterEqualsHomeOfficeReferenceNumber(){
    // Given
    String homeOfficeNumber = "HO123456";
    // When
    Page<ClientDetail> result = repository.findAll(null, null,
        null, null, null,
        homeOfficeNumber, null, PageRequest.of(0, 10));
    // Then
    assertEquals(1L, result.getContent().size());
    assertEquals("HO123456", result.getContent().getFirst()
        .getHomeOfficeNumber());
  }

  @Test
  @DisplayName("Should return multiple clients filter like home office reference number")
  void shouldReturnMultipleClientsFilterLikeHomeOfficeReferenceNumber(){
    // Given
    String homeOfficeNumber = "HO";
    // When
    Page<ClientDetail> result = repository.findAll(null, null,
        null, null, null,
        homeOfficeNumber, null, PageRequest.of(0, 10));
    // Then
    assertEquals(2L, result.getContent().size());
    assertEquals("HO123456", result.getContent().getFirst()
        .getHomeOfficeNumber());
    assertEquals("HO987654", result.getContent().get(1)
        .getHomeOfficeNumber());
  }

  @Test
  @DisplayName("Should return single client filter equals national insurance number")
  void shouldReturnSingleClientFilterEqualsNationalInsuranceNumber(){
    // Given
    String nationalInsuranceNumber = "AB123456C";
    // When
    Page<ClientDetail> result = repository.findAll(null, null,
        null, null, null,
        null, nationalInsuranceNumber,
        PageRequest.of(0, 10));
    // Then
    assertEquals(1L, result.getContent().size());
    assertEquals("AB123456C", result.getContent().getFirst()
        .getNationalInsuranceNumber());
  }

  @Test
  @DisplayName("Should return multiple clients filter like national insurance number")
  void shouldReturnMultipleClientsFilterLikeNationalInsuranceNumber(){
    // Given
    String nationalInsuranceNumber = "123";
    // When
    Page<ClientDetail> result = repository.findAll(null, null,
        null, null, null,
        null, nationalInsuranceNumber,
        PageRequest.of(0, 10));
    // Then
    assertEquals(2L, result.getContent().size());
    assertEquals("AB123456C", result.getContent().getFirst()
        .getNationalInsuranceNumber());
    assertEquals("CD123654E", result.getContent().get(1)
        .getNationalInsuranceNumber());
  }

  @Test
  @DisplayName("Should sort by first name")
  void shouldSortByFirstName(){
    // Given
    // When
    PageRequest pageable = PageRequest.of(0, 10,
        Sort.by(Sort.Order.asc("FIRSTNAME")));
    Page<ClientDetail> result = repository.findAll(null, null,
        null, null, null,
        null, null,
        pageable);
    // Then
    assertEquals(2L, result.getContent().size());
    assertEquals("Jane", result.getContent().getFirst()
        .getFirstName());
    assertEquals("John", result.getContent().get(1)
        .getFirstName());
  }

}
