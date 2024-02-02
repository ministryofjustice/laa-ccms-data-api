package uk.gov.laa.ccms.data.repository;

import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.ProceedingClientInvolvementType;
import uk.gov.laa.ccms.data.entity.ProceedingClientInvolvementTypeId;

/**
 * This is a Spring repository for ProceedingClientInvolvementType entity operations. It extends
 * ReadOnlyRepository interface for providing basic read-only operations on
 * ProceedingClientInvolvementType entities. The primary key for ProceedingClientInvolvementType
 * entity is ProceedingClientInvolvementTypeId. It is annotated with @Repository, which makes it a
 * part of the Spring framework's persistence layer.
 *
 * @Repository allows for exception translation into Spring's DataAccessException hierarchy.
 */
@Repository
public interface ProceedingClientInvolvementTypeRepository extends
    ReadOnlyRepository<ProceedingClientInvolvementType, ProceedingClientInvolvementTypeId> {
}
