package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents an Office entity.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_PROVIDER_OFFICES_V")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Office {

  /**
   * The office ID.
   */
  @Id
  @Column(name = "OFFICE_ID")
  private Integer id;

  /**
   * The name of the office.
   */
  @Column(name = "OFFICE_NAME")
  private String name;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "office")
  private List<FeeEarner> feeEarners;

}
