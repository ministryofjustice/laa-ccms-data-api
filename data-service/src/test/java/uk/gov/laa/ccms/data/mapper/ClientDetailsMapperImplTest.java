package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.gov.laa.ccms.data.entity.ClientDetail;
import uk.gov.laa.ccms.data.model.ClientDetails;
import uk.gov.laa.ccms.data.model.ClientSummary;

@DisplayName("Client details mapper implementation test")
class ClientDetailsMapperImplTest {

  ClientDetailsMapper mapper = new ClientDetailsMapperImpl();

  @Test
  @DisplayName("Should map empty client summary")
  void shouldMapEmptyClientSummary(){
    // Given
    ClientDetail entity = new ClientDetail();
    // When
    ClientSummary result = mapper.mapToClientSummary(entity);
    // Then
    assertNotNull(result);
    assertEquals("", result.getPostalCode());
  }
  
  @Test
  @DisplayName("Should map client summary")
  void shouldMapToClientSummary(){
    // Given
    ClientDetail entity = getTestClientDetail();
    // When
    ClientSummary result = mapper.mapToClientSummary(entity);
    // Then
    assertEquals("Surname At Birth", result.getSurnameAtBirth());
    assertEquals("First Surname", result.getFullName());
    assertEquals(LocalDate.of(2000, 1, 1), result.getDateOfBirth());
    assertEquals("Gender", result.getGender());
    assertEquals("Home Office Number", result.getHomeOfficeReference());
    assertEquals("First", result.getFirstName());
    assertEquals("Surname", result.getSurname());
  }

  @Test
  @DisplayName("Should map postcode from address")
  void shouldMapPostcodeFromAddress(){
    // Given
    ClientDetail entity = getTestClientDetail();
    // When
    ClientSummary result = mapper.mapToClientSummary(entity);
    // Then
    // Then
    assertEquals("TS23 1JH", result.getPostalCode());
  }

  @Test
  @DisplayName("Should map multiple clients")
  void shouldMapMultipleClients(){
    // Given
    ClientDetail entity = getTestClientDetail();
    ClientDetail entityTwo = getTestClientDetail();
    Page<ClientDetail> page = new PageImpl<>(Arrays.asList(entity, entityTwo));
    // When
    ClientDetails result = mapper.mapToClientDetails(page);
    // Then
    assertEquals(2, result.getSize());
  }

  @Test
  @DisplayName("Should map pageable properties")
  void shouldMapPageableProperties(){
    // Given
    ClientDetail entity = getTestClientDetail();
    ClientDetail entityTwo = getTestClientDetail();
    Pageable pageable = Pageable.ofSize(2).withPage(5);
    Page<ClientDetail> page = new PageImpl<>(Arrays.asList(entity, entityTwo), pageable, 20);
    // When
    ClientDetails result = mapper.mapToClientDetails(page);
    // Then
    assertEquals(2, result.getSize());
    assertEquals(5, result.getNumber());
    assertEquals(20, result.getTotalElements());
    assertEquals(10, result.getTotalPages());
  }

  private static ClientDetail getTestClientDetail() {
    return ClientDetail.builder()
        .clientReferenceNumber(123L)
        .title("Title")
        .firstName("First")
        .surname("Surname")
        .surnameAtBirth("Surname At Birth")
        .dateOfBirth(LocalDate.of(2000, 1, 1))
        .gender("Gender")
        .countryOfOrigin("Country")
        .maritalStatus("Marital Status")
        .correspondenceMethod("Method")
        .disabilityType("Disability Type")
        .correspondenceLanguage("Language")
        .ethnicMonitoring("Monitoring")
        .noFixAbode("false")
        .nationalInsuranceNumber("NINO")
        .homeOfficeNumber("Home Office Number")
        // TODO Add address again once address has been added
        //.address("<Address><AddressID>52731</AddressID><House>26</House><AddressLine1>26, Grange Avenue</AddressLine1><City>BILLINGHAM</City><Country>GBR</Country><PostalCode>TS23 1JH</PostalCode></Address>")
        .build();
  }
}
