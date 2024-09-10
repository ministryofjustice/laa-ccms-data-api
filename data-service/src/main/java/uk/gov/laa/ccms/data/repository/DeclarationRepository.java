package uk.gov.laa.ccms.data.repository;

import uk.gov.laa.ccms.data.entity.Declaration;
import uk.gov.laa.ccms.data.entity.DeclarationId;

/**
 * Repository interface for performing read-only operations on Declaration entities.
 */
public interface DeclarationRepository extends ReadOnlyRepository<Declaration, DeclarationId> {
}
