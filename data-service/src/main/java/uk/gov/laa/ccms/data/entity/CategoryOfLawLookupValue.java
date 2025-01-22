package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

/**
 * Represents a lookup value for category of law.
 *
 * <p>This entity corresponds to the "XXCCMS_CATEGORY_OF_LAW_V" table in the database.
 * The JSON representation of this entity uses the snake case naming strategy.</p>
 *
 * <p>It's an immutable entity, meaning its state cannot be changed once it's created. The primary
 * attributes of this entity include a code, its corresponding matter type description, and an
 * indicator whether copying the cost limit is allowed for this particular category of law.</p>
 *
 * @see PropertyNamingStrategies.SnakeCaseStrategy
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_CATEGORY_OF_LAW_V", schema = "XXCCMS")
@Immutable
public class CategoryOfLawLookupValue {

  @Id
  @Column(name = "CATEGORY_OF_LAW_CODE")
  private String code;

  @Column(name = "MATTER_TYPE_DESCRIPTION")
  private String matterTypeDescription;

  @Column(name = "COPY_COST_LIMIT_IND")
  private Boolean copyCostLimit;
}
