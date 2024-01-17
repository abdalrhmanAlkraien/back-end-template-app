package com.marinabits.energietechs.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

/**
 * Class represents User domain model, as JPA Entity.
 *
 * @author Omar Ismail
 * @version 1.0
 */
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"USER_UUID"}),
                @UniqueConstraint(columnNames = {"USERNAME"})
        })
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
public class User extends AbstractEntity implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 5666668516577592568L;

    @Basic(optional = false)
    @Column(name = "USER_UUID", nullable = false, updatable = false)
    @ToString.Exclude
    private String userUuid;

    @NonNull
    @Basic(optional = false)
    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @NonNull
    @Basic(optional = false)
    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @NonNull
    @Basic(optional = false)
    @Column(nullable = false)
    @ToString.Exclude
    private String username;

    @NonNull
    @Basic(optional = false)
    @Column(nullable = false)
    @ToString.Exclude
    private String password;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Transient
    private String accessToken;

    @Column(name = "TOKEN_EXPIRY_DATE")
    private Instant tokenExpiryDate;

    @JoinColumn(name = "LANGUAGE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = EAGER)
    private Language language;


    @OneToMany(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID",
            nullable = false, insertable = false, updatable = false)
    private Set<Role> authorities = new HashSet<>();

    @ToString.Exclude
    private boolean enabled = true;

    public User(Integer id) {
        super.setId(id);
    }

    public void setAuthorities(Set<Role> roles) {
        for (Role role : roles) {
            role.setRolePK(new RolePK(this.getId(), role.getAuthority()));
            this.authorities.add(role);
        }
    }

    public void setAuthorities(String... authorities) {
        for (String authority : authorities)
            this.authorities.add(new Role(new RolePK(this.getId(), authority)));
    }

    public String getFullName() {
        return getFirstName()
                .concat(" ")
                .concat(getLastName());
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        User user = (User) o;
        return Objects.equals(this.getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId(), this.userUuid, this.firstName, this.lastName, this.username,
                this.password, this.authorities, this.enabled, this.refreshToken);
    }
}
