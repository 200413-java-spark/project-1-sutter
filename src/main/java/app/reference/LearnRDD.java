package app.reference;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;

import scala.Tuple2;

public class LearnRDD {

    /*
        RESOURCES:
            RDD Programming Guide - https://spark.apache.org/docs/2.4.5/rdd-programming-guide.html 
            API Documentation - https://spark.apache.org/docs/2.4.5/api/java/org/apache/spark/api/java/package-summary.html
    */

    public static final String filePath = new File("src/main/resources/learn-rdd-data.txt").getAbsolutePath();
    
    public static void learnRDD() {

        // CONFIGURATION & CONTEXT
        SparkConf conf = new SparkConf().setAppName("project-1-sutter").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        // PARALLELIZED COLLECTIONS
        // List<Integer> dataset = Arrays.asList(1, 2, 3, 4, 5);
        // // let spark set the number of partitions:
        // JavaRDD<Integer> distData = sc.parallelize(dataset);
        // // manually set the number of partitions:
        // //JavaRDD<Integer> distData2 = sc.parallelize(dataset, 3);
        // // note: typically you want 2-4 partitions for each CPU in your cluster
        
        // EXTERNAL DATASETS
        // //JavaRDD<String> distFile = sc.textFile(filePath);
        // JavaRDD<String> distFile = sc.textFile(new File("src/main/resources/lease-data-sample.csv").getAbsolutePath());
        
        // OPERATIONS
        JavaRDD<String> lines = sc.textFile(filePath);
        JavaRDD<Integer> lineLengths = lines.map(s -> s.length());
        // save in memory after first computation:
        lineLengths.persist(StorageLevel.MEMORY_ONLY()); // identical to: lineLengths.cache()
        // execute computation:
        int totalLength = lineLengths.reduce((a, b) -> a + b);

        // END SPARK PROCESS
        sc.close();

    }

    public static void reduceByKeyExample() {
        
        SparkConf conf = new SparkConf().setAppName("project-1-sutter").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        // get number of items in an RDD using JavaRDD.count()
        // final List<String> names = new ArrayList<>(Arrays.asList("Mehrab", "Anthony", "Sutter", "Brandon", "Pejal", "Phuc", "Cynthia", "John", "Jeff", "Jeremy", "Christian", "Daniel", "Garrison", "Jacob", "Jay", "Mason"));
        // long count = sparkContext.parallelize(names).count();
        // System.out.println("\nCOUNT: " + count + "\n");

        List<String> names = new ArrayList<>(Arrays.asList("Matt", "Mark", "Mason", "Michael", "Mark", "Matt", "Matt", "Michael", "Matt", "Matt", "Mark"));
        JavaRDD<String> namesRDD = sc.parallelize(names, 4); // make 4 partitions
        JavaPairRDD<String, Integer> namesMapper = namesRDD.mapToPair((f) -> new Tuple2<>(f, 1)); // make pair for each item in the RDD (count of 1 for each)
        System.out.println("\nNames Mapper:\n" + namesMapper.collect() + "\n");
        JavaPairRDD<String, Integer> countNames = namesMapper.reduceByKey((x, y) -> ((int) x + (int) y)); // reduce by key
        System.out.println("\nCount of each name:\n" + countNames.collect() + "\n");

        sc.close();

    }

}