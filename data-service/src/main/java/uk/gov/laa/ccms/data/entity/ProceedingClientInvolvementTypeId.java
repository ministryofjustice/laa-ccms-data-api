package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;

/**
 * Represents the composite primary key for the
 * {@link uk.gov.laa.ccms.data.entity.ProceedingClientInvolvementType} entity.

 * @see ProceedingClientInvolvementType
 */
@Data
@Immutable
public class ProceedingClientInvolvementTypeId implements Serializable {

  /**
   * The proceeding code.
   */
  @Column(name = "PROCEEDING_CODE")
  private String proceedingCode;

  /**
   * The client involvement type.
   */
  @Column(name = "CLIENT_INVOLVEMENT_TYPE")
  private String clientInvolvementType;

}
