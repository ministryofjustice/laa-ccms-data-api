package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;


/**
 * Represents the composite primary key for the {@link StageEndLookupValue} entity.
 *
 * <p>This class is used to uniquely identify each record in the associated common lookup value
 * entity. The combination of the proceeding code and stage end value forms the composite key.</p>
 *
 * <p>The class is marked as immutable, ensuring the integrity and consistency of the identifier
 * once it's created.</p>
 *
 * @see StageEndLookupValue
 */
@Data
@Immutable
public class StageEndLookupValueId implements Serializable {

  /**
   * The proceeding code.
   */
  @Column(name = "PROCEEDING_CODE")
  private String proceedingCode;

  /**
   * The stage end value.
   */
  @Column(name = "STAGE_END")
  private String stageEnd;

}
