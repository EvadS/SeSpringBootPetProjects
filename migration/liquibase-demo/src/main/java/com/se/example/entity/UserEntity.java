package com.se.example.entity;

import javax.persistence.*;


@Entity
@Table(name = "scr_mst_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_id", nullable = false)
    private String email;

    @Column(name = "customer_id_user_id", nullable = false)
    private String customerIdUserId;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerIdUserId() {
        return customerIdUserId;
    }

    public void setCustomerIdUserId(String customerIdUserId) {
        this.customerIdUserId = customerIdUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
