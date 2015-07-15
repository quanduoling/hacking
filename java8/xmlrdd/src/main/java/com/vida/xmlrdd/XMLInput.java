package com.vida.xmlrdd;

//import edu.umd.cloud9.collection.XMLInputFormat;
import org.apache.spark.SparkConf;

import edu.umd.cloud9.collection.XMLInputFormatOld;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;


//  % spark-submit --class "com.vida.xmlrdd.XMLInput" --master=local[4] target/uber-xml-rdd-1.0.jar
public class XMLInput {
  public static void main(String[] args) {
    // Create a Spark Context.
    SparkConf conf = new SparkConf().setAppName("Wikipedia XML data");
    JavaSparkContext sc = new JavaSparkContext(conf);

    sc.hadoopConfiguration().set("xmlinput.start", "<page>\n");
    sc.hadoopConfiguration().set("xmlinput.end", "</page>\n");

    // This takes 14 minutes to process locally.
    JavaPairRDD<LongWritable, Text> rdd = sc.hadoopFile(
    "/Users/vida/Data/wikipedia/enwiki-latest-pages-articles27.xml-p029625017p044065755.bz2",
    XMLInputFormatOld.class, LongWritable.class, Text.class);
    rdd.repartition(2);

//    JavaPairRDD<LongWritable, Text> rdd = sc.newAPIHadoopFile(
//        "/Users/vida/Data/wikipedia/enwiki-latest-pages-articles1.xml-p000000010p000010000.bz2",
//        XMLInputFormat.class, LongWritable.class, Text.class,
//        new Configuration());

    long start = System.currentTimeMillis();
    System.out.println(rdd.map(t -> t._1().toString()).count());
    long end = System.currentTimeMillis();

    System.out.println("Total time is: " + (end - start));
    }
}