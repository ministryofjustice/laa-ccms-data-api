package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_PROVIDERFIRMS_V")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Provider {

    /**
     * The unique identifier for the provider firm.
     */
    @Id
    @Column(name = "PROVIDERFIRM_ID")
    private Integer id;

    /**
     * The name of the provider.
     */
    @Column(name = "PROVIDERFIRM_NAME")
    private String name;

    /**
     * The provider's related offices.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVIDERFIRM_ID")
    private List<Office> offices;

}