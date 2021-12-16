package org.spring.demo.controller.vulnercontroller;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import org.spring.demo.SecVerify;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vulhunter.common.untrustdeserialization.UntrustdeserialCommon;
import org.vulhunter.common.untrustdeserialization.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;


@Controller
@RequestMapping("untrustedDeserialization")
public class UntrustedDeserializationController {

    @RequestMapping(value = "/XStream/deserial001.do")
    public void fromXML_Str1(@RequestParam String xmlString, HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        XStream xstream = new XStream();
        xstream.alias("user", User.class);
        User user = (User) xstream.fromXML(xmlString);
    }
}
