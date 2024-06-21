package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

/**
 * Represents an assessment summary attribute entity with various display properties.
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_PUI_OPA_ATTRIBUTE_V")
@Immutable
@IdClass(AssessmentSummaryAttributeId.class)
public class AssessmentSummaryAttribute implements Serializable {

  @Id
  @Column(name = "OPA_ENTITY_NAME")
  private String opaEntityName;

  @Id
  @Column(name = "OPA_ENTITY_DISPLAY_NAME")
  private String opaEntityDisplayName;

  @Id
  @Column(name = "OPA_ATTRIBUTE_NAME")
  private String opaAttributeName;

  @Id
  @Column(name = "OPA_ATTRIBUTE_DISPLAY_NAME")
  private String opaAttributeDisplayName;

  @Column(name = "DISPLAY_SEQUENCE")
  private Integer displaySequence;

  @Column(name = "SUMMARY_DISPLAY_FLAG")
  private Boolean summaryDisplayFlag;

  @JoinColumn(name = "ENTITY_LEVEL", table = "XXCCMS_PUI_OPA_ENTITIES_V")
  private Integer entityLevel;

  @JoinColumn(name = "ENTITY_DISPLAY_SEQUENCE", table = "XXCCMS_PUI_OPA_ENTITIES_V")
  private Integer entityDisplaySequence;




}

