package uk.gov.laa.ccms.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uk.gov.laa.ccms.data.entity.AssessmentSummaryAttribute;
import uk.gov.laa.ccms.data.entity.AssessmentSummaryAttributeId;

/**
 * Repository interface for accessing assessment summary attributes.
 */
public interface AssessmentSummaryAttributesRepository
    extends ReadOnlyRepository<AssessmentSummaryAttribute, AssessmentSummaryAttributeId> {

  /**
   * Finds all summary attributes based on the summary type and pagination details.
   *
   * @param summaryType the condition to filter by entity level
   * @param pageable    the pagination information
   * @return a paginated list of assessment summary attributes
   */
  @Query(
      value = "SELECT "
          + "  A.OPA_ENTITY_NAME, "
          + "  A.OPA_ENTITY_DISPLAY_NAME, "
          + "  A.OPA_ATTRIBUTE_NAME, "
          + "  A.OPA_ATTRIBUTE_DISPLAY_NAME, "
          + "  A.DISPLAY_SEQUENCE, "
          + "  A.SUMMARY_DISPLAY_FLAG, "
          + "  E.ENTITY_LEVEL, "
          + "  E.ENTITY_DISPLAY_SEQUENCE "
          + "FROM "
          + "  XXCCMS_PUI_OPA_ATTRIBUTE_V A, "
          + "  XXCCMS_PUI_OPA_ENTITIES_V E "
          + "WHERE "
          + "  A.OPA_ENTITY_NAME = E.OPA_ENTITY_NAME "
          + "  AND A.SUMMARY_DISPLAY_FLAG = 'Y' "
          + "  AND (:entityLevelCondition IS NULL "
          + "       OR (:entityLevelCondition = 'PARENT' AND E.ENTITY_LEVEL < '2') "
          + "       OR (:entityLevelCondition = 'CHILD' AND E.ENTITY_LEVEL >= '2')) "
          + "ORDER BY "
          + "  E.ENTITY_DISPLAY_SEQUENCE, "
          + "  A.DISPLAY_SEQUENCE",
      countQuery = "SELECT COUNT(*) "
          + "FROM "
          + "  XXCCMS_PUI_OPA_ATTRIBUTE_V A, "
          + "  XXCCMS_PUI_OPA_ENTITIES_V E "
          + "WHERE "
          + "  A.OPA_ENTITY_NAME = E.OPA_ENTITY_NAME "
          + "  AND A.SUMMARY_DISPLAY_FLAG = 'Y' "
          + "  AND (:entityLevelCondition IS NULL "
          + "       OR (:entityLevelCondition = 'PARENT' AND E.ENTITY_LEVEL < '2') "
          + "       OR (:entityLevelCondition = 'CHILD' AND E.ENTITY_LEVEL >= '2'))",
      nativeQuery = true
  )
  Page<AssessmentSummaryAttribute> findAllSummaryAttributes(
      @Param("entityLevelCondition") String summaryType,
      Pageable pageable
  );


}
