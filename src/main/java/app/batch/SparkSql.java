package app.batch;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;

public class SparkSql {
    private SparkSession session;
    private Dataset<Row> data;

    public void load(String filePath) {
        session = SparkSession
                    .builder()
                    .appName("spark-sql-examples")
                    .master("local[*]")
                    //.config("spark.some.config.option", "some-value")
                    .getOrCreate();
        data = session
            .read()
            .option("header", true)
            .csv(filePath);
    }

    public void closeSession() {
        session.close();
    }

    public void getLeaseCountByState() {
        
        //data.printSchema();
        //data.show();

        Dataset<Row> df = data
            .withColumn("country", col("Lessor Country"))
            .withColumn("state", col("Lessor State"))
            .select(col("country"), col("state"))
            .filter(col("country").equalTo("US"));
        df.show();
        
        Dataset<Row> counts = df
            .groupBy(col("state"))
            .count();
        counts.show();

    }
    
}