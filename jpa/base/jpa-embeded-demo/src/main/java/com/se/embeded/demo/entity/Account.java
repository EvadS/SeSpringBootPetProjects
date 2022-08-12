package com.se.embeded.demo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
@IdClass(AccountId.class)
public class Account //implements Serializable
{
    @Id
    private String accountNumber;
    @Id
    private String accountType;

    private double balance;

    public Account() {
    }

    public Account(String accountNumber, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Account account = (Account) o;
//        return Double.compare(account.balance, balance) == 0 &&
//                Objects.equals(accountNumber, account.accountNumber) &&
//                Objects.equals(accountType, account.accountType);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(accountNumber, accountType, balance);
//    }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // getters and setters, equals(), toString() .... (omitted for brevity)
}