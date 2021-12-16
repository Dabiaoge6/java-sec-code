package cn.webank.rmb.message;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestBid {

  private String path;

  public TestBid() {
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public static void main(String[] args) {
    TestBid bid = new TestBid();
    bid.setPath("D:\\webank");
    Gson gson = new Gson();
    String path = gson.toJson(bid);
    System.out.println(path);

  }
}
