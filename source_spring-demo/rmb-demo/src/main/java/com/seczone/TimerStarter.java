package com.seczone;

import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerStarter {

  private static final Logger logger = LoggerFactory
      .getLogger(TimerStarter.class);

  private static TimerTaskEvent[] tasksInEachMin = {new AppActivityEvent()};
  private static TimerTaskEvent[] tasksInEach30Secs = {new TestFixUntrustedDeserialEvent()};
  private static TimerTaskEvent[] tasksInEach3Secs = {new TestUntrustedDeserialEvent()};


  public static void start() {
    Timer timer = new Timer(true);

    timer.schedule(new TimerTask() {
      public void run() {
        executeTaskAnyway(tasksInEachMin);
      }
    }, 60 * 1000, 60 * 1000);
    timer.schedule(new TimerTask() {
      public void run() {
        executeTaskAnyway(tasksInEach30Secs);
      }
    }, 30 * 1000, 30 * 1000);

    timer.schedule(new TimerTask() {
      public void run() {
        executeTaskAnyway(tasksInEach3Secs);
      }
    }, 20 * 1000, 3 * 1000);

  }

  private static void executeTaskAnyway(TimerTaskEvent[] tasks) {
    for (TimerTaskEvent task : tasks) {
      try {
        task.execute();
      } catch (Exception t) {
        logger.error(t.getMessage());
      }
    }
  }

}
