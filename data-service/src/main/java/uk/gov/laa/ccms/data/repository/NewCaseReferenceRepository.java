package uk.gov.laa.ccms.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.CaseReference;

/**
 * <p>This interface defines a repository for managing CaseReference entities in a read-only manner.
 *     It extends {@link ReadOnlyRepository} with {@link CaseReference} as the entity type
 *     and String as the primary key type.</p>
 *
 * <p>The NewCaseReferenceRepository provides a custom
 *     query method to retrieve the next case reference.</p>
 *
 * @author Jamie Briggs
 * @see CaseReference
 */
@Repository
public interface NewCaseReferenceRepository
    extends ReadOnlyRepository<CaseReference, String> {

  /**
   * Retrieves the next case reference from the {@link CaseReference} entity.
   *
   * @return the next case reference as a {@link String}
   */
  @Query("SELECT nextRef.caseReference FROM CaseReference nextRef")
  String getNextCaseReference();
}
