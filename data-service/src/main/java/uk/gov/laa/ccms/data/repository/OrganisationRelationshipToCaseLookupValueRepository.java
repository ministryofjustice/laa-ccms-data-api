package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.OrganisationRelationshipToCaseLookupValue;

/**
 * This is a Spring repository for OrganisationRelationshipToCase entity operations. It extends
 * ReadOnlyRepository interface for providing basic read-only operations on
 * OrganisationRelationshipToCase entities.
 */
@Repository
public interface OrganisationRelationshipToCaseLookupValueRepository
    extends ReadOnlyRepository<OrganisationRelationshipToCaseLookupValue, String> {}
