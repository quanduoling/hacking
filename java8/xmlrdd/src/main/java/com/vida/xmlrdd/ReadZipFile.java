package com.vida.xmlrdd;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.IOException;
import java.util.Arrays;

public class ReadZipFile {

  public static void main(String[] args) throws IOException {
    SparkConf conf = new SparkConf()
        .setAppName("Wikipedia XML data")
        .setMaster("local[4]");
    JavaSparkContext sc = new JavaSparkContext(conf);

//    JavaRDD<String> rdd = sc.textFile("src/main/resources/LoanStats3a.csv.zip");
//    System.out.println("Next line is from the zip file:");
//    System.out.println(rdd.first());

    JavaPairRDD<Text, BytesWritable> rdd = sc.newAPIHadoopFile(
        "src/main/resources/LoanStats3a.csv.zip",
        ZipFileInputFormat.class,
        Text.class,
        BytesWritable.class,
        new Job().getConfiguration());

    JavaRDD<String> lines = rdd.flatMap(s -> Arrays.asList(new String(s._2().getBytes()).split("\n")));

    System.out.println("Next line is from the zip file:");
    System.out.println(rdd.count());
    System.out.println(lines.take(10));
  }
}
