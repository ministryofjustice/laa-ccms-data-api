package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@Data
@Immutable
public class CommonLookupValueId implements Serializable {
    @Column(name = "LOV_TYPE")
    private String type;

    @Column(name = "CODE")
    private String code;

}