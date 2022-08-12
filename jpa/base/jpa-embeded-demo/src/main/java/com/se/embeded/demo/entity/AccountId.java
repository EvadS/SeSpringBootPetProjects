package com.se.embeded.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class AccountId implements Serializable {
    private String accountNumber;
    private String accountType;

    public AccountId() {
    }

    public AccountId(String accountNumber, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        AccountId accountId = (AccountId) o;
//        return Objects.equals(accountNumber, accountId.accountNumber) &&
//                Objects.equals(accountType, accountId.accountType);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(accountNumber, accountType);
//    }

// equals() and hashCode()
}