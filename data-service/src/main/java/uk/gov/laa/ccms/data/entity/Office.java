package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Represents an Office entity.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_PROVIDER_OFFICES_V")
@Immutable
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Office {

    /**
     * The office ID.
     */
    @Id
    @Column(name = "OFFICE_ID")
    private Integer id;

    /**
     * The name of the office.
     */
    @Column(name = "OFFICE_NAME")
    private String name;

}
