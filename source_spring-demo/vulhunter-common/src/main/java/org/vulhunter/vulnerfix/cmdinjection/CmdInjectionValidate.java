package org.vulhunter.vulnerfix.cmdinjection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CmdInjectionValidate {

    /**
     * 正则验证
     */
    public String regStr() default "";
}
