package org.spring.demo;

import java.util.ArrayList;
import java.util.List;

public class TrackScopeLock {

  private static ThreadLocal<Integer> sinkLocker;
  private static ThreadLocal<Integer> locker;
  private static ThreadLocal<Integer> sourceLocker;
  private static boolean inited = false;

  public static void lazyInitialize() {
    if (!inited) {
      sinkLocker = new ThreadLocal<Integer>() {
        public Integer initialValue() {
          return 0;
        }
      };
      locker = new ThreadLocal<Integer>() {
        public Integer initialValue() {
          return 0;
        }
      };
      sourceLocker = new ThreadLocal<Integer>() {
        public Integer initialValue() {
          return 0;
        }
      };
      inited = true;
    }
  }

  public static void reset() {
    lazyInitialize();
    sinkLocker.set(0);
    locker.set(0);
    sourceLocker.set(0);
  }

  public static void resetProcessLock() {
    lazyInitialize();
    locker.set(0);
  }

  public static boolean isSinkAllowed() {
    lazyInitialize();
    return sinkLocker.get() == 1;
  }

  public static boolean isAllowed() {
    lazyInitialize();
    return locker.get() == 1;
  }

  public static boolean isSourceAllowed() {
    lazyInitialize();
    return sourceLocker.get() == 1;
  }

  public static void enterMethodScope() {
    lazyInitialize();
    locker.set(locker.get() + 1);
  }

  public static void leaveMethodScope() {
    lazyInitialize();
    locker.set(locker.get() - 1);
  }

  public static void enterSinkMethodScope() {
    lazyInitialize();
    sinkLocker.set(sinkLocker.get() + 1);
  }

  public static void leaveSinkMethodScope() {
    lazyInitialize();
    sinkLocker.set(sinkLocker.get() - 1);
  }

  public static void enterSourceMethodScope() {
    lazyInitialize();
    sourceLocker.set(sourceLocker.get() + 1);
  }

  public static void leaveSourceMethodScope() {
    lazyInitialize();
    sourceLocker.set(sourceLocker.get() - 1);
  }

  public static ThreadLocal<List<String>> verifiedRuleIds = new ThreadLocal() {
    @Override
    protected Object initialValue() {
      return new ArrayList<String>(10);
    }
  };

  public static void checkSecurityValidation(String ruleId,Object args) {
    try {
      verifiedRuleIds.get().add(ruleId);
    } catch (Throwable throwable) {
    }
  }

  public static void checkSecurityXstrem(Object thisObj) {
    try {
//      verifiedRuleIds.get().add(ruleId);
    } catch (Throwable throwable) {
    }
  }

}
