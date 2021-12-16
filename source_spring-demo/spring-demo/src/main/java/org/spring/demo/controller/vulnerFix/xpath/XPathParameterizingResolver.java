package org.spring.demo.controller.vulnerFix.xpath;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathVariableResolver;
import java.util.HashMap;
import java.util.Map;

public class XPathParameterizingResolver implements XPathVariableResolver {
    Map<QName, Object> vars = new HashMap<QName, Object>();

    public void addVariable(QName name, Object value) {
        vars.put(name, value);
    }

    public Object resolveVariable(QName name) {
        return vars.get(name);
    }
}
