package com.vida.runc

import java.nio.charset.Charset

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Attempt to run the /tmp/simple c program.
 *
 * gcc -o /tmp/simple simple.c
 */
object RunC {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
      .setAppName("RunCProgram")
      .setMaster("local[1]")
    val sc = new SparkContext(sparkConf)

    val names = sc.parallelize(Array("Vida", "John", "Pat", "Parviz", "Prakash", "Richard", "Arsalan"))

    //System.setProperty("file.encoding", "ISO-8859-1")

    println("The Default charset is: " + Charset.defaultCharset())

    val piped = names.pipe(Seq("/tmp/simple")) //.map(s => s.toString())

    println("The number of elements in names is: " + names.count())
    println("The number of elements in piped is: " + piped.count())
    piped.collect().map(println(_))

    sc.stop()
  }
}
