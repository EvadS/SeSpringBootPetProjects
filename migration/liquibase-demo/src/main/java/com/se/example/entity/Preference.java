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


    private String childName;
    @ManyToOne
    @JoinColumn(name = "scr_mst_users_id", nullable = true)
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

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
