package org.vulhunter.common.hardpassword;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class HardCodePasswordTest {

    public final String password = "seczone@123";
    
    public final String secret = "seczone@123456";
    
    /*public void test() {
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }*/

}
