package org.vulhunter.common.xxe;

import nu.xom.Element;

public class Person {
    
    private String first;
    private String last;
    
    public Person(Element person)  
    {  
        first=person.getFirstChildElement("first").getValue() + "testFirst";  
        last=person.getFirstChildElement("last").getValue() + "testLast";  
    }
    
    public String getFirst() {
        return first;
    }
    public void setFirst(String first) {
        this.first = first;
    }
    public String getLast() {
        return last;
    }
    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Person [first=" + first + ", last=" + last + "]";
    }
    
}
