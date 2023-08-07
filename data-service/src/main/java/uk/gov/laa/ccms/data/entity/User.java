package uk.gov.laa.ccms.data.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Where;

/**
 * Represents a user entity.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "XXCCMS_USERS_V")
@Immutable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {

    /**
     * The user ID.
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * The login ID of the user.
     */
    @Id
    @Column(name = "USER_LOGIN_ID")
    private String loginId;

    /**
     * The party ID of the user.
     */
    @Column(name = "USER_PARTY_ID")
    private Integer partyId;

    /**
     * The username of the user.
     */
    @Column(name = "USER_NAME")
    private String username;

    /**
     * The type of the user.
     */
    @Column(name = "USER_TYPE")
    private String userType;

    /**
     * The firms associated with the user.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_LOGIN_ID")
    @Where(clause = "user_end_date IS NULL OR user_end_date > TRUNC(SYSDATE)")
    private List<Firm> firms;

    /**
     * The functions assigned to the user.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "XXCCMS_USER_ROLES_V", joinColumns = @JoinColumn(name = "USER_LOGIN_ID"))
    @Column(name = "FUNCTION")
    private List<String> functions;

    /**
     * The provider of the user.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVIDERFIRM_ID", referencedColumnName = "PROVIDERFIRM_ID")
    private Provider provider;
}
