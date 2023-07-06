package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CommonValue;
import uk.gov.laa.ccms.data.entity.CommonValueId;

@Repository
public interface CommonValueRepository extends ReadOnlyRepository<CommonValue, CommonValueId> {

}