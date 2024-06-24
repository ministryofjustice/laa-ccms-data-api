package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;

/**
 * Represents the composite primary key for the AssessmentSummaryEntity entity.
 */
@Data
@Immutable
public class AssessmentSummaryEntityId implements Serializable {

  @Column(name = "OPA_ENTITY_NAME")
  private String opaEntityName;

  @Column(name = "OPA_ENTITY_DISPLAY_NAME")
  private String opaEntityDisplayName;

  @Column(name = "ENTITY_LEVEL")
  private Integer entityLevel;
}

