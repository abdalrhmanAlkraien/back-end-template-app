package com.marinabits.energietechs.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Objects;

@Entity
@Table(name = "AUTHORITY")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@ToString(exclude = {"rolePK"})
public class Role implements GrantedAuthority {

    public static final String CLIENT = "CLIENT";
    public static final String ADMIN = "ADMIN";

    @Serial
    private static final long serialVersionUID = 5369984016984936492L;

    @EmbeddedId
    protected RolePK rolePK;

    @NonNull
    @Basic(optional = false)
    @Column(nullable = false, insertable = false, updatable = false)
    private String authority;

    public Role(RolePK rolePK) {
        setRolePK(rolePK);
        this.authority = rolePK.getAuthority();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Role role = (Role) o;
        return Objects.equals(this.rolePK, role.rolePK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.rolePK);
    }
}
