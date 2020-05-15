package app.batch;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.storage.StorageLevel;

import app.database.States;
import app.types.State;
import scala.Tuple2;

public final class BatchJob {
    public static final SparkConf config = new SparkConf().setAppName("project-1-sutter").setMaster("local");
    public static JavaSparkContext app;
    public static final String filePath = new File("src/main/resources/gov-lease-data.csv").getAbsolutePath();
    public static JavaRDD<String> data;
    public static String status = "PENDING";

    public static void execute() {
        if (!status.equals("PENDING")) return;
        else status = "IN PROGRESS";

        app = new JavaSparkContext(config);
        data = app.textFile(filePath);
        calculateLeaseCountByState(data);
        app.close();
        
        status = "COMPLETE";
    }

    public static void calculateLeaseCountByState(JavaRDD<String> rdd) {
    
        // split each line of the csv file
        JavaRDD<ArrayList<String>> lines = rdd.map(new Function<String, ArrayList<String>>() {
            public ArrayList<String> call(String line) {
                //String[] arr = line.split(",");
                String[] arr = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(arr));
                return list;
            }
        });

        // remove the first line from the collection (the headers)
        JavaRDD<ArrayList<String>> noHeaders = lines.filter(arr -> !arr.get(0).equals("Lease Number"));

        // generate pairs according to U.S. state
        JavaPairRDD<String, Integer> pairs = noHeaders.mapToPair(new PairFunction<ArrayList<String>,String,Integer>() {
            public Tuple2<String, Integer> call(ArrayList<String> arr) {
                String state = arr.get(15);
                Integer num = 1;
                return new Tuple2(state, num);
            }
        });

        // get lease counts for each state
        JavaPairRDD<String, Integer> leaseCounts = pairs.reduceByKey((x, y) -> ((int) x + (int) y));

        // convert list of tuples to list of State objects
        List<State> states = new ArrayList<State>();
        List<Tuple2<String, Integer>> result = leaseCounts.collect();
        String state;
        Integer leaseCount;
        for (Tuple2<String, Integer> pair : result ) {
            state = pair._1();
            leaseCount = pair._2();
            states.add(new State(state, leaseCount));
        }

        // clean up data
        states.removeIf(item -> item.state.isEmpty() || item.state.equals("US") || item.state.equals("DC") || item.state.equals("PR") || item.state.equals("BC") || item.state.equals("PQ") || item.state.equals("VI") || item.state.equals("MP") || item.state.equals("NU") || item.state.equals("AS") || item.state.equals("GU"));

        // // persist to database
        States database = new States();
        database.insertMany(states);

    }

    public static void countNames() {
        
        SparkConf conf = new SparkConf().setAppName("project-1-sutter").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        
        // get number of items in an RDD using JavaRDD.count()
        // final List<String> names = new ArrayList<>(Arrays.asList("Mehrab", "Anthony", "Sutter", "Brandon", "Pejal", "Phuc", "Cynthia", "John", "Jeff", "Jeremy", "Christian", "Daniel", "Garrison", "Jacob", "Jay", "Mason"));
        // long count = sparkContext.parallelize(names).count();
        // System.out.println("\nCOUNT: " + count + "\n");

        List<String> names = new ArrayList<>(Arrays.asList("Matt", "Mark", "Mason", "Michael", "Mark", "Matt", "Matt", "Michael", "Matt", "Matt", "Mark"));
        JavaRDD<String> namesRDD = sparkContext.parallelize(names, 4); // make 4 partitions
        JavaPairRDD<String, Integer> namesMapper = namesRDD.mapToPair((f) -> new Tuple2<>(f, 1)); // make pair for each item in the RDD (count of 1 for each)
        System.out.println("\nNames Mapper:\n" + namesMapper.collect() + "\n");
        JavaPairRDD<String, Integer> countNames = namesMapper.reduceByKey((x, y) -> ((int) x + (int) y)); // reduce by key
        System.out.println("\nCount of each name:\n" + countNames.collect() + "\n");

        sparkContext.close();
    }
    
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
        // //JavaRDD<String> distFile = sc.textFile("data.txt");
        // JavaRDD<String> distFile = sc.textFile(new File("src/main/resources/lease-data-sample.csv").getAbsolutePath().toString());
        
        // OPERATIONS
        JavaRDD<String> lines = sc.textFile("data.txt");
        JavaRDD<Integer> lineLengths = lines.map(s -> s.length());
        // save in memory after first computation:
        lineLengths.persist(StorageLevel.MEMORY_ONLY()); // identical to: lineLengths.cache()
        // execute computation:
        int totalLength = lineLengths.reduce((a, b) -> a + b);

        // END SPARK PROCESS
        sc.close();
    }

}