package com.se.example.entity;

import javax.persistence.*;


@Entity
@Table(name = "scr_notification")
public class Preference {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private  Long notificationId;

    @Column(name="customer_id", nullable = false)
    private String customerId;

    @ManyToOne
    @JoinColumn(name = "scr_mst_users_customer_id_user_id", nullable = true)
    private UserEntity userEntity;

    public Preference() {
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
