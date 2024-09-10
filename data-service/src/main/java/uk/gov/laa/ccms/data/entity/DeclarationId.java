package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;


/**
 * Composite key class for Declaration entity, consisting of declaration type, declaration number
 * and declaration text. This class is immutable.
 */
@Data
@Immutable
public class DeclarationId implements Serializable {

  /**
   * The type of the declaration.
   */
  @Column(name = "DECLARATION_TYPE")
  private String declarationType;

  /**
   * The unique number identifying the declaration.
   */
  @Column(name = "DECLARATION_NUMBER")
  private String declarationNumber;

  /**
   * The text for the declaration.
   */
  @Column(name = "DECLARATION_TEXT")
  private String declarationText;


}
