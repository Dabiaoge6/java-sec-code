package org.spring.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence charSequence) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(charSequence);
        //加密方法可以根据自己的需要修改
        return encode;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
	}

}
