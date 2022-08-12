package com.se.pattern.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrentAccount implements Account{
    Logger logger = LoggerFactory.getLogger(CurrentAccount.class);

    @Override
    public void accountType() {
        logger.info("CURRENT ACCOUNT");
    }
}
