package org.spring.demo;

import com.thoughtworks.xstream.security.TypePermission;
import com.thoughtworks.xstream.security.WildcardTypePermission;

public class TestXStream {
  /*private SecurityMapper securityMapper;
  private transient boolean securityInitialized;*/
//  private String ruleId = "xss";
  private boolean securityFlag=false;

  public void allowTypesByWildcard(String[] patterns) {
//    TrackScopeLock.enterMethodScope();
    this.addPermission();
    this.securityFlag = true;
    /*if (TrackScopeLock.isAllowed()) {
      securityFlag = true;
//      TrackScopeLock.checkSecurityXstrem(this);
    }
    TrackScopeLock.leaveMethodScope();*/
  }

  public void addPermission() {
    /*if (this.securityMapper != null) {
      this.securityInitialized = true;
      this.securityMapper.addPermission(permission);
    }*/

  }

}
