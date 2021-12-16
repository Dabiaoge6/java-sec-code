package org.vulhunter.common.xxe;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "worker")
public class Worker {
    List<Programmer> programmer;

    public List<Programmer> getProgrammer() {
        return programmer;
    }

    @XmlElement(name = "programmer")
    public void setProgrammer(List<Programmer> programmer) {
        this.programmer = programmer;
    }
    
    

}
