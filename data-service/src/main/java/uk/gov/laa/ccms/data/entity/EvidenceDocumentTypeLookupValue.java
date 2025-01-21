package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


/**
 * Represents a evidence document type lookup value entity.
 *
 * <p>This entity corresponds to the "XXCCMS_EVIDENCE_DOC_TYPE_V" view in the database and is used
 * for evidence document type lookup values. Each record is uniquely identified by its type and
 * code, both serving as composite primary keys.</p>
 *
 * <p>This entity is immutable, meaning its state cannot be changed once it's created.</p>
 *
 * @see PropertyNamingStrategies.SnakeCaseStrategy
 */
@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_EVIDENCE_DOC_TYPE_V", schema = "XXCCMS")
@Immutable
public class EvidenceDocumentTypeLookupValue implements Serializable {

  @EmbeddedId
  private EvidenceDocumentTypeLookupValueId id;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "START_DATE_ACTIVE")
  private LocalDateTime startDateActive;

  @Column(name = "END_DATE_ACTIVE")
  private LocalDateTime endDateActive;
}

