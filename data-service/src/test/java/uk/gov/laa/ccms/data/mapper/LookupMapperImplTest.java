package uk.gov.laa.ccms.data.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.model.CommonLookupValueDetail;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupValueDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LookupMapperImplTest {

    LookupMapperImpl mapper = new LookupMapperImpl();

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

    private CaseStatusLookupValue createCaseStatusLookupValue(String suffix, Boolean copyAllowed) {
        CaseStatusLookupValue caseStatusLookupValue = new CaseStatusLookupValue();
        caseStatusLookupValue.setCode("code" + suffix);
        caseStatusLookupValue.setDescription("description" + suffix);
        caseStatusLookupValue.setCopyAllowed(copyAllowed);
        return caseStatusLookupValue;
    }

    private CaseStatusLookupValueDetail createCaseStatusLookupValueDetail(CaseStatusLookupValue caseStatusLookupValue) {
        CaseStatusLookupValueDetail detail = new CaseStatusLookupValueDetail();
        detail.setCode(caseStatusLookupValue.getCode());
        detail.setDescription(caseStatusLookupValue.getDescription());
        detail.setCopyAllowed(caseStatusLookupValue.getCopyAllowed());
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

    @Test
    void toCaseStatusLookupValueListDetails() {
        List<CaseStatusLookupValue> caseStatusLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            caseStatusLookupValues.add(createCaseStatusLookupValue(String.valueOf(i), Boolean.TRUE));
        }

        Page<CaseStatusLookupValue> caseStatusLookupValuePage = new PageImpl<>(caseStatusLookupValues);

        CaseStatusLookupDetail expected = new CaseStatusLookupDetail();
        expected.setTotalPages(caseStatusLookupValuePage.getTotalPages());
        expected.setTotalElements((int) caseStatusLookupValuePage.getTotalElements());
        expected.setNumber(caseStatusLookupValuePage.getNumber());
        expected.setSize(caseStatusLookupValuePage.getSize());
        expected.setContent(mapper.caseStatusLookupValueListToCaseStatusLookupValueDetailList(
            (caseStatusLookupValuePage.getContent())));

        CaseStatusLookupDetail actual = mapper.toCaseStatusLookupDetail(caseStatusLookupValuePage);

        assertEquals(expected, actual);
    }

    @Test
    void caseStatusLookupValueToCaseStatusLookupValueDetails() {
        CaseStatusLookupValue caseStatusLookupValue = createCaseStatusLookupValue("", Boolean.FALSE);
        CaseStatusLookupValueDetail expectedDetails = createCaseStatusLookupValueDetail(caseStatusLookupValue);
        CaseStatusLookupValueDetail actualDetails =
            mapper.caseStatusLookupValueToCaseStatusLookupValueDetail(caseStatusLookupValue);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void caseStatusLookupValueListToCaseStatusLookupValueDetailsList() {
        List<CaseStatusLookupValue> caseStatusLookupValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            caseStatusLookupValues.add(createCaseStatusLookupValue(String.valueOf(i), Boolean.TRUE));
        }

        List<CaseStatusLookupValueDetail> expectedDetailsList = new ArrayList<>();
        for (CaseStatusLookupValue caseStatusLookupValue : caseStatusLookupValues) {
            expectedDetailsList.add(createCaseStatusLookupValueDetail(caseStatusLookupValue));
        }

        List<CaseStatusLookupValueDetail> actualDetailsList =
            mapper.caseStatusLookupValueListToCaseStatusLookupValueDetailList(caseStatusLookupValues);

        assertEquals(expectedDetailsList, actualDetailsList);
    }
}