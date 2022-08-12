package com.se.demo.trans.entity;

import com.se.embeded.demo.entity.AccountId;

import javax.persistence.*;

@Entity
@Table(name = "wallet")

public class Wallet {

    @EmbeddedId
    private  WalletID walletID;

    //@Type(type = "org.hibernate.type.TextType")
    @Column(length = 1024)
    private  String name;

    private double amount;

    private String reference;

    public Wallet() {
    }


}


/*
@Entity
@Table(name = "wallet")
@IdClass(WalletID.class)
public class Wallet {


    @Id
    private String walletCurrency;
    @Id
    private long accountId;

    //@Type(type = "org.hibernate.type.TextType")
    @Column(length = 1024)
    private  String name;

    private double amount;

    private String reference;

    public Wallet() {
    }


}

 */