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
 * Represents a Proceeding Outcome Result entity from the "XXCCMS_OUTCOME_RESULTS_V" database table.
 *
 * <p>This entity is immutable, meaning its state cannot be changed once it's created.</p>
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_OUTCOME_RESULTS_V")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OutcomeResultLookupValue {

  /**
   * The compound id for the outcome result.
   */
  @EmbeddedId
  private OutcomeResultLookupValueId id;

  /**
   * The description of the outcome result.
   */
  @Column(name = "OUTCOME_RESULT_DESCRIPTION")
  private String outcomeResultDescription;

  /**
   * The name of the proceeding.
   */
  @Column(name = "OUTCOME_RESULT_LOV")
  private String outcomeResultLov;


  /**
   * The stage end lov value for the proceeding.
   */
  @Column(name = "ENABLED_FLAG")
  private Boolean enabled;
}