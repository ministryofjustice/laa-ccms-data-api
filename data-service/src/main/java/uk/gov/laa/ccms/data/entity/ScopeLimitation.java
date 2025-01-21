package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a Scope Limitation entity within the system.
 *
 * <p>This entity corresponds to a row from the "XXCCMS_SCOPELIMITATIONS_V" table in the database.
 * </p>
 *
 * <p>The JSON representation of this entity uses the snake case naming strategy.
 * A Scope Limitation is uniquely identified by its compound ID.</p>
 *
 * <p>This entity is marked as immutable, meaning its state cannot be changed once it's created.</p>
 *
 * @see PropertyNamingStrategies.SnakeCaseStrategy
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_SCOPELIMITATIONS_V", schema = "XXCCMS")
@Immutable
public class ScopeLimitation implements Serializable {

  @EmbeddedId
  private ScopeLimitationId id;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "DEFAULT_WORDING")
  private String defaultWording;

  @Column(name = "STAGE")
  private Integer stage;

  @Column(name = "COST_LIMITATION")
  private BigDecimal costLimitation;

  @Column(name = "EMERGENCY_COST_LIMITATION")
  private BigDecimal emergencyCostLimitation;

  @Column(name = "NON_STD_WORDING_REQD")
  private Boolean nonStandardWordingRequired;

  @Column(name = "EMERGENCY_SCOPE_DEFAULT")
  private Boolean emergencyScopeDefault;

  @Column(name = "EMERGENCY")
  private Boolean emergency;

  @Column(name = "DEFAULT_CODE")
  private Boolean defaultCode;

  @Column(name = "SCOPE_DEFAULT")
  private Boolean scopeDefault;

}

