package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
 * Represents a lookup value for counsel.
 *
 * <p>This entity corresponds to the "XXCCMS_COUNSELS_V" table in the database. The JSON
 * representation of this entity uses the snake case naming strategy.
 *
 * <p>It's an immutable entity, meaning its state cannot be changed once it's created. The primary
 * attributes of this entity helps in identifying the record
 *
 * @see PropertyNamingStrategies.SnakeCaseStrategy
 * @author Ashutosh Gautam
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({
  "name",
  "company",
  "category",
  "county",
  "outwardPostCode",
  "legalAidSupplierNumber"
})
@Table(name = "XXCCMS_COUNSELS_V", schema = "XXCCMS")
@Immutable
public class CounselLookupValue {

  @Id
  @Column(name = "COUNSEL_ID")
  private String counselId;

  @Column(name = "NAME")
  private String name;

  @Column(name = "COMPANY")
  private String company;

  @Column(name = "ADVOCATE_LEVEL")
  private String category;

  @Column(name = "COUNTY")
  private String county;

  @Column(name = "OUTWARD_POST_CODE")
  private String outwardPostCode;

  @Column(name = "LEGAL_AID_SUPP_NUMBER")
  private String legalAidSupplierNumber;
}
