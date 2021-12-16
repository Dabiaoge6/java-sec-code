/*
 *
 * FileName：XxeContent
 * Description：XxeContent
 * Version：1.0
 * Modified By：
 * Author: mapf
 * Date:2021/1/22 10:49
 */

package org.vulhunter.common.xxe;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author mapf
 * @description
 * @date 2021/1/22 10:49
 */

public class XxeContent {

    String id;

    String text;

    public String getId() {
        return id;
    }
    @XmlElement
    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    @XmlElement
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "XxeContent{" + "id='" + id + '\'' + ", text='" + text + '\'' + '}';
    }
}
