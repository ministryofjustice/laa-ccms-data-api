package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a Stage End entity from the "XXCCMS_STAGE_END_V" database table.
 *
 * <p>This entity is immutable, meaning its state cannot be changed once it's created.</p>
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_STAGE_END_V", schema = "XXCCMS")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StageEndLookupValue {

  /**
   * The compound id for the stage end.
   */
  @EmbeddedId
  private StageEndLookupValueId id;

  /**
   * The description of the stage end.
   */
  @Column(name = "STAGE_END_DESCRIPTION")
  private String description;

  /**
   * The lov for the stage end.
   */
  @Column(name = "STAGE_END_LOV")
  private String stageEndLov;

  /**
   * The enabled flag for the stage end.
   */
  @Column(name = "ENABLED_FLAG")
  private Boolean enabled;
}