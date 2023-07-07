package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CommonLookupValue;
import uk.gov.laa.ccms.data.entity.CommonLookupValueId;

@Repository
public interface CommonLookupValueRepository extends ReadOnlyRepository<CommonLookupValue, CommonLookupValueId> {

}