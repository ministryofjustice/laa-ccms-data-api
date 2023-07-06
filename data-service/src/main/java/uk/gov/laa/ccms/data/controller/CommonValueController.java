package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.CommonValuesApi;
import uk.gov.laa.ccms.data.entity.CommonValue;
import uk.gov.laa.ccms.data.model.CommonValueListDetails;
import uk.gov.laa.ccms.data.service.CommonValueService;

/**
 * REST controller for managing common values.
 */
@RestController
@RequiredArgsConstructor
public class CommonValueController implements CommonValuesApi {

    private final CommonValueService commonValueService;
    private final ModelMapper modelMapper;

    /**
     * GET common values by type and code.
     *
     * @param type     the type of the common values
     * @param code     the code of the common values
     * @param pageable pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of common values in the body
     */
    @Override
    public ResponseEntity<CommonValueListDetails> getCommonValues(String type, String code, Pageable pageable) {

        CommonValue example = new CommonValue();
        example.setType(type);
        example.setCode(code);

        Page<CommonValue> page = commonValueService.getCommonValues(Example.of(example), pageable);

        CommonValueListDetails response = modelMapper.map(page, CommonValueListDetails.class);

        return ResponseEntity.ok(response);
    }
}