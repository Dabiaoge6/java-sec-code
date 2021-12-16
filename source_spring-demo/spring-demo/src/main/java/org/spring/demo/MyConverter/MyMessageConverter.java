package org.spring.demo.MyConverter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

public class MyMessageConverter extends AbstractHttpMessageConverter<User> {
  private Logger logger = LoggerFactory.getLogger(MyMessageConverter.class);

  public MyMessageConverter(){
    super(new MediaType("application","x-java-serialized-object",Charset.forName("UTF-8")));
  }

  protected boolean supports(Class<?> clazz) {
    return User.class.isAssignableFrom(clazz);
  }

  protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage)
      throws IOException, HttpMessageNotReadableException {
    String requestBody = StreamUtils.copyToString(inputMessage.getBody(),Charset.forName("UTF-8"));
    String[] bodyArray = requestBody.split("-");
    User user = new User();
    user.setName(bodyArray[0]);
    user.setPwd(bodyArray[1]);
    return user;
  }

  protected void writeInternal(User user, HttpOutputMessage outputMessage)
      throws IOException, HttpMessageNotWritableException {
  }

  /*public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new MyMessageConverter());
  }*/
}
