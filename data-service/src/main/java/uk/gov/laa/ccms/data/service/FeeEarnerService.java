package uk.gov.laa.ccms.data.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.repository.FeeEarnerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeeEarnerService {

    private final FeeEarnerRepository feeEarnerRepository;

    public Page<FeeEarner> getFeeEarners(Example<FeeEarner> example, Pageable pageable){
        return feeEarnerRepository.findAll(example, pageable);
    }

}
