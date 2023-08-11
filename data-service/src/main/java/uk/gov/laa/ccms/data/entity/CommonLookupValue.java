package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


/**
 * Represents a common lookup value entity.
 *
 * <p>This entity corresponds to the "XXCCMS_COMMON_LOV_V" table in the database and is used for
 * general-purpose lookup values. Each record is uniquely identified by its type and code, both
 * serving as composite primary keys.</p>
 *
 * <p>This entity is immutable, meaning its state cannot be changed once it's created.</p>
 *
 * @see PropertyNamingStrategies.SnakeCaseStrategy
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_COMMON_LOV_V")
@Immutable
@IdClass(CommonLookupValueId.class)
public class CommonLookupValue implements Serializable {

  @Id
  @Column(name = "LOV_TYPE")
  private String type;

  @Id
  @Column(name = "CODE")
  private String code;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "START_DATE_ACTIVE")
  private LocalDateTime startDateActive;

  @Column(name = "ATTRIBUTE11")
  private String attribute11;

  @Column(name = "ATTRIBUTE12")
  private String attribute12;

  @Column(name = "ENABLED_FLAG")
  private String enabled;

  @Column(name = "DEFAULT_CODE")
  private String defaultCode;

}

