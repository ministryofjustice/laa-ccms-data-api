package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;

/**
 * Represents the composite primary key for the AssessmentSummaryAttribute entity.
 */
@Data
@Immutable
public class AssessmentSummaryAttributeId implements Serializable {

  @Column(name = "OPA_ENTITY_NAME")
  private String opaEntityName;

  @Column(name = "OPA_ENTITY_DISPLAY_NAME")
  private String opaEntityDisplayName;

  @Column(name = "OPA_ATTRIBUTE_NAME")
  private String opaAttributeName;

  @Column(name = "OPA_ATTRIBUTE_DISPLAY_NAME")
  private String opaAttributeDisplayName;
}

