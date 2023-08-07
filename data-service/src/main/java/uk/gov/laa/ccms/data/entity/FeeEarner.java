package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/*
 * Represents a Fee Earner entity
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_FEE_EARNERS_V")
@Immutable
public class FeeEarner implements Serializable {
  /**
   * The unique identifier for the FeeEarner
   */
  @Id
  @Column(name = "CONTACT_ID")
  private Integer id;

  /**
   * The name for the Fee Earner
   */
  @Column(name = "CONTACT_NAME")
  private String name;


  /**
   * The Fee Earner's related ProviderFirm
   */
  @ManyToOne
  @JoinColumn(name = "PROVIDERFIRM_ID")
  private Provider provider;

}
