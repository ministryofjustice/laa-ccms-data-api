package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a Proceeding entity from the "XXCCMS_PROCEEDING_V" database table.
 *
 * <p>This entity is immutable, meaning its state cannot be changed once it's created.</p>
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_PROCEEDING_V", schema = "XXCCMS")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Proceeding {

  /**
   * The unique identifier for the proceeding.
   */
  @Id
  @Column(name = "PROCEEDING_CODE")
  private String code;

  /**
   * The name of the proceeding.
   */
  @Column(name = "PROCEEDING_NAME")
  private String name;

  /**
   * The description of the proceeding.
   */
  @Column(name = "DESCRIPTION")
  private String description;

  /**
   * The related category of law for the proceeding.
   */
  @Column(name = "CATEGORY_OF_LAW_CODE")
  private String categoryOfLawCode;

  /**
   * The stage end lov value for the proceeding.
   */
  @Column(name = "STAGE_END_LOV")
  private String stageEndLov;

  /**
   * The outcome result lov value for the proceeding.
   */
  @Column(name = "OUTCOME_RESULT_LOV")
  private String outcomeResultLov;

  /**
   * The matter type value for the proceeding.
   */
  @Column(name = "MATTER_TYPE")
  private String matterType;

  /**
   * The amendment only flag for the proceeding.
   */
  @Column(name = "AMENDMENT_ONLY")
  private Boolean amendmentOnly;

  /**
   * The enabled flag for the proceeding.
   */
  @Column(name = "ENABLED_FLAG")
  private Boolean enabled;

  /**
   * The order type required flag for the proceeding.
   */
  @Column(name = "ORDER_TYPE_REQUIRED")
  private Boolean orderTypeRequired;

  /**
   * The lar scope value for the proceeding.
   */
  @Column(name = "PROC_LAR_SCOPE")
  private String larScope;

}