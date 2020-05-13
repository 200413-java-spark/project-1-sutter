package app.database;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeesTest {
    public static Employees employees = new Employees();

    @Test
    public void testEmployeesDao() {
        Assert.assertTrue(employees.insert("Erik"));
        Assert.assertTrue(employees.delete("Erik"));
    }

}