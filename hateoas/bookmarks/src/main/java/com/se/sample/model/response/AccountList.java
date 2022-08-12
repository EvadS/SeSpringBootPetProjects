package com.se.sample.model.response;

import com.se.sample.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountList {

    private List<Account> accountsList = new ArrayList<>();

    public AccountList(List<Account> list) {
        this.accountsList = new ArrayList<>(list);
        // TODO: remove
//        accountsList = list.stream()
//                .collect(Collectors.toList());
    }

    public List<Account> getAccountsList() {
        return accountsList;
    }
}
