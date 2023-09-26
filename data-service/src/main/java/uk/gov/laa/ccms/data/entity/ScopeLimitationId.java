package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

/**
 * Class to represent the compound primary key for the ScopeLimitation entity.
 */
@Embeddable
@Data
public class ScopeLimitationId implements Serializable {
  @Column(name = "SCOPELIMITATIONS")
  private String scopeLimitations;

  @Column(name = "CATEGORY_OF_LAW_CODE")
  private String categoryOfLawCode;

  @Column(name = "MATTER_TYPE")
  private String matterType;

  @Column(name = "PROCEEDING_CODE")
  private String proceedingCode;

  @Column(name = "LEVEL_OF_SERVICE_CODE")
  private String levelOfServiceCode;
}