package com.se.pattern.factory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SavingAccount implements Account{

   Logger logger = LoggerFactory.getLogger(SavingAccount.class);
    @Override
    public void accountType() {
        logger.info("SAVING ACCOUNT");
    }
}
