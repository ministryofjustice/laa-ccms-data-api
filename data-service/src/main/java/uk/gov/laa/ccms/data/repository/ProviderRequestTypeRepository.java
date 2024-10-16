package uk.gov.laa.ccms.data.repository;

import uk.gov.laa.ccms.data.entity.ProviderRequestType;

/**
 * Repository interface for accessing {@link ProviderRequestType} entities.
 * Extends {@link ReadOnlyRepository} for read-only operations.
 */

public interface ProviderRequestTypeRepository
    extends ReadOnlyRepository<ProviderRequestType, String> {

}
