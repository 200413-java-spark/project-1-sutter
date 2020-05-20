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

import app.database.States;
import app.types.State;
import scala.Tuple2;

public final class BatchJob {
    
    public static final SparkConf config = new SparkConf().setAppName("project-1-sutter").setMaster("local");
    public static JavaSparkContext job;
    public static final String filePath = new File("src/main/resources/gov-lease-data.csv").getAbsolutePath();
    public static final String filePathTest = new File("src/main/resources/gov-lease-data-test.csv").getAbsolutePath();
    public static JavaRDD<String> data;
    public static String status = "PENDING";

    public static void execute() {
        if (!status.equals("PENDING")) return;
        else status = "IN PROGRESS";
        job = new JavaSparkContext(config);
        data = job.textFile(filePath);
        calculateLeaseCountByState(data);
        job.close();
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
        States dao = new States();
        dao.insertMany(states);

    }

}