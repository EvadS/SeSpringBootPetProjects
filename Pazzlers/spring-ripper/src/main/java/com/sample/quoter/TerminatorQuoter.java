package com.sample.quoter;

import javax.annotation.PostConstruct;

@Profiling
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;
    private String message;

    public TerminatorQuoter() {
        System.out.println("phase1. Constructor. Repeater=" + repeat);
    }

    @PostConstruct
    public void init() {

        System.out.println("phase2. PostConstructor. Repeater=" + repeat);
    }

    @Override
    public void sayQuotes() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("messages = " + message);
        }
    }

    // TODO: for xml configuration
    public void setMessage(String message) {
        this.message = message;
    }
}
