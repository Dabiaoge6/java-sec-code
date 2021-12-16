package org.spring.demo.controller.testjson;

import com.alibaba.fastjson.JSON;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <p>
 * Title: JsonUtils class
 * </p>
 *
 * @author Lance Li
 * @version 1.0.0
 */

public class JsonUtils {

  private final int MAX_RESOLVE_DEEPTH = 50;

  private Stack<String> stack;
  private Map<String, String> parameterMap;
  private int currentResolvedDeepth = 0;

  public static <T> T toObject(String jsonString, Class<T> T) {
    try {
      return JSON.parseObject(jsonString, T);
    } catch (Exception e) {
      return null;
    }
  }

  public static <T> List<T> toList(String jsonString, Class<T> T) {
    try {
      return JSON.parseArray(jsonString, T);
    } catch (Exception e) {
      return null;
    }
  }

  public static String fromObject(Object dataObject) {
    try {
      return JSON.toJSONString(dataObject);
    } catch (Exception e) {
      return null;
    }
  }

  public Map<String, String> toMap(String jsonStr) {
    resetCurrentDeepth();
    JSONObject jsonObject = new JSONObject(jsonStr);
    stack = new Stack<String>();
    parameterMap = new LinkedHashMap<String, String>();
    if (jsonObject == null) {
      return parameterMap;
    }
    visitJsonObject(jsonObject);
    //System.out.println(jsonObject.toString());
    return parameterMap;
  }

  private void visitJsonObject(JSONObject jsonObject) {
    if (isOverMaxDeepthLimit()){
      return;
    }
    Iterator iterator = jsonObject.keys();
    while (iterator.hasNext()) {
      String key = (String) iterator.next();
      stack.push(key);
      if (isJsonArray(jsonObject, key)) {
        JSONArray array = jsonObject.getJSONArray(key);
        visitJsonArray(array);
      } else if (isJsonObject(jsonObject, key)) {
        JSONObject object = jsonObject.getJSONObject(key);
        visitJsonObject(object);
      } else {
        visitJsonObjectLeaf(jsonObject, key);
      }
      stack.pop();
    }
    increaseDeepth();
  }

  private void visitJsonArray(JSONArray jsonArray) {
    if (isOverMaxDeepthLimit()){
      return;
    }

    boolean isEmptyArray = jsonArray.length()==0;
    if (isEmptyArray){
      return;
    }

    for (int i = 0; i < jsonArray.length(); i++) {
      stack.push("" + i);
      if (isJsonArray(jsonArray, i)) {
        JSONArray array = jsonArray.getJSONArray(i);
        visitJsonArray(array);
      } else if (isJsonObject(jsonArray, i)) {
        JSONObject object = jsonArray.getJSONObject(i);
        visitJsonObject(object);
      } else {
        visitArrayLeaf(jsonArray, i);
      }
      stack.pop();
    }

    increaseDeepth();
  }

  private boolean isOverMaxDeepthLimit(){
    return currentResolvedDeepth >= MAX_RESOLVE_DEEPTH;
  }

  private void increaseDeepth(){
    ++currentResolvedDeepth;
  }

  private void resetCurrentDeepth(){
    currentResolvedDeepth = 0;
  }

  private void visitJsonObjectLeaf(JSONObject jsonObject, String key) {
    String value = jsonObject.get(key).toString();
    parameterMap.put(getNodeName(), value);
  }

  private void visitArrayLeaf(JSONArray array, int index) {
    String value = array.get(index).toString();
    parameterMap.put(getNodeName(), value);
  }

  private boolean isJsonArray(JSONObject object, String key) {
    try {
      JSONArray array = object.getJSONArray(key);
      if (array != null) {
        return true;
      }
    } catch (Exception e) {
    }
    return false;
  }

  private boolean isJsonObject(JSONObject object, String key) {
    try {
      JSONObject jsonObject = object.getJSONObject(key);
      if (jsonObject != null) {
        return true;
      }
    } catch (Exception e) {
    }
    return false;
  }

  private boolean isJsonArray(JSONArray jsonArray, int index) {
    try {
      JSONArray array = jsonArray.getJSONArray(index);
      if (array != null) {
        return true;
      }
    } catch (JSONException e) {
    }
    return false;
  }

  private boolean isJsonObject(JSONArray jsonArray, int index) {
    try {
      JSONObject object = jsonArray.getJSONObject(index);
      if (object != null) {
        return true;
      }
    } catch (Exception e) {
    }
    return false;
  }

  private String getNodeName() {
    String prefixName = "";
    for (int i = 0; i < stack.size(); i++) {
      String stackedNode = stack.get(i);
      prefixName += stackedNode + "/";
    }
    return prefixName.substring(0, prefixName.length() - 1);
  }

  public String setValue(String jsonStr, String key, String value) {
    JSONObject jsonObject = new JSONObject(jsonStr);
    List<String> keys = Utils.StringToList(key, "/");
    Stack<String> keyStack = new Stack<String>();
    for (int i = keys.size() - 1; i >= 0; i--) {
      keyStack.push(keys.get(i));
    }
    visitJsonObjectForSetValue(jsonObject, keyStack, value);
    return jsonObject.toString();
  }

  private void visitJsonObjectForSetValue(JSONObject jsonObject, Stack<String> keys, String value) {
    String _key = keys.pop();
    if (isJsonObject(jsonObject, _key)) {
      JSONObject object = jsonObject.getJSONObject(_key);
      this.visitJsonObjectForSetValue(object, keys, value);
    } else if (isJsonArray(jsonObject, _key)) {
      JSONArray array = jsonObject.getJSONArray(_key);
      this.visitJsonArrayForSetValue(array, keys, value);
    } else {
      jsonObject.remove(_key);
      jsonObject.put(_key, value);
    }
  }

  private void visitJsonArrayForSetValue(JSONArray array, Stack<String> keys, String value) {
    String key = keys.pop();

    // Empty Object
    if (Utils.isEmpty(key)){
      array.put(0, value);
      return;
    }

    try {
      int _index = Integer.valueOf(key);
      if (isJsonArray(array, _index)) {
        array = array.getJSONArray(_index);
        this.visitJsonArrayForSetValue(array, keys, value);
      } else if (isJsonObject(array, _index)) {
        JSONObject object = array.getJSONObject(_index);
        this.visitJsonObjectForSetValue(object, keys, value);
      } else {
        array.remove(_index);
        array.put(_index, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
