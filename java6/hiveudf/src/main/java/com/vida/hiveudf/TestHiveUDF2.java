package com.vida.hiveudf;

import org.apache.hadoop.hive.ql.exec.UDF;

public final class TestHiveUDF2 extends UDF {
  public String evaluate(final String s) {
    return "Hello " + s;
  }
}
