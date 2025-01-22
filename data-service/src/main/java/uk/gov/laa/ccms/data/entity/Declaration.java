package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Entity representing a declaration with type, bill type, and declaration number.
 * This class is immutable and uses snake_case naming for JSON serialization.
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_DECLARATION_TEXT_V", schema = "XXCCMS")
@Immutable
@IdClass(DeclarationId.class)
public class Declaration {

  /**
   * The type of the declaration.
   */
  @Id
  @Column(name = "DECLARATION_TYPE")
  private String declarationType;

  /**
   * The type of the bill associated with the declaration.
   */
  @Column(name = "BILL_TYPE")
  //This cannot be included in the id due to the null constraint
  //if changes are made to this view, we will need to rethink this approach.
  private String billType;

  /**
   * The unique number identifying the declaration.
   */
  @Id
  @Column(name = "DECLARATION_NUMBER")
  private String declarationNumber;

  /**
   * The text content of the declaration.
   */
  @Id
  @Column(name = "DECLARATION_TEXT")
  private String declarationText;


  /**
   * Replaces any invalid characters in the declaration text with a single quote.
   */
  @PostLoad
  public void handleDeclarationText() {
    if (declarationText != null) {
      declarationText = declarationText.replace('�', '\'');
    }
  }
}
