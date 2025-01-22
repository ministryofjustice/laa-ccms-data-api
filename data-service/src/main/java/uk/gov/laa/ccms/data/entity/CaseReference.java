package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


/**
 * Represents a case reference from the generate reference view.
 *
 * @author Jamie Briggs
 * @see CaseReference
 * */
@Getter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_GENERATE_V", schema = "XXCCMS")
@Immutable
public class CaseReference implements Serializable {

  /**
   * The next available case reference number.
   */
  @Id
  @Column(name = "NEW_CASE_REFERENCE")
  private String caseReference;
}
