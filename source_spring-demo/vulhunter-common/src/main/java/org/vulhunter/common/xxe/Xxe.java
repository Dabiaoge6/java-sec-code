/*
 *
 * FileName：TestText
 * Description：TestText
 * Version：1.0
 * Modified By：
 * Author: mapf
 * Date:2021/1/8 9:09
 */

package org.vulhunter.common.xxe;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author mapf
 * @description
 * @date 2021/1/8 9:09
 */
@XmlRootElement(name = "xxe")
public class Xxe {

    private String name;


    private XxeContent content;

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public XxeContent getContent() {
        return content;
    }
    @XmlElement
    public void setContent(XxeContent content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Xxe{" + "name='" + name + '\'' + ", content=" + content + '}';
    }
}
