package cn.seczone.mongdb.demo;

import cn.seczone.mongdb.demo.util.MongDBUtil;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

/**
 * Created by hujj on 2020-07-03.
 */
public class SimpleTest {
  private static final String HOST = "10.0.1.103";
  private static final int PORT = 27017;
  private static final String DB_NAME = "admin";

  public static void main(String[] args) {
    //查询所有的聚集集合
    DB db = MongDBUtil.getConnect2();
    //执行shell
    CommandResult commandResult = db.doEval("db.version()");
    System.out.println(commandResult.getString("retval"));
  }

}
