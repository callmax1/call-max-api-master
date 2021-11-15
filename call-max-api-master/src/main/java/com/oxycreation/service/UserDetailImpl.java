package com.oxycreation.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oxycreation.model.Role;
import com.oxycreation.model.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class UserDetailImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    private String profileImage;

    private Role role;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailImpl(Long id, String email, String password,String profileImage,Role role,
                          Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.profileImage =profileImage;
        this.role= role;
        this.authorities = authorities;
    }

    public static UserDetailImpl build(User user) {


        return new UserDetailImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getProfileImage(),
                user.getRole(),
                Collections.emptyList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailImpl user = (UserDetailImpl) o;
        return Objects.equals(id, user.id);
    }
}

