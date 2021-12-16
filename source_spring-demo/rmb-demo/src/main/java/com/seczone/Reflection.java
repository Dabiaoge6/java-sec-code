package com.seczone;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reflection {

  private static final Logger LOGGER = LoggerFactory.getLogger(Reflection.class.getName());

  /**
   * 根据方法名调用对象的某一个方法
   *
   * @param object 调用方法的对象
   * @param methodName 方法名称
   * @param paramTypes 参数类型列表
   * @param parameters 参数列表
   * @return 方法返回值
   */
  public static Object invokeMethod(Object object, String methodName, Class[] paramTypes,
      Object... parameters) {
    if (null == object) {
      return null;
    }
    return invokeMethod(object, object.getClass(), methodName, paramTypes, parameters);
  }

  /**
   * 反射调用方法，并把返回值进行强制转换为String
   *
   * @return 被调用函数返回的String
   * @see #invokeMethod(Object, String, Class[], Object...)
   */
  public static String invokeStringMethod(Object object, String methodName, Class[] paramTypes,
      Object... parameters) {
    Object ret = invokeMethod(object, methodName, paramTypes, parameters);
    return ret != null ? (String) ret : null;
  }

  /**
   * 反射获取对象的字段包括私有的
   *
   * @param object 被提取字段的对象
   * @param fieldName 字段名称
   * @return 字段的值
   */
  public static Object getField(Object object, String fieldName) {
    Object fieldInstance = null;
    try {
      Field field = object.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      fieldInstance = field.get(object);
    } catch (NoSuchFieldException e) {
      LOGGER.error(e.getMessage(), e);
    } catch (IllegalAccessException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return fieldInstance;
  }

  /**
   * 反射获取父类对象的字段包括私有的
   *
   * @param paramClass 被提取字段的对象
   * @param fieldName 字段名称
   * @return 字段的值
   */
  public static Object getSuperField(Object paramClass, String fieldName) {
    Object object = null;
    try {
      Field field = paramClass.getClass().getSuperclass().getDeclaredField(fieldName);
      field.setAccessible(true);
      object = field.get(paramClass);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    return object;
  }

  /**
   * 调用某一个类的静态方法
   *
   * @param className 类名
   * @param methodName 方法名称
   * @param paramTypes 参数类型列表
   * @param parameters 参数列表
   * @return 方法返回值
   */
  public static Object invokeStaticMethod(String className, String methodName, Class[] paramTypes,
      Object... parameters) {
    try {
      Class clazz = Class.forName(className);
      return invokeMethod(null, clazz, methodName, paramTypes, parameters);
    } catch (Exception e) {
      LOGGER.error("failed to invoke static method: " + e.getMessage(), e);
      return null;
    }
  }

  public static Object invokeMethod(Object object, Class clazz, String methodName,
      Class[] paramTypes, Object... parameters) {
    try {
      Method method = clazz.getMethod(methodName, paramTypes);
      if (!method.isAccessible()) {
        method.setAccessible(true);
      }
      return method.invoke(object, parameters);
    } catch (Exception e) {
      String message = "Reflection call " + methodName + " failed: " + e.getMessage();
      if (clazz != null) {
        message =
            "Reflection call " + clazz.getName() + "." + methodName + " failed: " + e.getMessage();
      }
      LOGGER.error(message, e);
      return null;
    }
  }

  public static boolean isPrimitiveType(Object object) {
    try {
      return ((Class<?>) object.getClass().getField("TYPE").get(null)).isPrimitive();
    } catch (Exception e) {
      return false;
    }
  }

}
