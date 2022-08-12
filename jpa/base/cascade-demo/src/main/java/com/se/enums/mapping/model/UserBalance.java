package com.se.enums.mapping.model;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity(name = "UserBalance")
@Table(name = "user_balance")
public class UserBalance {
    @EmbeddedId
    private UserBalanceId userBalanceId;

    private BigInteger amount;

    public UserBalance() {
    }

    public UserBalance(UserBalanceId userBalanceId, BigInteger amount) {
        this.userBalanceId = userBalanceId;
        this.amount = amount;
    }

    public UserBalanceId getUserBalanceId() {
        return userBalanceId;
    }

    public void setUserBalanceId(UserBalanceId userBalanceId) {
        this.userBalanceId = userBalanceId;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UserBalance{" +
                "userBalanceId=" + userBalanceId +
                ", amount=" + amount +
                '}';
    }
}
