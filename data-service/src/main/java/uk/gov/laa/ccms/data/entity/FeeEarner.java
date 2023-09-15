package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a Fee Earner entity within the system.
 *
 * <p>This entity corresponds to a join between "XXCCMS_FEE_EARNERS_V"
 * and "XXCCMS_FEE_EARNER_OFFICES_V" table in the database. It captures
 * details of individuals or entities that earn fees for the services they provide, such as legal
 * professionals.</p>
 *
 * <p>The JSON representation of this entity uses the snake case naming strategy. A Fee Earner is
 * uniquely identified by its ID, and it also contains a reference to the {@link Provider}
 * entity.</p>
 *
 * <p>This entity is marked as immutable, meaning its state cannot be changed once it's created.</p>
 *
 * @see PropertyNamingStrategies.SnakeCaseStrategy
 * @see Provider
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_FEE_EARNER_OFFICES_V")
@SecondaryTable(name = "XXCCMS_FEE_EARNERS_V")
@Immutable
public class FeeEarner implements Serializable {
  /**
   * The unique identifier for the FeeEarner.
   */
  @Id
  @Column(name = "CONTACT_ID")
  private Integer id;

  /**
   * The name for the Fee Earner.
   */
  @Column(name = "CONTACT_NAME", table = "XXCCMS_FEE_EARNERS_V")
  private String name;

  /**
   * The Fee Earner's related Office.
   */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID")
  private Office office;
}
