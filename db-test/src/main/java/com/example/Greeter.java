package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Greeter {

    private static final Logger LOGGER = LogManager.getLogger(Greeter.class);

    public String method1(String str) {
        LOGGER.info(str);
        return str;
    }

    public String magi() {
        String str = "magi";
        return str;
    }

}
