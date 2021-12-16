package org.vulhunter.common.untrustdeserialization;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class XStreamUtil {
  private XStreamUtil() {
    throw new IllegalStateException("Utility class");
  }
  private static Map<Class<?>, XStream> xStreamMap = new ConcurrentHashMap<Class<?>, XStream>();

  private static ReentrantLock xStreamLock = new ReentrantLock();
  private static XStream getXStreamInstance(Class<?> clazz) {
    return createXstreamNotExist(clazz, clazz);
  }


  /**
   * 解决不同的请求对应的key 类型不同，但是defineInClass的类型是相同的
   *
   * @param keyClazz
   * @param defineInClazz
   * @return
   */
  private static XStream getXStreamInstance(Class<?> keyClazz, Class<?> defineInClazz) {
    return createXstreamNotExist(keyClazz, defineInClazz);
  }

  private static XStream createXstreamNotExist(Class<?> keyClazz, Class<?> parseClazz) {
    if (xStreamMap.containsKey(keyClazz)) {
      return xStreamMap.get(keyClazz);
    }
    xStreamLock.lock();
    try {
      XStream xStream = new XStream(new StaxDriver());
      //解决安全警告: Security framework of XStream not initialized, XStream is probably vulnerable.
      XStream.setupDefaultSecurity(xStream);
      xStream.ignoreUnknownElements();
      //该package下的类允许解析
      xStream.allowTypesByWildcard(new String[]{"org.vulhunter.common.untrustdeserialization.User"});
      xStream.processAnnotations(parseClazz);
      xStreamMap.put(keyClazz, xStream);
      return xStream;
    }finally {
      xStreamLock.unlock();
    }

  }


  public static Object fromXML(String xml, final Class type) {
    try {
      return getXStreamInstance(type).fromXML(xml);
    } catch (XStreamException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String toXml(Object obj) {
    try {
      return getXStreamInstance(obj.getClass()).toXML(obj);
    } catch (XStreamException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String marshal(Object obj) {
    try {
      final StringWriter stringWriter = new StringWriter();
      getXStreamInstance(obj.getClass()).marshal(obj, new CompactWriter(stringWriter));
      return stringWriter.toString();
    } catch (XStreamException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String toXml(Class<?> keyClazz, Object obj) {
    try {
      return getXStreamInstance(keyClazz, obj.getClass()).toXML(obj);
    } catch (XStreamException e) {
      e.printStackTrace();
    }
    return null;

  }

  /**
   * 动态添加别名
   *
   * @param keyClazz   key 的class ，eg：OBCResEle
   * @param parseClazz 解析的根元素，eg: CommonResRoot
   * @param alias      别名 eg: OBCReq
   * @param definedIn  字段定义于哪个元素 eg:CommonMessageRoot
   * @param fieldName  字段的名称 eg: res
   * @throws Exception
   */
  public static void addFiledAlias(Class keyClazz, Class parseClazz, String alias, Class definedIn, String fieldName) throws Exception {
    try {
      XStream stream = getXStreamInstance(keyClazz, parseClazz);
      stream.aliasField(alias, definedIn, fieldName);
      stream.aliasSystemAttribute(null, "class");
    } catch (XStreamException e) {
      throw new Exception(e);
    }
  }
}