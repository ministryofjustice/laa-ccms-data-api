package uk.gov.laa.ccms.data.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;
import uk.gov.laa.ccms.data.entity.CaseStatusLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.repository.AmendmentTypeLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CaseStatusLookupValueRepository;
import uk.gov.laa.ccms.data.repository.CommonLookupValueRepository;

/**
 * Service class for managing common values.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LookupService {

    private final CommonLookupValueRepository commonLookupValueRepository;

    private final CaseStatusLookupValueRepository caseStatusLookupValueRepository;

    private final AmendmentTypeLookupValueRepository amendmentTypeLookupValueRepository;

    /**
     * Retrieves a page of common values based on the provided example and pagination information.
     *
     * @param example  the example of the common values
     * @param pageable pagination information
     * @return a page of common values
     */
    public Page<CommonLookupValue> getCommonLookupValues(Example<CommonLookupValue> example, Pageable pageable) {
        return commonLookupValueRepository.findAll(example, pageable);
    }

    /**
     * Retrieves a page of case status values based on the provided example and pagination information.
     *
     * @param example  the example of the common values
     * @param pageable pagination information
     * @return a page of case status values
     */
    public Page<CaseStatusLookupValue> getCaseStatusLookupValues(Example<CaseStatusLookupValue> example, Pageable pageable){
        return caseStatusLookupValueRepository.findAll(example, pageable);
    }

    /**
     * Retrieves a page of amendment type values based on the provided example and pagination information.
     *
     * @param example  the example of the amendment type values
     * @param pageable pagination information
     * @return a page of amendment type values
     */
    public Page<AmendmentTypeLookupValue> getAmendmentTypeLookupValues(Example<AmendmentTypeLookupValue> example, Pageable pageable){
        return amendmentTypeLookupValueRepository.findAll(example, pageable);
    }

}
