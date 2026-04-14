package uk.gov.laa.ccms.data.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.gov.laa.ccms.data.entity.CounselLookupValue;
import uk.gov.laa.ccms.data.mapper.LookupMapper;
import uk.gov.laa.ccms.data.mapper.LookupMapperImpl;
import uk.gov.laa.ccms.data.model.CounselLookupDetail;

/**
 * Base class for CounselControllerTest class and contains basic test supporting member variables
 * and methods.
 */
public sealed class BaseCounselControllerTest permits CounselControllerTest {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private static final LookupMapper lookupMapper = new LookupMapperImpl();

  protected static String ALL_PARAMS_EMPTY =
      "Invalid request. Please input at least one parameter for your search criteria.";
  protected static String TOO_MANY_RESULTS =
      "Too many results. Please refine your search criteria.";

  protected static CounselLookupDetail getCounselLookupDetail(List<CounselLookupValue> values) {

    Page<CounselLookupValue> counselLookupValuesPage =
        new PageImpl<>(values, Pageable.ofSize(10).withPage(0), values.size());

    return lookupMapper.toCounselLookupDetail(counselLookupValuesPage);
  }

  protected static List<CounselLookupValue> getAllCounselLookupValues() {
    CounselLookupValue counselLookupValue = new CounselLookupValue();
    counselLookupValue.setCounselId("18392");
    counselLookupValue.setName("SHAUN S DODDS");
    counselLookupValue.setCompany("SHAUN S DODDS");
    counselLookupValue.setLegalAidSupplierNumber("1099V");
    counselLookupValue.setCategory("Junior");
    counselLookupValue.setOutwardPostCode("LS11 ");
    counselLookupValue.setCounty(null);

    return List.of(counselLookupValue);
  }

  protected static List<CounselLookupValue> get501CounselLookupValues() {
    CounselLookupValue counselLookupValue = new CounselLookupValue();
    counselLookupValue.setCounselId("18392");
    counselLookupValue.setName("SHAUN S DODDS");
    counselLookupValue.setCompany("SHAUN S DODDS");
    counselLookupValue.setLegalAidSupplierNumber("1099V");
    counselLookupValue.setCategory("Junior");
    counselLookupValue.setOutwardPostCode("LS11 ");
    counselLookupValue.setCounty(null);

    return Collections.nCopies(505, counselLookupValue);
  }

  protected static String getJsonString(Object object) throws JsonProcessingException {
    return OBJECT_MAPPER.writeValueAsString(object);
  }
}
