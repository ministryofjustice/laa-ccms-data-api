package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


/**
 * Represents a firm entity from the "XXCCMS_USER_FIRMS_V" database table.
 *
 * <p>This entity captures essential details about a firm, such as its unique identifier, name,
 * and the end date associated with the user of the firm.</p>
 *
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_USER_FIRMS_V", schema = "XXCCMS")
@Immutable
public class Firm implements Serializable {

  /**
   * The unique identifier for the firm.
   */
  @Id
  @Column(name = "PROVIDERFIRM_ID")
  private Integer id;

  /**
   * The name of the firm.
   */
  @Column(name = "PROVIDER_NAME")
  private String name;

  /**
   * Whether this is the primary provider firm.
   */
  @Column(name = "PRIMARY_FLAG")
  private Boolean isPrimary;

  /**
   * The end date of the user associated with the firm.
   * This field is ignored during JSON serialization/deserialization.
   */
  @Column(name = "USER_END_DATE")
  @JsonIgnore
  private LocalDateTime userEndDate;
}
