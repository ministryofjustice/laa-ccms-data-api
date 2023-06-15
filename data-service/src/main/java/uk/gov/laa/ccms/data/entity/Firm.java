package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "XXCCMS_USER_FIRMS_V")
@Immutable
public class Firm implements Serializable {

    /**
     * The unique identifier for the firm.
     */
    @Id
    @Column(name = "PROVIDERFIRM_ID")
    private Integer id;

    /**
     * The name of the firm.
     */
    @Column(name = "PROVIDER_NAME")
    private String name;

    /**
     * The end date of the user associated with the firm.
     * This field is ignored during JSON serialization/deserialization.
     */
    @Column(name = "USER_END_DATE")
    @JsonIgnore
    private LocalDateTime userEndDate;
}
