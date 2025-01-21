package uk.gov.laa.ccms.data.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

/**
 * Represents an assessment summary attribute entity with various display properties.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_PUI_OPA_ATTRIBUTE_V", schema = "XXCCMS")
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

  @Column(name = "ENTITY_DISPLAY_SEQUENCE")
  private Integer entityDisplaySequence;

  @Column(name = "SUMMARY_DISPLAY_FLAG")
  private Boolean summaryDisplayFlag;

  @ManyToOne
  @JoinColumn(
      name = "OPA_ENTITY_NAME",
      referencedColumnName = "OPA_ENTITY_NAME",
      insertable = false,
      updatable = false)
  @JoinColumn(
      name = "OPA_ENTITY_DISPLAY_NAME",
      referencedColumnName = "OPA_ENTITY_DISPLAY_NAME",
      insertable = false,
      updatable = false)
  private AssessmentSummaryEntity entity;

}

