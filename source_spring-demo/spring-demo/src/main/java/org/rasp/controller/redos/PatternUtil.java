package org.rasp.controller.redos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

  public static final Pattern PATTERN = Pattern.compile("([a-zA-Z]+)*");

  public Matcher matchCharacter(CharSequence sequence) {
    CharSequence hardenMatcher;
    if ((hardenMatcher = HandleCharSequeue.get().hardenMatcher(this, sequence)) != null) {
      sequence = hardenMatcher;
    }
    return PATTERN.matcher(sequence);
  }

}
