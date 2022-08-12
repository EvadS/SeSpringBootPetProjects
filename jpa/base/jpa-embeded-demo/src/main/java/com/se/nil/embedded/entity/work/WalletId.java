package com.se.nil.embedded.entity.work;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WalletId implements Serializable {

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "payment_currency")
    private String paymentCurrency;

    public WalletId() {

    }

    public WalletId(Long accountId, String paymentCurrency) {
        this.accountId = accountId;
        this.paymentCurrency = paymentCurrency;
    }

    public Long getId() {
        return accountId;
    }

    public void setId(Long accountId) {
        this.accountId = accountId;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletId walletId = (WalletId) o;
        return Objects.equals(accountId, walletId.accountId) &&
                Objects.equals(paymentCurrency, walletId.paymentCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, paymentCurrency);
    }
}
