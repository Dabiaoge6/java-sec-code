package com.seczone.security.test;

import java.lang.Runtime;
import java.lang.Process;
public class Muma1{

    static {

        try {

            Runtime rt = Runtime.getRuntime();

            String[] commands = {"calc.exe"};
            Process pc = rt.exec(commands);

            pc.waitFor();

        } catch (Exception e) {

// do nothing

        }

    }
}
