package cn.seczone.hessian.demo;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.vulhunter.bean.User;

public class Test1 {

  private static final String url = "http://localhost:8087/hessian-demo/testPost";

  public static void main(String[] args) throws Throwable {
    // 序列化
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    Hessian2Output h2o = new Hessian2Output(os);

    h2o.startMessage();
    h2o.writeObject(new User("hujj", "seczone"));
    h2o.writeString("I am client.");
    h2o.completeMessage();
    h2o.close();

    byte[] buffer = os.toByteArray();
    os.close();

    ByteArrayEntity byteArrayEntity = new ByteArrayEntity(buffer,
        ContentType.create("x-application/hessian", "UTF-8"));

    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(url);
    post.setEntity(byteArrayEntity);
    CloseableHttpResponse response = client.execute(post);

    System.out.println("response status:\n"
        + response.getStatusLine().getStatusCode());
    HttpEntity body = response.getEntity();
    InputStream is = body.getContent();
    Hessian2Input h2i = new Hessian2Input(is);
    h2i.startMessage();

    User person = (User) h2i.readObject();
    System.out.println("response:\n" + person.toString());
    System.out.println(h2i.readString());

    h2i.completeMessage();
    h2i.close();
    is.close();

  }

}
