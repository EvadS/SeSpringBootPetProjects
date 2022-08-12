package com.se.demo.trans.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WalletID implements Serializable {

   private String walletCurrency;
   private long accountId;

    public WalletID() {
    }

    public WalletID(String walletCurrency, long accountId) {
        this.walletCurrency = walletCurrency;
        this.accountId = accountId;
    }

    public String getWalletCurrency() {
        return walletCurrency;
    }

    public void setWalletCurrency(String walletCurrency) {
        this.walletCurrency = walletCurrency;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long employeeKey) {
        this.accountId = employeeKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletID walletID = (WalletID) o;
        return accountId == walletID.accountId &&
                Objects.equals(walletCurrency, walletID.walletCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletCurrency, accountId);
    }
}
