package com.codenation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    @JsonIgnore
    private Collection<Role> roles;

    public Authority(final String name) {
        super();
        this.name = name;
    }

    public Authority(Long id, String name, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public Authority(){}

    @Override
    public String getAuthority() {
        return name;
    }
}