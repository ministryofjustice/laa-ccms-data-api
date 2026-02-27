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
 * Represents a provider entity from the "XXCCMS_PROVIDERFIRMS_V" database table.
 *
 * <p>This entity is immutable, meaning its state cannot be changed once it's created.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_PROVIDERCONTACTS_V", schema = "XXCCMS")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProviderContact {

  /** The unique identifier for the provider firm. */
  @Id
  @Column(name = "CONTACT_ID")
  private Integer id;

  /** The name of the provider. */
  @Column(name = "CONTACT_NAME")
  private String name;
}
