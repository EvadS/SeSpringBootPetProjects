package com.se.generated;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name3")
    private String name3;

    @Column(name = "wallet_from_employee_key")
    private Long walletFromEmployeeKey;

    @Column(name = "wallet_from_wallet_currency")
    private String walletFromWalletCurrency;

    @Column(name = "wallet_to_employee_key")
    private Long walletToEmployeeKey;

    @Column(name = "wallet_sender_wallet_currency")
    private String walletSenderWalletCurrency;



    public String getName3() {
        return this.name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public Long getWalletFromEmployeeKey() {
        return this.walletFromEmployeeKey;
    }

    public void setWalletFromEmployeeKey(Long walletFromEmployeeKey) {
        this.walletFromEmployeeKey = walletFromEmployeeKey;
    }

    public String getWalletFromWalletCurrency() {
        return this.walletFromWalletCurrency;
    }

    public void setWalletFromWalletCurrency(String walletFromWalletCurrency) {
        this.walletFromWalletCurrency = walletFromWalletCurrency;
    }

    public Long getWalletToEmployeeKey() {
        return this.walletToEmployeeKey;
    }

    public void setWalletToEmployeeKey(Long walletToEmployeeKey) {
        this.walletToEmployeeKey = walletToEmployeeKey;
    }

    public String getWalletSenderWalletCurrency() {
        return this.walletSenderWalletCurrency;
    }

    public void setWalletSenderWalletCurrency(String walletSenderWalletCurrency) {
        this.walletSenderWalletCurrency = walletSenderWalletCurrency;
    }
}
