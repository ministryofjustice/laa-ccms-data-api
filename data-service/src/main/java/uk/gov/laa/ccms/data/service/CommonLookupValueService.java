package uk.gov.laa.ccms.data.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.repository.CommonLookupValueRepository;

/**
 * Service class for managing common values.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommonLookupValueService {

    private final CommonLookupValueRepository commonLookupValueRepository;

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

}
