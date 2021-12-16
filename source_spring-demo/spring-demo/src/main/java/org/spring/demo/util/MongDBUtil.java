package org.spring.demo.util;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujj on 2020-07-03.
 */
public class MongDBUtil {

  public static DB getConnect(){
    //连接到 mongodb 服务
    MongoClient mongoClient = new MongoClient("10.0.1.103", 27017);

    //连接到数据库
    DB mongoDatabase = mongoClient.getDB("iastdb");

    //返回连接数据库对象
    return mongoDatabase;
  }

  //需要密码认证方式连接
  public static DB getConnect2(){
    List<ServerAddress> adds = new ArrayList<ServerAddress>();
    //ServerAddress()两个参数分别为 服务器地址 和 端口
    ServerAddress serverAddress = new ServerAddress("10.0.1.103", 27017);
    adds.add(serverAddress);

    List<MongoCredential> credentials = new ArrayList<MongoCredential>();
    //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
    MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("hujj", "admin", "test@123".toCharArray());
    credentials.add(mongoCredential);

    //通过连接认证获取MongoDB连接
    MongoClient mongoClient = new MongoClient(adds, credentials);

    //连接到数据库
    DB mongoDatabase = mongoClient.getDB("admin");

    //返回连接数据库对象
    return mongoDatabase;
  }

}
