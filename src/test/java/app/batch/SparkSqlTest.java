package app.batch;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SparkSqlTest {
    private static SparkSql sparkSql;
    private static final String INPUT_FILE = "src/test/resources/gov-lease-data-test.csv";
    private static final String INPUT_FILE_TEST = "src/test/resources/gov-lease-data-test-small.csv";

    @BeforeClass
    public static void before() {
        sparkSql = new SparkSql();
        sparkSql.load(INPUT_FILE_TEST);
    }

    @Test
    public void example() {
        sparkSql.getLeaseCountByState();
    }

    @AfterClass
    public static void after() {
        sparkSql.closeSession();
    }

}