package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_APP_AMEND_TYPES_V")
@Immutable
public class AmendmentTypeLookupValue implements Serializable {

    @Id
    @Column(name = "APP_TYPE_CODE")
    private String applicationTypeCode;

    @Column(name = "APP_TYPE_DESCRIPTION")
    private String applicationTypeDescription;

    @Column(name = "COST_LIMIT_CAP")
    private String costLimitCap;

    @Column(name = "DEVOLVED_POWERS_IND")
    private String devolvedPowersInd;

    @Column(name = "DEFAULT_LAR_SCOPE_FLAG")
    private String defaultLARScopeFlag;


}