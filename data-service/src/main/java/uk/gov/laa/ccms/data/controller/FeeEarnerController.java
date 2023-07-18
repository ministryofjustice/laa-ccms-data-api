package uk.gov.laa.ccms.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccms.data.api.FeeEarnersApi;
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.mapper.FeeEarnerMapper;
import uk.gov.laa.ccms.data.model.FeeEarnerDetail;
import uk.gov.laa.ccms.data.service.FeeEarnerService;

/**
 * REST controller for managing common values.
 */
@RestController
@RequiredArgsConstructor
public class FeeEarnerController implements FeeEarnersApi {

    private final FeeEarnerService feeEarnerService;
    private final FeeEarnerMapper feeEarnerMapper;

    /**
     * GET fee earners by providerFirmId.
     *
     * @param providerFirmId     the id of the FeeEarner's related ProviderFirm
     * @param pageable pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fee earners in the body
     */
    @Override
    public ResponseEntity<FeeEarnerDetail> getFeeEarners(Integer providerFirmId, Pageable pageable) {
        FeeEarner example = new FeeEarner();
        example.setProvider(new Provider());
        example.getProvider().setId(providerFirmId);

        Page<FeeEarner> page = feeEarnerService.getFeeEarners(Example.of(example), pageable);

        return ResponseEntity.ok(feeEarnerMapper.toFeeEarnerDetail(page));
    }
}