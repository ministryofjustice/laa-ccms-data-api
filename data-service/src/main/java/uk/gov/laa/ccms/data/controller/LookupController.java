package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.CommonLookupValuesApi;
import uk.gov.laa.ccms.data.api.CaseStatusLookupValuesApi;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.mapper.LookupMapper;
import uk.gov.laa.ccms.data.model.CommonLookupDetail;
import uk.gov.laa.ccms.data.model.CaseStatusLookupDetail;
import uk.gov.laa.ccms.data.service.LookupService;

/**
 * REST controller for managing lookup values.
 */
@RestController
@RequiredArgsConstructor
public class LookupController implements CommonLookupValuesApi, CaseStatusLookupValuesApi {

    private final LookupService lookupService;
    private final LookupMapper lookupMapper;

    /**
     * GET common lookup values by type and code.
     *
     * @param type     the type of the common lookup values
     * @param code     the code of the common lookup values
     * @param pageable pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of common values in the body
     */
    @Override
    public ResponseEntity<CommonLookupDetail> getCommonLookupValues(String type, String code, Pageable pageable) {
        CommonLookupValue example = new CommonLookupValue();
        example.setType(type);
        example.setCode(code);

        Page<CommonLookupValue> page = lookupService.getCommonLookupValues(Example.of(example), pageable);
        CommonLookupDetail response = lookupMapper.toCommonLookupDetail(page);

        return ResponseEntity.ok(response);
    }

    /**
     * GET case status lookup values by code and copyAllowed flag.
     *
     * @param code          the code of the case status lookup values
     * @param copyAllowed   the value of the copyAllowed flag for the case status lookup values
     * @param pageable      pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of case status values in the body
     */
    @Override
    public ResponseEntity<CaseStatusLookupDetail> getCaseStatusLookupValues(String code, Boolean copyAllowed, Pageable pageable) {
        CaseStatusLookupValue example = new CaseStatusLookupValue();
        example.setCode(code);
        example.setCopyAllowed(copyAllowed);

        Page<CaseStatusLookupValue> page = lookupService.getCaseStatusLookupValues(Example.of(example), pageable);
        CaseStatusLookupDetail response = lookupMapper.toCaseStatusLookupDetail(page);

        return ResponseEntity.ok(response);
    }
}