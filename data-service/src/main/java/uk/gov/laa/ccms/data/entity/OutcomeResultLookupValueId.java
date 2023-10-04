package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;


/**
 * Represents the composite primary key for the {@link OutcomeResultLookupValue} entity.
 *
 * <p>This class is used to uniquely identify each record in the associated common lookup value
 * entity. The combination of the proceeding code and outcome result forms the composite key.</p>
 *
 * <p>The class is marked as immutable, ensuring the integrity and consistency of the identifier
 * once it's created.</p>
 *
 * @see OutcomeResultLookupValue
 */
@Data
@Immutable
public class OutcomeResultLookupValueId implements Serializable {

  /**
   * The proceeding code for the Outcome Result.
   */
  @Column(name = "PROCEEDING_CODE")
  private String proceedingCode;

  /**
   * The name of the outcome result.
   */
  @Column(name = "OUTCOME_RESULT")
  private String outcomeResult;

}
