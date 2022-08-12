package com.se.demo.trans.entity;

import org.hibernate.mapping.Map;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyType;

    public Account() {
    }

//    @OneToMany
//            (
//            mappedBy = "Wallet.a"
//    )

    @OneToMany(mappedBy = "walletID.accountId", fetch = FetchType.EAGER)
    private List<Wallet> walletList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public List<Wallet> getWalletList() {
        return walletList;
    }

    public void setWalletList(List<Wallet> walletList) {
        this.walletList = walletList;
    }
}
