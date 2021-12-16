package cn.webank.rmb.message;

import java.util.Random;

/**
 * Created by hujj on 2020-07-06.
 */
public class WeMQMessageUtil {

  public static Message extractRMBMessageFromWeMQMessage(
      org.apache.rocketmq.common.message.Message weMqMessage) {
    String body = new String(weMqMessage.getBody());
    Message message = new Message();
    message.setContent(body);
//    Destination destination = new Destination();
    SimpleDestination destination = new SimpleDestination();
    destination.setServiceOrEventId(String.valueOf(System.nanoTime()));
    destination.setDcnNo("M10" + new Random().nextInt(20));
    destination.setScenario(String.format("%02d", new Random().nextInt(99)));
    destination.setOrganizationId("99996");
    destination.setOrganizationIdInputFlag("1");
    destination.setName("com.seczone.RMBMessageService.addBatchAndCleanBatch");
    message.setDestination(destination);

    AppHeader appHeader = new AppHeader();
    appHeader.setTransCode("zfb00" + new Random().nextInt(9));
    message.setAppHeader(appHeader);

    SysHeader sysHeader = new SimpleSysHeader();
//    SysHeader sysHeader = new SysHeader();
    sysHeader.setBizSeqNo("2007210KA03998532994370700232533");
    sysHeader.setConsumerSeqNo("2007211KA03998532994370800232534");
    sysHeader.setConsumerSvrId("10.107.118.248");
    sysHeader.setRmbVersion("2.1.9");
    message.setSysHeader(sysHeader);

    return message;
  }


}
