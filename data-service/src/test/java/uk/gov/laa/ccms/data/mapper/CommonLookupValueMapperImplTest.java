package uk.gov.laa.ccms.data.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.model.CommonLookupValueDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;

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

    private CommonLookupValueDetail createCommonLookupValueDetails(CommonLookupValue commonLookupValue) {
        CommonLookupValueDetail detail = new CommonLookupValueDetail();
        detail.setType(commonLookupValue.getType());
        detail.setCode(commonLookupValue.getCode());
        detail.setDescription(commonLookupValue.getDescription());
        detail.setStartDateActive(commonLookupValue.getStartDateActive().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        detail.setAttribute11(commonLookupValue.getAttribute11());
        detail.setAttribute12(commonLookupValue.getAttribute12());
        detail.setEnabled(Boolean.parseBoolean(commonLookupValue.getEnabled()));
        detail.setDefaultCode(commonLookupValue.getDefaultCode());
        return detail;
    }

    // Tests

    @Test
    void toCommonLookupValueListDetails() {
        List<CommonLookupValue> commonLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            commonLookupValues.add(createCommonLookupValue(String.valueOf(i)));
        }

        Page<CommonLookupValue> commonLookupValuePage = new PageImpl<>(commonLookupValues);


        CommonLookupDetail expected = new CommonLookupDetail();
        expected.setTotalPages(commonLookupValuePage.getTotalPages());
        expected.setTotalElements((int) commonLookupValuePage.getTotalElements());
        expected.setNumber(commonLookupValuePage.getNumber());
        expected.setSize(commonLookupValuePage.getSize());
        expected.setContent(mapper.commonLookupValueListToCommonLookupValueDetailList(commonLookupValuePage.getContent()));

        CommonLookupDetail actual = mapper.toCommonLookupDetail(commonLookupValuePage);

        assertEquals(expected, actual);
    }

    @Test
    void commonLookupValueToCommonLookupValueDetails() {
        CommonLookupValue commonLookupValue = createCommonLookupValue("");
        CommonLookupValueDetail expectedDetails = createCommonLookupValueDetails(commonLookupValue);
        CommonLookupValueDetail actualDetails = mapper.commonLookupValueToCommonLookupValueDetail(commonLookupValue);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void commonLookupValueListToCommonLookupValueDetailsList() {
        List<CommonLookupValue> commonLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            commonLookupValues.add(createCommonLookupValue(String.valueOf(i)));
        }

        List<CommonLookupValueDetail> expectedDetailsList = new ArrayList<>();
        for (CommonLookupValue commonLookupValue : commonLookupValues) {
            expectedDetailsList.add(createCommonLookupValueDetails(commonLookupValue));
        }

        List<CommonLookupValueDetail> actualDetailsList = mapper.commonLookupValueListToCommonLookupValueDetailList(commonLookupValues);

        assertEquals(expectedDetailsList, actualDetailsList);
    }
}