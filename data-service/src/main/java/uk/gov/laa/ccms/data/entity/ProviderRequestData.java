package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Entity representing the provider request data.
 */
@Entity
@Table(name = "XXCCMS_PROVIDER_REQUEST_DATA_V")
@Data
@NoArgsConstructor
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProviderRequestData implements Serializable {

  @EmbeddedId
  private ProviderRequestDataId id;

  @Column(name = "data_item_label")
  private String dataItemLabel;

  @Column(name = "data_item_type", nullable = false)
  private String dataItemType;

  @Column(name = "mandatory_flag", nullable = false)
  private String mandatoryFlag;

  @Column(name = "data_item_seq", nullable = false)
  private String dataItemSeq;

  @Column(name = "lov_lookup_type")
  private String lovLookupType;

  @ManyToOne
  @JoinColumn(name = "REQUEST_TYPE")
  private ProviderRequestType providerRequestType;



}
