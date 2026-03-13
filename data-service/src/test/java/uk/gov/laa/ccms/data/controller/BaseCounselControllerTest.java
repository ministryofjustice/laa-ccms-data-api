package uk.gov.laa.ccms.data.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  protected static int RECORDS_NOT_FOUND = 0;
  protected static String ALL_PARAMS_EMPTY =
      "Invalid request. Please input at least one parameter for your search criteria.";
  protected static String TOO_MANY_RESULTS =
      "Too many results. Please refine your search criteria.";

  static CounselLookupDetail getAllCounselLookupValues() {
    CounselLookupValue counselLookupValue = new CounselLookupValue();
    counselLookupValue.setCounselId("18392");
    counselLookupValue.setName("SHAUN S DODDS");
    counselLookupValue.setCompany("SHAUN S DODDS");
    counselLookupValue.setLegalAidSupplierNumber("1099V");
    counselLookupValue.setCategory("Junior");
    counselLookupValue.setOutwardPostCode("LS11 ");
    counselLookupValue.setCounty(null);

    List<CounselLookupValue> counselLookupValueList = List.of(counselLookupValue);

    Pageable pageable = PageRequest.of(0, 10, Sort.by("name").descending());

    Page<@NotNull CounselLookupValue> counselLookupValues =
        new PageImpl<>(counselLookupValueList, pageable, counselLookupValueList.size());

    LookupMapper lookupMapper = new LookupMapperImpl();

    return lookupMapper.toCounselLookupDetail(counselLookupValues);
  }

  protected static JsonNode getJson(String jsonString) throws JsonProcessingException {
    return OBJECT_MAPPER.readTree(jsonString);
  }

  protected static <T> T getRealObject(String jsonString, Class<T> classType)
      throws JsonProcessingException {
    return OBJECT_MAPPER.readValue(jsonString, classType);
  }

  protected static String getJsonString(Object object) throws JsonProcessingException {
    return OBJECT_MAPPER.writeValueAsString(object);
  }
}
