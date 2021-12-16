package org.spring.demo.controller.teststring;

public class TestMain {

  public static void main(String[] args) {
    String inputName = "seczone";
    char[] chars = new char[inputName.length()];
    inputName.getChars(0, inputName.length(), chars, 0);//seczone [0,6]
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(chars, 1, 4);//eczo [0,3]
    System.out.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    CharSequence charSequence = inputName;
    stringBuilder.append(charSequence, 1, 5);//eczo [0,3]
    System.out.println(stringBuilder.toString());

    stringBuilder = new StringBuilder("test");
    charSequence = "012"+inputName;//[3,9]
    stringBuilder.append(charSequence, 1, 10);//eczo [0,3]
    System.out.println(stringBuilder.toString());

    stringBuilder = new StringBuilder("test");
    charSequence = inputName+"012";//[0,6]
    stringBuilder.append(charSequence, 3, 6);//eczo [0,3]
    System.out.println(stringBuilder.toString());
  }

}
