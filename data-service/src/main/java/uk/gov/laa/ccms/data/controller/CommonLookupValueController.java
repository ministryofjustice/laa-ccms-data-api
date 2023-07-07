package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.CommonLookupValuesApi;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.mapper.CommonLookupValueMapper;
import uk.gov.laa.ccms.data.model.CommonLookupValueListDetails;
import uk.gov.laa.ccms.data.model.CommonLookupValueDetails;
import uk.gov.laa.ccms.data.service.CommonLookupValueService;

/**
 * REST controller for managing common values.
 */
@RestController
@RequiredArgsConstructor
public class CommonLookupValueController implements CommonLookupValuesApi {

    private final CommonLookupValueService commonLookupValueService;
    private final CommonLookupValueMapper commonLookupValueMapper;

    /**
     * GET common lookup values by type and code.
     *
     * @param type     the type of the common lookup values
     * @param code     the code of the common lookup values
     * @param pageable pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of common values in the body
     */
    @Override
    public ResponseEntity<CommonLookupValueListDetails> getCommonLookupValues(String type, String code, Pageable pageable) {
        CommonLookupValue example = new CommonLookupValue();
        example.setType(type);
        example.setCode(code);

        Page<CommonLookupValue> page = commonLookupValueService.getCommonLookupValues(Example.of(example), pageable);
        CommonLookupValueListDetails response = commonLookupValueMapper.toCommonLookupValueListDetails(page);

        return ResponseEntity.ok(response);
    }
}