package com.se.nil.embedded.entity.work;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * user wallet model
 *
 * @author Evgeniy Skiba
 */
@Entity(name = "Wallet")
@Table(name = "wallet")
public class Wallet implements Serializable {

    @EmbeddedId
    private WalletId walletId;

    private double balance;

    public Wallet() {

    }

    public Wallet(WalletId walletId,  double balance) {
        this.walletId = walletId;
        this.balance = balance;
      }

    public WalletId getWalletId() {
        return walletId;
    }

    public void setWalletId(WalletId walletId) {
        this.walletId = walletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletId=" + walletId +
                ", balance=" + balance +
                '}';
    }
}
