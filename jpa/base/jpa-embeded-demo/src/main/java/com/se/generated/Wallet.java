package com.se.generated;

import javax.persistence.*;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @Column(name = "employee_key")
    private Long employeeKey;

    @Id
    @Column(name = "wallet_currency")
    private String walletCurrency;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "name")
    private String name;

    @Column(name = "reference")
    private String reference;


    public Long getEmployeeKey() {
        return this.employeeKey;
    }

    public void setEmployeeKey(Long employeeKey) {
        this.employeeKey = employeeKey;
    }

    public String getWalletCurrency() {
        return this.walletCurrency;
    }

    public void setWalletCurrency(String walletCurrency) {
        this.walletCurrency = walletCurrency;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
