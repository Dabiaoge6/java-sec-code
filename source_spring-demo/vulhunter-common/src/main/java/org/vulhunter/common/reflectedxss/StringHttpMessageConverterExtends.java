package org.vulhunter.common.reflectedxss;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.StringHttpMessageConverter;

public class StringHttpMessageConverterExtends extends StringHttpMessageConverter {

    public void getWriteInternal(String inputName, HttpOutputMessage outputMessage) throws IOException {
        writeInternal(inputName,outputMessage);
    }
}
