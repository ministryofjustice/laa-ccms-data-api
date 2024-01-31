package uk.gov.laa.ccms.data.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccms.data.entity.Proceeding;


/**
 * This is a Spring repository for Proceeding entity operations. It extends ReadOnlyRepository
 * interface for providing basic read-only operations on Proceeding entities. The primary key for
 * Proceeding entity is String.It is annotated with @Repository, which makes it a part of the Spring
 * framework's persistence layer.
 *
 * @Repository allows for exception translation into Spring's DataAccessException hierarchy.
 */
@Repository
public interface ProceedingRepository extends ReadOnlyRepository<Proceeding, String> {
  @Query(value =
      "SELECT A.* FROM XXCCMS_PROCEEDING_V A , XXCCMS_LEAD_PROC_CTRL_V B WHERE "
          + "A.PROC_LAR_SCOPE = B.PERMITTED_PROCEEDING_SCOPE"
          + " AND (:categoryOfLaw is null or A.CATEGORY_OF_LAW_CODE = :categoryOfLaw)"
          + " AND (:matterType is null or A.MATTER_TYPE = :matterType)"
          + " AND (:amendmentOnly is null or A.AMENDMENT_ONLY = :amendmentOnly)"
          + " AND (:enabled is null or A.ENABLED_FLAG = :enabled)"
          + " AND (:larScopeFlag is null or B.CASE_LAR_SCOPE_FLAG = :larScopeFlag)"
          + " AND (:appOrCertType is null or B.APP_OR_CERT_TYPE = :appOrCertType)",
      countQuery =
          "SELECT COUNT(*) FROM XXCCMS_PROCEEDING_V A , XXCCMS_LEAD_PROC_CTRL_V B WHERE "
              + "A.PROC_LAR_SCOPE = B.PERMITTED_PROCEEDING_SCOPE"
              + " AND (:categoryOfLaw is null or A.CATEGORY_OF_LAW_CODE = :categoryOfLaw)"
              + " AND (:matterType is null or A.MATTER_TYPE = :matterType)"
              + " AND (:amendmentOnly is null or A.AMENDMENT_ONLY = :amendmentOnly)"
              + " AND (:enabled is null or A.ENABLED_FLAG = :enabled)"
              + " AND (:larScopeFlag is null or B.CASE_LAR_SCOPE_FLAG = :larScopeFlag)"
              + " AND (:appOrCertType is null or B.APP_OR_CERT_TYPE = :appOrCertType)",
      nativeQuery = true)
  Page<Proceeding> findAllLeadProceedings(
      @Param("categoryOfLaw") String categoryOfLaw,
      @Param("matterType") String matterType,
      @Param("amendmentOnly") String amendmentOnly,
      @Param("enabled") String enabled,
      @Param("larScopeFlag") String larScopeFlag,
      @Param("appOrCertType") String appOrCertType,
      Pageable pageable);

}
