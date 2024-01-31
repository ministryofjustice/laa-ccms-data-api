package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents the ProceedingClientInvolvementType entity in the database.
 * This entity is used to store information about the type of client involvement in a proceeding.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_PROC_CLIENT_INV_TYPE_V")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProceedingClientInvolvementType {

  /**
   * The ID of the ProceedingClientInvolvementType.
   * This is an embedded ID, meaning it's a composite primary key.
   */
  @EmbeddedId
  private ProceedingClientInvolvementTypeId id;

  /**
   * The name of the client involvement type.
   */
  @Column(name = "CLIENT_INVOLVEMENT_TYPE_NAME")
  private String clientInvolvementTypeName;
}
