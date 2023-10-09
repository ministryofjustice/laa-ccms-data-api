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
 * Represents a Prior Authority Type entity from the "XXCCMS_PRIOR_AUTHORITY_TYPE_V" database table.
 *
 * <p>This entity is immutable, meaning its state cannot be changed once it's created.</p>
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_PRIOR_AUTHORITY_TYPE_V")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PriorAuthorityType {

  /**
   * The code for the Prior Auth Type.
   */
  @Id
  @Column(name = "CODE")
  private String code;

  /**
   * The description of the Prior Auth Type.
   */
  @Column(name = "DESCRIPTION")
  private String description;

  /**
   * The value required flag.
   */
  @Column(name = "VALUE_REQUIRED_FLAG")
  private Boolean valueRequired;

  /**
   * The related prior authority details for this type.
   */
  @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
  private List<PriorAuthority> priorAuthorities;

}