package com.databricks.protobuf;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.JavaSchemaRDD;

import java.io.IOException;
import java.util.ArrayList;

import static com.databricks.protobuf.AddressBookProtos.AddressBook;
import static com.databricks.protobuf.AddressBookProtos.Person;

public class Main {



    public static void main(String[] args) throws IOException{
      SparkConf conf = new SparkConf().setAppName("Log Analyzer");
      JavaSparkContext sc = new JavaSparkContext(conf);

      JavaSQLContext sqlContext = new JavaSQLContext(sc);


      AddressBook.Builder addressBook = AddressBook.newBuilder();

      Person john =
          Person.newBuilder()
              .setId(1234)
              .setName("John Doe")
              .setEmail("jdoe@example.com")
              .addPhone(
                  Person.PhoneNumber.newBuilder()
                      .setNumber("555-4321")
                      .setType(Person.PhoneType.HOME))
              .build();

      Person jane =
          Person.newBuilder()
              .setId(1235)
              .setName("Jane Doe")
              .setEmail("janedoe@example.com")
              .addPhone(
                  Person.PhoneNumber.newBuilder()
                      .setNumber("555-5321")
                      .setType(Person.PhoneType.HOME))
              .build();


      addressBook.addPerson(john);
      addressBook.addPerson(jane);

      ArrayList<AddressBook> addressBookList = new ArrayList<AddressBook>();
      addressBookList.add(addressBook.build());


      JavaRDD<AddressBook> rdd = sc.parallelize(addressBookList);
      JavaSchemaRDD schemaRDD = sqlContext.applySchema(rdd, AddressBook.class);
      schemaRDD.registerTempTable("Test");
      sqlContext.sql("select * from Test");

//      FileOutputStream output = new FileOutputStream("/Users/vida/Code/hacking/java7/protobuf/address-book.protoout");
//      addressBook.build().writeTo(output);
//      output.close();
    }
}
