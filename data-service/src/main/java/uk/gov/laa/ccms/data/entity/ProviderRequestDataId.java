package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;

/**
 * Composite ID class for provider request data.
 */
@Data
@Immutable
public class ProviderRequestDataId implements Serializable {


  @Column(name = "request_type", nullable = false, insertable = false, updatable = false)
  private String requestType;

  @Column(name = "data_item_code", nullable = false)
  private String dataItemCode;

}
