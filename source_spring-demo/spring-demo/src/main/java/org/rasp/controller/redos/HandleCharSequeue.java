package org.rasp.controller.redos;


public class HandleCharSequeue {

  private static HandleCharSequeue instance = new HandleCharSequeue();

  public static HandleCharSequeue get(){
    return instance;
  }

  private HandleCharSequeue(){
  }

  public CharSequence hardenMatcher(PatternUtil paramPattern, CharSequence paramCharSequence) {
    return new CustomCharSequeue(paramPattern,paramCharSequence,3000000);
  }

}
