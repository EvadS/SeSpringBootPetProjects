package com.se.songs.entity;

import javax.persistence.*;

//TODO move to admin module
@Entity(name = "AdminUser")
@Table(name = "admin_user")
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;


    private String password;

    // TODO: add to db
    private boolean enabled;

    //private AdminUserStatus status;

    public AdminUser() {
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole roleEntity;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserRole getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(UserRole roleEntity) {
        this.roleEntity = roleEntity;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}