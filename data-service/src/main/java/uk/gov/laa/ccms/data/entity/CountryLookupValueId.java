package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;

/**
 * Represents the composite primary key for the {@link CountryLookupValue} entity.
 *
 * <p>This class is used to uniquely identify each record in the associated common lookup value
 * entity. The combination of the lookup value type and its code forms the composite key.
 *
 * <p>The class is marked as immutable, ensuring the integrity and consistency of the identifier
 * once it's created.
 *
 * @see CountryLookupValue
 */
@Data
@Immutable
public class CountryLookupValueId implements Serializable {

  @Column(name = "CODE")
  private String code;

  @Column(name = "DESCRIPTION")
  private String description;
}
