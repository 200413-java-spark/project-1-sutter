package app.batch;

import java.io.File;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class BatchJobTest {

    public static final SparkConf config = new SparkConf().setAppName("project-1-sutter").setMaster("local");
    public static JavaSparkContext app;
    public static final String filePath = new File("src/test/resources/lease-data-sample-small.csv").getAbsolutePath();
    public static JavaRDD<String> data;

    @BeforeClass
    public static void loadData() {
        app = new JavaSparkContext(config);
        data = app.textFile(filePath);
    }

    @Test
    public void testBatchJob(){
        BatchJob.calculateLeaseCountByState(data);
    }

    @AfterClass
    public static void closeContext() {
        app.close();
    }

}