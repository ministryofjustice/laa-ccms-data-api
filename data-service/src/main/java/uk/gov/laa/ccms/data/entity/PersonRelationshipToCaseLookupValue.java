package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents a Person to case relationship entity from the "XXCCMS_PER_RELTOCASE_V" database view.
 *
 * <p>This entity is immutable, meaning its state cannot be changed once it's created.</p>
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_PER_RELTOCASE_V", schema = "XXCCMS")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PersonRelationshipToCaseLookupValue {

  @Id
  @Column(name = "CODE")
  private String code;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "DEFAULT_CODE")
  private Boolean defaultCode;

  @Column(name = "OPPONENT_IND")
  private Boolean opponentInd;

  @Column(name = "DOB_MANDATORY")
  private Boolean dateOfBirthMandatory;

  @Column(name = "COPY_PARTY")
  private Boolean copyParty;

}
