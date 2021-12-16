package com.seczone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TimerTaskEvent {

  private static final Logger logger = LoggerFactory
      .getLogger(TimerTaskEvent.class);

  public abstract void run();

  public void execute() {
    try {
      this.run();
    } catch (Throwable t) {
      logger.error("execute TimerTaskEvent exception: ", t);
    }
  }

}
