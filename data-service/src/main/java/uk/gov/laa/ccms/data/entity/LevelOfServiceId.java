package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;

/**
 * Represents the composite primary key for the {@link uk.gov.laa.ccms.data.entity.LevelOfService}
 * entity.

 * @see LevelOfService
 */
@Data
@Immutable
public class LevelOfServiceId implements Serializable {

  /**
   * The category of law code.
   */
  @Column(name = "CATEGORY_OF_LAW_CODE")
  private String categoryOfLawCode;

  /**
   * The Matter type.
   */
  @Column(name = "MATTER_TYPE")
  private String matterType;

  /**
   * The proceeding code.
   */
  @Column(name = "PROCEEDING_CODE")
  private String proceedingCode;

  @Column(name = "LEVEL_OF_SERVICE_CODE")
  private String levelOfServiceCode;


}
