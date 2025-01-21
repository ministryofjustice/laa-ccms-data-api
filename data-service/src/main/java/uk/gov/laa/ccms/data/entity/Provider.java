package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a provider entity from the "XXCCMS_PROVIDERFIRMS_V" database table.
 *
 * <p>This entity is immutable, meaning its state cannot be changed once it's created.</p>
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_PROVIDERFIRMS_V", schema = "XXCCMS")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Provider {

  /**
   * The unique identifier for the provider firm.
   */
  @Id
  @Column(name = "PROVIDERFIRM_ID")
  private Integer id;

  /**
   * The name of the provider.
   */
  @Column(name = "PROVIDERFIRM_NAME")
  private String name;

  /**
   * The provider's related offices.
   */
  @OneToMany
  @JoinColumn(name = "PROVIDERFIRM_ID")
  private List<Office> offices;

  /**
   * The provider's related contact names.
   */
  @OneToMany
  @JoinColumn(name = "PROVIDERFIRM_ID")
  private List<ProviderContact> contactNames;

}