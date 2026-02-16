package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.Immutable;

/**
 * Represents an assessment summary entity with various display properties.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_PUI_OPA_ENTITIES_V", schema = "XXCCMS")
@Immutable
@IdClass(AssessmentSummaryEntityId.class)
public class AssessmentSummaryEntity {

  @Id
  @Column(name = "OPA_ENTITY_NAME")
  private String opaEntityName;

  @Id
  @Column(name = "OPA_ENTITY_DISPLAY_NAME")
  private String opaEntityDisplayName;

  @Id
  @Column(name = "ENTITY_LEVEL")
  private Integer entityLevel;

  @Column(name = "RELATIONSHIP_TEXT")
  private String relationshipText;

  @Column(name = "REV_RELATIONSHIP_TEXT")
  private String revRelationshipText;

  @Column(name = "INSTANCE_NAME_COLUMN")
  private String instanceNameColumn;

  @Column(name = "PRIMARY_KEY")
  private String primaryKey;

  @Column(name = "PARENT_ENTITY_CODE")
  private String parentEntityCode;

  @Column(name = "ENTITY_DISPLAY_SEQUENCE")
  private String entityDisplaySequence;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "entity", fetch = FetchType.EAGER)
  @SQLRestriction("SUMMARY_DISPLAY_FLAG = 'Y' ")
  @OrderBy("displaySequence")
  private List<AssessmentSummaryAttribute> attributes;

}
