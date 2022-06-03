package ru.kata.spring.boot_security.demo.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "db_pred_prod", name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }

    public Role(Long id, Set<User> users, String role) {
        this.id = id;
        this.users = users;
        this.role = role;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
