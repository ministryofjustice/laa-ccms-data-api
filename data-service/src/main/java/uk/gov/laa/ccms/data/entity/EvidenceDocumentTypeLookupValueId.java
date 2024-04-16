package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


/**
 * Represents the composite primary key for the {@link EvidenceDocumentTypeLookupValue} entity.
 *
 * <p>This class is used to uniquely identify each record in the associated evidence
 * document type lookup value entity. The combination of the lookup value type and its code
 * forms the composite key.</p>
 *
 * <p>The class is marked as immutable, ensuring the integrity and consistency of the identifier
 * once it's created.</p>
 *
 * @see EvidenceDocumentTypeLookupValue
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class EvidenceDocumentTypeLookupValueId implements Serializable {

  @Column(name = "LOV_TYPE")
  private String type;

  @Column(name = "CODE")
  private String code;

}