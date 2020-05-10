package app.batch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public final class BatchJob {

    public static void countNames() {
        
        final SparkConf conf = new SparkConf().setAppName("Project 1 Mehrab").setMaster("local");
        final JavaSparkContext sparkContext = new JavaSparkContext(conf);
        
        // get number of items in an RDD using JavaRDD.count()
        // final List<String> names = new ArrayList<>(Arrays.asList("Mehrab", "Anthony", "Sutter", "Brandon", "Pejal", "Phuc", "Cynthia", "John", "Jeff", "Jeremy", "Christian", "Daniel", "Garrison", "Jacob", "Jay", "Mason"));
        // long count = sparkContext.parallelize(names).count();
        // System.out.println("\nCOUNT: " + count + "\n");

        final List<String> names = new ArrayList<>(Arrays.asList("Matt", "Mark", "Mason", "Michael", "Mark", "Matt", "Matt", "Michael", "Matt", "Matt", "Mark"));
        final JavaRDD<String> namesRDD = sparkContext.parallelize(names, 4); // make 4 partitions
        final JavaPairRDD<String, Integer> namesMapper = namesRDD.mapToPair((f) -> new Tuple2<>(f, 1)); // make pair for each item in the RDD (count of 1 for each)
        System.out.println("\nNames Mapper:\n" + namesMapper.collect() + "\n");
        final JavaPairRDD<String, Integer> countNames = namesMapper.reduceByKey((x, y) -> ((int) x + (int) y)); // reduce by key
        System.out.println("\nCount of each name:\n" + countNames.collect() + "\n");

        sparkContext.close();
    }
    
    public static void learnRDD() {

        // CONFIGURATION & CONTEXT
        SparkConf config = new SparkConf().setAppName("project-1-sutter").setMaster("local");
        JavaSparkContext app = new JavaSparkContext(config);
        
        // PARALLELIZED COLLECTIONS
        List<Integer> dataset = Arrays.asList(1, 2, 3, 4, 5);
        // let spark set the number of partitions:
        JavaRDD<Integer> distData = app.parallelize(dataset);
        // manually set the number of partitions:
        //JavaRDD<Integer> distData2 = app.parallelize(dataset, 3);
        // note: typically you want 2-4 partitions for each CPU in your cluster
        
        // EXTERNAL DATASETS
        //JavaRDD<String> distFile = app.textFile(new File("src/main/resources/lease-data-sample.csv").getAbsolutePath().toString());

        // END SPARK PROCESS
        app.close();
    }

}