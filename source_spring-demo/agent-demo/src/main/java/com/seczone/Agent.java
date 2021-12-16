package com.seczone;

import com.seczone.transformer.MyClassTransformer;
import java.lang.instrument.Instrumentation;

public class Agent {

  public static void premain(String agentArgs, Instrumentation inst) {
    inst.addTransformer(new MyClassTransformer(), true);
  }
}
