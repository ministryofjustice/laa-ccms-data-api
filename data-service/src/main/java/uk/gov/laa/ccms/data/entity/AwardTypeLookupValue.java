package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


/**
 * Represents an award type lookup value entity.
 *
 * <p>This entity corresponds to the "XXCCMS_AWARD_TYPE_V" table in the database and is used for
 * award type lookup values. Each record is uniquely identified by its code.</p>
 *
 * <p>This entity is immutable, meaning its state cannot be changed once it's created.</p>
 *
 * @see PropertyNamingStrategies.SnakeCaseStrategy
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_AWARD_TYPE_V", schema = "XXCCMS")
@Immutable
public class AwardTypeLookupValue implements Serializable {
  @Id
  @Column(name = "CODE")
  private String code;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "START_DATE_ACTIVE")
  private LocalDateTime startDateActive;

  @Column(name = "END_DATE_ACTIVE")
  private LocalDateTime endDateActive;

  @Column(name = "AWARD_TYPE")
  private String awardType;

  @Column(name = "ENABLED_FLAG")
  private Boolean enabled;
}

