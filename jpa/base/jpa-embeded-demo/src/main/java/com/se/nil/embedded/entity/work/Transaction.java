package com.se.nil.embedded.entity.work;


import com.se.nil.embedded.entity.Office;

import javax.persistence.*;

@Entity(name = "Transaction")
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "wallet_id_from", referencedColumnName = "account_id"),
            @JoinColumn(name = "wallet_pc_from", referencedColumnName = "payment_currency")
    })
    private Wallet walletFrom;

//    @ManyToOne(fetch= FetchType.LAZY)
//    @JoinColumns({
//            @JoinColumn(name = "wallet_account_id_to", referencedColumnName = "id"),
//            @JoinColumn(name = "wallet_pc_to", referencedColumnName = "pc")
//    })
//    private Wallet walletTo;


    private double walletAmount;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wallet getWalletFrom() {
        return walletFrom;
    }

    public void setWalletFrom(Wallet walletFrom) {
        this.walletFrom = walletFrom;
    }

    public double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(double walletAmount) {
        this.walletAmount = walletAmount;
    }

//    public Wallet getWalletTo() {
//        return walletTo;
//    }
//
//    public void setWalletTo(Wallet walletTo) {
//        this.walletTo = walletTo;
//    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", walletAmount=" + walletAmount +
                '}';
    }
}
