package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Provider {

    /**
     * The unique identifier for the provider firm.
     */
    @Column(name = "PROVIDERFIRM_ID")
    private Integer id;

    /**
     * The name of the provider.
     */
    @Column(name = "PROVIDER_NAME")
    private String name;
}