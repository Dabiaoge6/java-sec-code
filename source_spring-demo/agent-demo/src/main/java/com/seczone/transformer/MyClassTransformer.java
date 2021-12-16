package com.seczone.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class MyClassTransformer implements ClassFileTransformer {

  @Override
  public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
      ProtectionDomain protectionDomain, byte[] classfileBuffer) {
    if ("com/seczone/Test".equals(className)) {
      try {
        // 从ClassPool获得CtClass对象
        final ClassPool classPool = ClassPool.getDefault();
        final CtClass clazz = classPool.get("com.seczone.Test");
        CtMethod method =clazz.getMethod("print","(Ljava/lang/String;)V");
        String beforeCodeSrc = "com.seczone.PreTest.beforeMethod($1);";
        method.insertBefore(beforeCodeSrc);
        String afterCodeSrc = "com.seczone.PreTest.afterMethod($1);";
        method.insertAfter(afterCodeSrc);
        byte[] byteCode = clazz.toBytecode();
        //detach的意思是将内存中曾经被javassist加载过的Date对象移除，如果下次有需要在内存中找不到会重新走javassist加载
        clazz.detach();
        return byteCode;
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    // 如果返回null则字节码不会被修改
    return null;
  }
}
