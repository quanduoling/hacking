package com.vida.hiveudf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.List;

public class ListListUDF extends UDF {
  /**
   *
   * @param bitrateUsagesObj
   *   SQL schema: array<struct<bitratekbps:int, playtimems:int, bufftimems:int>>
   *   Java Type: List<List<Integer>>
   * @return
   */
  public long evaluate(Object bitrateUsagesObj) {
    if (bitrateUsagesObj == null) {
      return 0l;
    }
    @SuppressWarnings("unchecked")
    List<List> bitrateUsages = (List<List>) bitrateUsagesObj;
    long retVal = 0;
    for (int i = 0; i < bitrateUsages.size(); i++) {
      @SuppressWarnings("unchecked")
      List<Object> brList = (List<Object>) bitrateUsages.get(i);
      @SuppressWarnings("unchecked")
      Integer playTime = (Integer) brList.get(1);
      try {
        retVal +=  (long)(playTime.intValue());
      } catch (NullPointerException e) {
        System.out.println(e);
      }
    }

    return retVal;
  }
}
