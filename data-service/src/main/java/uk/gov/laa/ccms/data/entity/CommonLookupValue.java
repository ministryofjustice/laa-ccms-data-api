package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_COMMON_LOV_V")
@Immutable
@IdClass(CommonLookupValueId.class)
public class CommonLookupValue implements Serializable {

    @Id
    @Column(name = "LOV_TYPE")
    private String type;

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_DATE_ACTIVE")
    private LocalDateTime startDateActive;

    @Column(name = "ATTRIBUTE11")
    private String attribute11;

    @Column(name = "ATTRIBUTE12")
    private String attribute12;

    @Column(name = "ENABLED_FLAG")
    private String enabled;

    @Column(name = "DEFAULT_CODE")
    private String defaultCode;

}

