package app.reference;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

public final class FunctionalInterfaces {
    
    // OPTION 1
    public static void optionOneExample() {
        class GetLength implements Function<String, Integer> {
            public Integer call(String s) { return s.length(); }
        }
        
        class Sum implements Function2<Integer, Integer, Integer> {
            public Integer call(Integer a, Integer b) { return a + b; }
        }
        SparkConf config = new SparkConf().setAppName("project-1-sutter").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(config);
        JavaRDD<String> lines = sc.textFile("data.txt");
        JavaRDD<Integer> lineLengths = lines.map(new GetLength());
        int totalLength = lineLengths.reduce(new Sum());
        System.out.println("\nTOTAL LENGTH: " + totalLength + "\n");
        sc.close();
    }

    // INLINE FUNCTIONS
    public static void inlineFunctionsExample() {
        SparkConf config = new SparkConf().setAppName("project-1-sutter").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(config);
        JavaRDD<String> lines = sc.textFile("data.txt");
        JavaRDD<Integer> lineLengths = lines.map(new Function<String, Integer>() {
            public Integer call(String s) { return s.length(); }
        });
        int totalLength = lineLengths.reduce(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer a, Integer b) { return a + b; }
        });
        System.out.println("\nTOTAL LENGTH: " + totalLength + "\n");
        sc.close();
    }

    // LAMBDA EXPRESSIONS
    public static void lambdaExpressionsExample() {
        SparkConf config = new SparkConf().setAppName("project-1-sutter").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(config);
        JavaRDD<String> lines = sc.textFile("data.txt");
        JavaRDD<Integer> lineLengths = lines.map(s -> s.length());
        int totalLength = lineLengths.reduce((a, b) -> a + b);
        System.out.println("\nTOTAL LENGTH: " + totalLength + "\n");
        sc.close();
    }
    
}