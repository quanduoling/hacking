package com.vida.xmlrdd;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.hadoop.compression.lzo.LzoCodec;
import com.hadoop.mapreduce.LzoTextInputFormat;

import java.io.IOException;
import java.util.Arrays;

public class ReadLZOFile {
  public static void main(String[] args) throws IOException {
    SparkConf conf = new SparkConf()
        .setAppName("Wikipedia XML data")
        .setMaster("local[4]");
    JavaSparkContext sc = new JavaSparkContext(conf);

//    JavaRDD<String> rdd = sc.textFile("src/main/resources/test.csv.lzo");
//    System.out.println("Next line is from the lzo file:");
//    System.out.println(rdd.first());

    Configuration configuration = new Job().getConfiguration();
    configuration.set("io.compression.codecs",
        "com.hadoop.compression.lzo.LzoCodec,com.hadoop.compression.lzo.LzopCodec,org.apache.hadoop.io.compress.SnappyCodec");

    JavaPairRDD<LongWritable, Text> rdd = sc.newAPIHadoopFile(
        "src/main/resources/test.csv.lzo",
        LzoTextInputFormat.class,
        LongWritable.class,
        Text.class,
        configuration);

    System.out.println("LZO File");
    System.out.println(rdd.count());
  }
}
