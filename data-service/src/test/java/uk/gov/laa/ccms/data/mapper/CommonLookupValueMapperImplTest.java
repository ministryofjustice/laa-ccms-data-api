package uk.gov.laa.ccms.data.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.model.CommonLookupValueDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CommonLookupValueMapperImplTest {

    CommonLookupValueMapperImpl mapper = new CommonLookupValueMapperImpl();

    // Helper methods to create objects
    private CommonLookupValue createCommonLookupValue(String suffix) {
        CommonLookupValue commonLookupValue = new CommonLookupValue();
        commonLookupValue.setType("type" + suffix);
        commonLookupValue.setCode("code" + suffix);
        commonLookupValue.setDescription("description" + suffix);
        commonLookupValue.setStartDateActive(LocalDateTime.now());
        commonLookupValue.setAttribute11("attribute11" + suffix);
        commonLookupValue.setAttribute12("attribute12" + suffix);
        commonLookupValue.setEnabled("true");
        commonLookupValue.setDefaultCode("defaultCode" + suffix);
        return commonLookupValue;
    }

    private CommonLookupValueDetails createCommonLookupValueDetails(CommonLookupValue commonLookupValue) {
        CommonLookupValueDetails details = new CommonLookupValueDetails();
        details.setType(commonLookupValue.getType());
        details.setCode(commonLookupValue.getCode());
        details.setDescription(commonLookupValue.getDescription());
        details.setStartDateActive(commonLookupValue.getStartDateActive().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        details.setAttribute11(commonLookupValue.getAttribute11());
        details.setAttribute12(commonLookupValue.getAttribute12());
        details.setEnabled(Boolean.parseBoolean(commonLookupValue.getEnabled()));
        details.setDefaultCode(commonLookupValue.getDefaultCode());
        return details;
    }

    // Tests

    @Test
    void toCommonLookupValueListDetails() {
        List<CommonLookupValue> commonLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            commonLookupValues.add(createCommonLookupValue(String.valueOf(i)));
        }

        Page<CommonLookupValue> commonLookupValuePage = new PageImpl<>(commonLookupValues);

        uk.gov.laa.ccms.data.model.CommonLookupValueListDetails expected = new uk.gov.laa.ccms.data.model.CommonLookupValueListDetails();
        expected.setTotalPages(commonLookupValuePage.getTotalPages());
        expected.setTotalElements((int) commonLookupValuePage.getTotalElements());
        expected.setNumber(commonLookupValuePage.getNumber());
        expected.setSize(commonLookupValuePage.getSize());
        expected.setContent(mapper.commonLookupValueListToCommonLookupValueDetailsList(commonLookupValuePage.getContent()));

        uk.gov.laa.ccms.data.model.CommonLookupValueListDetails actual = mapper.toCommonLookupValueListDetails(commonLookupValuePage);

        assertEquals(expected, actual);
    }

    @Test
    void commonLookupValueToCommonLookupValueDetails() {
        CommonLookupValue commonLookupValue = createCommonLookupValue("");
        CommonLookupValueDetails expectedDetails = createCommonLookupValueDetails(commonLookupValue);
        CommonLookupValueDetails actualDetails = mapper.commonLookupValueToCommonLookupValueDetails(commonLookupValue);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void commonLookupValueListToCommonLookupValueDetailsList() {
        List<CommonLookupValue> commonLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            commonLookupValues.add(createCommonLookupValue(String.valueOf(i)));
        }

        List<CommonLookupValueDetails> expectedDetailsList = new ArrayList<>();
        for (CommonLookupValue commonLookupValue : commonLookupValues) {
            expectedDetailsList.add(createCommonLookupValueDetails(commonLookupValue));
        }

        List<CommonLookupValueDetails> actualDetailsList = mapper.commonLookupValueListToCommonLookupValueDetailsList(commonLookupValues);

        assertEquals(expectedDetailsList, actualDetailsList);
    }
}