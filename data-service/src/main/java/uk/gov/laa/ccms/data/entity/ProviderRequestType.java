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
 * Represents a provider request type entity, mapped to the database view
 * XXCCMS_PROVIDER_REQTYPES_V. This class is immutable and uses
 * SnakeCaseStrategy for JSON serialization.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_PROVIDER_REQTYPES_V")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProviderRequestType {


  @Id
  @Column(name = "REQUEST_TYPE")
  private String type;

  @Column(name = "REQUEST_NAME")
  private String name;

  @Column(name = "CASE_RELATED_FLAG")
  private Boolean caseRelated;

  @Column(name = "ADDITIONAL_INFO_PROMPT")
  private String additionalInformationPrompt;

  @Column(name = "TASK_TYPE_ID")
  private String taskTypeId;

  @Column(name = "FILE_UPLD_ENABLED")
  private Boolean fileUploadEnabled;

  @Column(name = "ACCESS_FUNC_CODE")
  private String accessFunctionCode;

  @Column(name = "FILE_UPLD_PROMPT")
  private String fileUploadPrompt;


}
