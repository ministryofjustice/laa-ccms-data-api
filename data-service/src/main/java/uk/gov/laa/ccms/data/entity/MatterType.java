package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


/**
 * Represents a Matter type entity.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_MATTER_TYPES_V", schema = "XXCCMS")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MatterType {

  /**
   * The unique identifier for the matter type.
   */
  @Id
  @Column(name = "MATTER_TYPE")
  private String matterType;

  /**
   * The matter type's description.
   */
  @Column(name = "DESCRIPTION")
  private String description;

  /**
   * The category of law code.
   */
  @Column(name = "CATEGORY_OF_LAW_CODE")
  private String categoryOfLawCode;
}
