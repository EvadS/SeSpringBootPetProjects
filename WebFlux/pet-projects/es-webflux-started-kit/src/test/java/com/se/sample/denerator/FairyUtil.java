package com.se.sample.denerator;

import io.codearte.jfairy.Fairy;
import lombok.var;
import org.junit.jupiter.api.Test;

public class FairyUtil {

    @Test
    void test(){
        Fairy fairy = Fairy.create();

        var tee = fairy.company();
    }
}
