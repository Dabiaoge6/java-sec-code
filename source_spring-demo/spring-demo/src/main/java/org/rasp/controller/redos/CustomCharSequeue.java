package org.rasp.controller.redos;

import java.util.regex.Pattern;

public class CustomCharSequeue implements CharSequence {

  private PatternUtil c;
  private CharSequence d;
  private int e;
  private int f;
  private long g;

  public CustomCharSequeue() {
  }

  public CustomCharSequeue(PatternUtil c, CharSequence d, int e) {
    this.c = c;
    this.d = d;
    this.e = e;
    this.f = 0;
    this.g = 0L;
  }

  @Override
  public int length() {
    return 0;
  }

  @Override
  public char charAt(int index) {
    return 0;
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    return null;
  }
}
