package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

/**
 * Represents a user role in the system.
 */
@Entity
@Immutable
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_USER_ROLES_V")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@IdClass(UserRole.class)
public class UserRole implements Serializable {

    /**
     * The login ID of the user.
     */
    @Id
    @Column(name = "USER_LOGIN_ID")
    private String loginId;

    /**
     * The function associated with the user role.
     */
    @Id
    @Column(name = "FUNCTION")
    private String function;
}
