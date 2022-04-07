package com.example.Spring1.Model;

import com.example.Spring1.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    private String id;
    private String username;
    private String password;
    private List<GrantedAuthority> Authority;

    public MyUserDetails(User user,String role) {
        this.id = String.valueOf(user.getId());
        this.username = user.getStudent_name();
        this.password = user.getPassword();
        Authority=Arrays.stream(new String[]{role}).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public MyUserDetails(Adminn user,String role) {
        this.id = String.valueOf(user.getId());
        this.username = user.getUsername();
        this.password = user.getPassword();
        Authority=Arrays.stream(new String[]{role}).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
