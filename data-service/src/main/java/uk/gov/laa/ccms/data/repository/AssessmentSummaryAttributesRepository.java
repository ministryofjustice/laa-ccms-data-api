package uk.gov.laa.ccms.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.AssessmentSummaryEntity;
import uk.gov.laa.ccms.data.entity.AssessmentSummaryEntityId;

/**
 * Repository interface for accessing assessment summary attributes.
 */
@Repository
public interface AssessmentSummaryAttributesRepository
    extends ReadOnlyRepository<AssessmentSummaryEntity, AssessmentSummaryEntityId>,
    JpaSpecificationExecutor<AssessmentSummaryEntity> {

}
