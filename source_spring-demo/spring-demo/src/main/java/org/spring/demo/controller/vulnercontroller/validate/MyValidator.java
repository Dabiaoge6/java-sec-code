package org.spring.demo.controller.vulnercontroller.validate;

import org.apache.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyValidator implements ConstraintValidator<InputValidator, String> {
    private Logger logger = Logger.getLogger(MyValidator.class);
    private String regex = "[^/\\\\\\\\]+$";
    private Pattern pattern = Pattern.compile(regex);

    public void initialize(InputValidator constraintAnnotation) {

    }

    public boolean isValid(String path, ConstraintValidatorContext context) {
        if (path == null) {
            logger.error("Input path is null");
            return false;
        }
        Matcher matcher = pattern.matcher(path);
        return matcher.matches();
    }
}
