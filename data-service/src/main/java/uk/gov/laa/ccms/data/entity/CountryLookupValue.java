package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a lookup value for countries.
 *
 * <p>This entity corresponds to the "XXCCMS_COUNTRY_TYPES_V" table in the database.
 * The JSON representation of this entity uses the snake case naming strategy.</p>
 *
 * <p>It's an immutable entity, meaning that its state cannot be changed once it's created.</p>
 *
 * @see com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_COUNTRY_V", schema = "XXCCMS")
@Immutable
@IdClass(CountryLookupValueId.class)
public class CountryLookupValue {

  @Id
  @Column(name = "CODE")
  private String code;

  @Id
  @Column(name = "DESCRIPTION")
  private String description;

}
