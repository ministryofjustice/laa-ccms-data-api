package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "XXCCMS_PRIOR_AUTH_DETAILS_V", schema = "XXCCMS")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PriorAuthority {

  /**
   * The code for the Prior Auth.
   */
  @Id
  @Column(name = "CODE")
  private String code;

  /**
   * The description of the Prior Auth.
   */
  @Column(name = "DESCRIPTION")
  private String description;

  /**
   * The data type.
   */
  @Column(name = "DATA_TYPE")
  private String dataType;

  /**
   * The lov code.
   */
  @Column(name = "LOV_CODE")
  private String lovCode;

  /**
   * The mandatory flag.
   */
  @Column(name = "MANDATORY_FLAG")
  private Boolean mandatoryFlag;

  /**
   * Many to one back reference to PriorAuthorityType.
   */
  @ManyToOne
  @JoinColumn(name = "PRIOR_AUTHORITY_TYPE_CODE")
  private PriorAuthorityType type;

}