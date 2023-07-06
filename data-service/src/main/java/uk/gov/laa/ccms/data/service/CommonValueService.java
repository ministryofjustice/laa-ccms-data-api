package uk.gov.laa.ccms.data.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.CommonValue;
import uk.gov.laa.ccms.data.repository.CommonValueRepository;

/**
 * Service class for managing common values.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommonValueService {

    private final CommonValueRepository commonValueRepository;

    /**
     * Retrieves a page of common values based on the provided example and pagination information.
     *
     * @param example  the example of the common values
     * @param pageable pagination information
     * @return a page of common values
     */
    public Page<CommonValue> getCommonValues(Example<CommonValue> example, Pageable pageable) {

        Page<CommonValue> page = commonValueRepository.findAll(example, pageable);

        return page;
    }

}
