package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.EvidenceDocumentTypeLookupValue;


/**
 * This is a Spring repository for EvidenceDocumentTypeLookupValue entity operations.
 * It extends ReadOnlyRepository interface for providing basic read-only operations on
 * EvidenceDocumentTypeLookupValue entities. The primary key for EvidenceDocumentTypeLookupValue
 * entity is CommonLookupValueId.
 * It is annotated with @Repository, which makes it a part of the Spring framework's
 * persistence layer.
 *
 * @Repository allows for exception translation into Spring's DataAccessException hierarchy.
 */
@Repository
public interface EvidenceDocumentTypeLookupValueRepository
    extends ReadOnlyRepository<EvidenceDocumentTypeLookupValue, EvidenceDocumentTypeLookupValue> {

}
