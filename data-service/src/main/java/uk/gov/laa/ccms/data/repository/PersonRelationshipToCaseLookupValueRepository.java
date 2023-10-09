package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.PersonRelationshipToCaseLookupValue;

/**
 * This is a Spring repository for PersonRelationshipToCase entity operations. It extends
 * ReadOnlyRepository interface for providing basic read-only operations on PersonRelationshipToCase
 * entities.
 */
@Repository
public interface PersonRelationshipToCaseLookupValueRepository extends
    ReadOnlyRepository<PersonRelationshipToCaseLookupValue, String> {

}
