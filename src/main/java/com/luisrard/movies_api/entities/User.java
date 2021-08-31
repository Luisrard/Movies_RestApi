package com.luisrard.movies_api.entities;


import com.luisrard.movies_api.entities.enums.AppUserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/**
 * The users of the app
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 60, unique = true)
    private String nickname;
    @Column(nullable = false, length = 80, unique = true)
    private String mail;
    private boolean locked = false;
    private boolean enabled = false;
    @Enumerated(EnumType.STRING)
	private AppUserRole role;

    //User details implementation
    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Transient
    @Override
    public String getUsername() {
        return this.mail;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
