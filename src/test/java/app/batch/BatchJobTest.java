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

import app.database.States;

@Ignore
public class BatchJobTest {

    public static final SparkConf config = new SparkConf().setAppName("project-1-sutter").setMaster("local");
    public static JavaSparkContext app;
    public static final String filePath = new File("src/test/resources/gov-lease-data-test-small.csv").getAbsolutePath();
    public static JavaRDD<String> data;
    public static States databaseTable = new States();

    @BeforeClass
    public static void loadData() {
        app = new JavaSparkContext(config);
        data = app.textFile(filePath);
    }

    @Test
    public void testBatchJob(){
        BatchJob.calculateLeaseCountByState(data);
        Assert.assertEquals(3, databaseTable.count());
        Assert.assertTrue(databaseTable.deleteAll());
    }

    @AfterClass
    public static void closeContext() {
        app.close();
    }

}