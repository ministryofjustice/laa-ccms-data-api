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
 * Represents a Level Of service lookup value entity.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_LEVEL_OF_SERVICE_V")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LevelOfService {

  @EmbeddedId
  private LevelOfServiceId id;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "DEFAULT_CODE")
  private String defaultCode;
}
