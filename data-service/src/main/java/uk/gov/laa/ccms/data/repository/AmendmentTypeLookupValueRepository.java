package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.AmendmentTypeLookupValue;

@Repository
public interface AmendmentTypeLookupValueRepository extends ReadOnlyRepository<AmendmentTypeLookupValue, String> {

}