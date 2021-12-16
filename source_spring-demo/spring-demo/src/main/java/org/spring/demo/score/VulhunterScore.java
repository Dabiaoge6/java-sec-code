package org.spring.demo.score;

import com.csvreader.CsvWriter;
import org.spring.demo.score.entity.*;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * 1.读取“expectresult.csv”,生成expectResultList 2.分析“engine.log”: 2.1.生成actualResultList
 * 2.2.生成actualresult.csv 3.比较“expectResultList”和“actualResultList” *
 * 3.1.将actualResultList中的TestCaseResult对象字段report改为false 3.2.生成vulhunterResultList
 * 3.3.生成vulhunterresult.csv 4.打分 4.1.分析vulhunterResultList 4.1.1.计算每个漏洞的漏报，误报 4.1.2.计算工具的漏报，误报
 * 4.1.3.生成vulhunterscore.csv
 */
public class VulhunterScore {

  private static final String FIRST_WORD = "/";
  private static final String ENGINE_DEFAULT_HOME = "/.seczone/logs";
  private static List<String> allExecuteURLList = new ArrayList<String>();
  private static Logger logger = Logger.getLogger(VulhunterScore.class.getName());
  private static String engineHome = "";

  static {
    engineHome = System.getProperty("vul.home");
    if (engineHome == null || engineHome.equals("")) {
      engineHome = System.getProperty("user.home");
    }
  }

  /**
   * 1.读取“expectresult.csv”,生成expectResultList
   */
  public static List<TestCaseResult> createExpectResultList() throws Exception {
    //1.获取“expectedresults.csv”输入流
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream is = loader.getResourceAsStream("score/expectedresults_1.csv");
    InputStreamReader isr = new InputStreamReader(is);
    List<TestCaseResult> expectResultList = new ArrayList<TestCaseResult>();
    BufferedReader br = null;
    try {
      br = new BufferedReader(isr);
      //2.将读取的每一行转换成java对象
      boolean reader = true;
      String line = null;
      while (reader) {
        line = br.readLine();
        reader = line != null;
        if (reader) {
          String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
          if (parts[0] != null && parts[0].startsWith(FIRST_WORD)) {
            TestCaseResult testCaseResult = new TestCaseResult();
            testCaseResult.setUrl(parts[0]);
            testCaseResult.setCategory(parts[1]);
            testCaseResult.setRealVul(Boolean.parseBoolean(parts[2]));
            expectResultList.add(testCaseResult);
          }
        }
      }
    } finally {
      if (br != null) {
        br.close();
      }
    }
    return expectResultList;
  }

  /**
   * 2.分析engine.log文件 2.1.生成actualResultList
   *
   * @return actualResultList engine所有上报的漏洞集合
   */
  public static List<TestCaseResult> createAcutalResultList() throws Exception {
    File logFile = getEngineLog();
    BufferedReader reader = new BufferedReader(new FileReader(logFile));
    List<TestCaseResult> actualResultList = new ArrayList<TestCaseResult>();
    String line = "";
    int i = 0;
    try {
      while (line != null) {
        line = reader.readLine();
        if (line != null) {
          if (line.contains("current request uri is")) {
            String searchName = "current request uri is";
            int searchLength = searchName.length();
            int idx = line.indexOf(searchName);
            String url = line.substring(idx + searchLength, line.length());
            if (!allExecuteURLList.contains(url)) {
              allExecuteURLList.add(url);
            }
          } else if (line.contains("Report String===>>>")) {
            logger.info("report string num is " + (i++));
            String searchName1 = "\"uri\":";
            String searchName2 = "\"ruleId\":";
            String searchName3 = "\"url\":";
            String searchName4 = "\"server\":";
            String searchName5 = "\"sensitiveValue\":";
            int idx1 = line.indexOf(searchName1);
            int idx2 = line.indexOf(searchName2);
            int idx3 = line.indexOf(searchName3);
            int idx4 = line.indexOf(searchName4);
            int idx5 = line.indexOf(searchName5);
            int searchName1Length = searchName1.length();
            int searchName2Length = searchName2.length();
            String uri = "";
            String ruleId = "";
            if (idx1 != -1 && idx2 != -1 && idx3 != -1) {
              try {
                uri = line.substring(idx1 + searchName1Length + 1, idx3 - 2);
              } catch (StringIndexOutOfBoundsException e) {
                System.err.print("idx1==>" + idx1 + ",idx2==>" + idx2 + ",idx3==>" + idx3);
                System.err.println("line ===>" + line);
              }
              if (idx5 != -1) {
                ruleId = line.substring(idx2 + searchName2Length + 1, idx5 - 2);
              } else {
                ruleId = line.substring(idx2 + searchName2Length + 1, idx4 - 2);
              }
              if (ruleId.equals("library-report") || ruleId.equals("heartbeat") || ruleId
                  .equals("server-report") || ruleId.equals("app-report")) {
                break;
              }
              TestCaseResult tcr = new TestCaseResult();
              tcr.setUrl(uri);
              tcr.setCategory(ruleId);
              tcr.setReport(true);
              tcr.setRealVul(true);
              if (!actualResultList.contains(tcr)) {
                actualResultList.add(tcr);
              }
            }
          } else if (line.contains("parse policy file from byte file failed, try xml file")) {
            continue;
          }
        }
      }
    } finally {
      reader.close();
    }
    return actualResultList;
  }

  /**
   * 2.2.生成actualresult.csv
   */
  public static void createActualResultCsv() throws Exception {
    Collection<TestCaseResult> actualResultList = createAcutalResultList();
    String fileName = "actualresults.csv";
    String[] csvHeaders = {"uri", "category", "realVul", "report"};
    createCsv(actualResultList, fileName, csvHeaders);
  }

  /**
   * 3.比较“expectResultList”和“actualResultList” 3.1.将actualResultList中的TestCaseResult对象字段report改为false
   */
  private static List<TestCaseResult> createActualResultListTmp() throws Exception {
    List<TestCaseResult> actualResultListTmp = new ArrayList<TestCaseResult>();
    List<TestCaseResult> actualResultList = createAcutalResultList();
    for (TestCaseResult actualtcr : actualResultList) {
      actualtcr.setReport(false);
      actualResultListTmp.add(actualtcr);
    }
    return actualResultListTmp;
  }

  /**
   * 3.2.生成vulhunterResultList
   */
  public static List<TestCaseResult> createVulhunterResultList() throws Exception {
    List<TestCaseResult> expectResultList = createExpectResultList();
    List<TestCaseResult> actualResultListTmp = createActualResultListTmp();
    List<TestCaseResult> vulhunterResultList = new ArrayList<TestCaseResult>();
    for (TestCaseResult expecttcr : expectResultList) {
      if (actualResultListTmp.contains(expecttcr)) {
        expecttcr.setReport(true);
      }
      vulhunterResultList.add(expecttcr);
    }
    return vulhunterResultList;
  }

  /**
   * 3.3.生成vulhunterresult.csv
   */
  public static void createVulhunterResultCsv() throws Exception {
    Collection<TestCaseResult> vulhunterResultList = createVulhunterResultList();
    String fileName = "vulhunterresults.csv";
    String[] csvHeaders = {"uri", "category", "realVul", "report"};
    createCsv(vulhunterResultList, fileName, csvHeaders);

  }

  /**
   * 3.3.生成所有异常情况的（误报，漏报）csv
   */
  public static void createErrorResultCsv() throws Exception {
    Collection<TestCaseResult> vulhunterResultList = createVulhunterResultList();
    Collection<TestCaseResult> errorResultList = new ArrayList<TestCaseResult>();
    String fileName = "errorResult.csv";
    String[] csvHeaders = {"uri", "category", "realVul", "report"};
    for (TestCaseResult actualtcr : vulhunterResultList) {
      boolean report = actualtcr.isReport();
      boolean realVul = actualtcr.isRealVul();
      if (!report && realVul) {
        errorResultList.add(actualtcr);
      } else if (report && !realVul) {
        errorResultList.add(actualtcr);
      }
    }
    createCsv(errorResultList, fileName, csvHeaders);
  }

  /**
   * 4.打分 4.1.分析vulhunterResultList 4.1.1.计算每个漏洞的漏报，误报
   */
  public static Map<String, Counter> calculateRuleIdScores(
      List<TestCaseResult> vulhunterResultList) {
    Map<String, Counter> map = new TreeMap<String, Counter>();
    for (TestCaseResult testCaseResult : vulhunterResultList) {
      String ruleId = testCaseResult.getCategory();
      Counter score = map.get(ruleId);
      if (score == null) {
        score = new Counter();
        map.put(ruleId, score);
      }
      if (testCaseResult.isRealVul() && testCaseResult.isReport()) {
        score.tp++;//正确率
      } else if (testCaseResult.isRealVul() && !testCaseResult.isReport()) {
        score.fn++; // 漏报
      } else if (!testCaseResult.isRealVul() && testCaseResult.isReport()) {
        score.tn++;  // 误报
      } else if (!testCaseResult.isRealVul() && !testCaseResult.isReport()) {
        score.fp++;//假漏报不用上报
      }
    }
    return map;
  }

  /**
   * 统计每个漏洞的正确率，误报率和漏报率
   */
  public static void createVulnerCounterCsv() throws Exception {
    List<TestCaseResult> vulhunterResultList = createVulhunterResultList();
    Map<String, Counter> results = calculateRuleIdScores(vulhunterResultList);
    List<VulnerCounter> vulnerCounterList = new ArrayList<VulnerCounter>();
    for (String category : results.keySet()) {
      Counter vulCounter = results.get(category);
      int vulTotal = vulCounter.tp + vulCounter.fp + vulCounter.fn + vulCounter.tn;
      NumberFormat numberFormat = NumberFormat.getPercentInstance();
      numberFormat.setMinimumFractionDigits(2);
      double fprDouble;
      if (vulCounter.fp == 0 && vulCounter.tn == 0) {
        fprDouble = 0.0;
      } else {
        fprDouble = (double) vulCounter.fp / (double) (vulCounter.fp + vulCounter.tn);
      }
      double tprDouble = (double) vulCounter.tp / (double) (vulCounter.tp + vulCounter.fn);
      double scoreDouble = tprDouble - fprDouble;
      String tpr = numberFormat.format(tprDouble);
      String fpr = numberFormat.format(fprDouble);
      String score = null;
      if (scoreDouble < 0) {
        score = "0.00%";
      } else {
        score = numberFormat.format(scoreDouble);
      }
      VulnerCounter vulnerCounter = new VulnerCounter();
      vulnerCounter.setCategory(category);
      vulnerCounter.setTotal(vulTotal);
      vulnerCounter.setTp(vulCounter.tp);
      vulnerCounter.setFn(vulCounter.fn);
      vulnerCounter.setTn(vulCounter.tn);
      vulnerCounter.setFp(vulCounter.fp);
      vulnerCounter.setTpr(tpr);
      vulnerCounter.setFpr(fpr);
      vulnerCounter.setScore(score);
      vulnerCounterList.add(vulnerCounter);
    }
    String[] csvHeader = {"Vulnerability", "Total", "TP", "FN", "TN", "FP", "TPR", "FPR", "Score"};
    createCsv(vulnerCounterList, "vulnercounter.csv", csvHeader);
  }

  /**
   * 4.1.2.计算工具的漏报，误报
   */
  public static OverallResults calculateToolScores(Map<String, Counter> results) {
    OverallResults or = new OverallResults();
    double totalScore = 0;
    double totalFPRate = 0;
    double totalTPRate = 0;
    int total = 0;
    int totalTP = 0;
    int totalFP = 0;
    int totalFN = 0;
    int totalTN = 0;
    for (String category : results.keySet()) {
      Counter c = results.get(category);
      int rowTotal = c.tp + c.fn + c.tn + c.fp;
      double tpr = (double) c.tp / (double) (c.tp + c.fn);
      double fpr = (double) c.fp / (double) (c.fp + c.tn);
//			double fdr = c.fp / ( c.tp + c.fp );

      // category score is distance from (fpr,tpr) to the guessing line
      double side = tpr - fpr;
      double hyp = side * Math.sqrt(2); // Pythagoras
      double raw = hyp / 2;
      double score = raw * Math.sqrt(2); // adjust scores to 0-1

      if (!Double.isNaN(score)) {
        totalScore += score;
      }
      totalFPRate += fpr;
      totalTPRate += tpr;
      total += rowTotal;
      totalTP += c.tp;
      totalFP += c.fp;
      totalFN += c.fn;
      totalTN += c.tn;

      or.add(category, tpr, fpr, rowTotal, score);
    }

    int resultsSize = results.size();
    or.setScore(totalScore / resultsSize);
    or.setFalsePositiveRate(totalFPRate / resultsSize);
    or.setTruePositiveRate(totalTPRate / resultsSize);
    or.setTotal(total);
    or.setFindingCounts(totalTP, totalFP, totalFN, totalTN);
    return or;
  }

  /**
   * 4.1.3.生成vulhunterscore.csv
   */
  public static void createVulhunterScoreCsv() throws Exception {
    List<TestCaseResult> vulhunterResultList = createVulhunterResultList();
    Map<String, Counter> scoreMap = calculateRuleIdScores(vulhunterResultList);
    OverallResults ors = calculateToolScores(scoreMap);
    Collection<OverallResult> osList = ors.getResults();
    String fileName = "vulhunterscore.csv";
    String[] csvHeaders = {"category", "truePositiveRate", "falsePositiveRate", "total", "score"};
    createCsv(osList, fileName, csvHeaders);
  }

  /**
   * 获取“engine.log”文件:
   */
  private static File getEngineLog() {
    if (engineHome == null || engineHome.equals("")) {
      logger.info(engineHome + " isn't exist");
      return null;
    }
    String logsName = engineHome + ENGINE_DEFAULT_HOME;
    File logsDirectory = new File(logsName);
    if (!(logsDirectory.exists()) || !(logsDirectory.isDirectory())) {
      logger.info("log file isn't exist");
      return null;
    }
    File[] logFiles = logsDirectory.listFiles();
    if (logFiles.length == 0) {
      logger.info("log file has no childfile");
      return null;
    }
    for (File file : logFiles) {
      String filename = file.getName();
      if (filename.endsWith(".log") && "vulhunter_engine.log".equals(filename)) {
        return file;
      }
    }
    return null;
  }

  /**
   * 创建csv文件
   *
   * @param tList 写入csv文件的对象集合
   * @param fileName 生成csv文件名
   * @param csvHeaders csv文件头部
   */
  private static <T> void createCsv(Collection<T> tList, String fileName, String[] csvHeaders) {
    /*String rootPath = System.getProperty("user.dir");
    String filePath =
        rootPath + File.separator + "spring-demo" + File.separator + "src" + File.separator
            + "scorecard" + File.separator + fileName;*/
    String filePath =
        "D:\\projects\\testdemo\\seczone-iast-demo\\spring-demo\\src\\scorecard\\" + fileName;
//        String filePath = rootPath + File.separator + "src" + File.separator + "scorecard" + File.separator + fileName;
    CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("GBK"));
    try {
      csvWriter.writeRecord(csvHeaders);
      for (T t : tList) {
        Field[] fields = t.getClass().getDeclaredFields();
        String[] csvContent = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
          Field field = fields[i];
          String fieldname = field.getName();
          String methodname = "";
          Class tcrClass = t.getClass();
          if (fieldname.equals("realVul") || fieldname.equals("report")) {
            methodname = "is" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
          } else {
            methodname = "get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
          }
          Method method = tcrClass.getDeclaredMethod(methodname, new Class[]{});
          Object value = method.invoke(t);

          if (value == null) {
            continue;
          }
          csvContent[i] = value.toString();
        }
        csvWriter.writeRecord(csvContent);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } finally {
      if (csvWriter != null) {
        csvWriter.close();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    //生成actualresults.csv
    createActualResultCsv();
    //生成vulhunterresults.csv
    createVulhunterResultCsv();
    //生成误报，漏报csv
    createErrorResultCsv();
    //统计每个漏洞的误报和漏报以及正确上报数
    createVulnerCounterCsv();
//        //生成vulhunterscore.csv
    createVulhunterScoreCsv();

  }
}
