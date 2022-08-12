package com.se.songs.dto;

import com.se.songs.enums.AdminUserRole;

public class AdminUserDTO {
    private Long id;
    private String email;
    private String password;

    private AdminUserRole adminUserRole;


//    private AdminUserRole role;


    public AdminUserDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public AdminUserRole getAdminUserRole() {
        return adminUserRole;
    }

    public void setAdminUserRole(AdminUserRole adminUserRole) {
        this.adminUserRole = adminUserRole;
    }
}
