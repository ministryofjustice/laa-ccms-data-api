package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_APP_CASE_STATUS_V")
@Immutable
public class CaseStatusLookupValue {

  @Id
  @Column(name = "STATUS_CODE")
  private String code;

  @Column(name = "STATUS_DESCRIPTION")
  private String description;

  @Column(name = "COPY_ALLOWED_IND")
  private Boolean copyAllowed;
}
