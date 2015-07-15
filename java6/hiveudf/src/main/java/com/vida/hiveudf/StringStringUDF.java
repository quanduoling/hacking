package com.vida.hiveudf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class StringStringUDF extends UDF {
  public String evaluate(String s1, String s2) {
    return s1 + s2;
  }
}
