package org.spring.demo.controller.testjson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

  private static Logger logger = LoggerFactory.getLogger(Utils.class);

  public static boolean isEmpty(String str) {
    return str == null || str.length() < 1;
  }

  public static List<String> StringToList(String str) {
    return StringToList(str, ",");
  }

  public static List<String> StringToList(String str, String split) {
    List<String> strs = new ArrayList<String>();
    if (Utils.isEmpty(str)) {
      return strs;
    }
    if (str.contains(split)) {
      strs = Arrays.asList(str.split(split));
    } else {
      strs.add(str);
    }
    return strs;
  }

}
