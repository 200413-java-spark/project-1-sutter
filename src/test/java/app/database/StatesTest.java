package app.database;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import app.types.State;

public class StatesTest {

    public static States dao = new States();
    public static List<State> states = new ArrayList<State>();

    @BeforeClass
    public static void setup() {
        states.add(new State("NY", 782));
        states.add(new State("TX", 568));
        states.add(new State("HI", 139));
    }

    @Test
    public void testInsertMany(){
        Assert.assertEquals(0, dao.count());
        Assert.assertTrue(dao.insertMany(states));
        Assert.assertEquals(3, dao.count());
    }

    @Test
    public void testSelect(){
        State state = dao.select(new State("TX"));
        Assert.assertEquals(568, state.leaseCount);
    }

    @AfterClass
    public static void clearTable() {
        dao.deleteAll();
        Assert.assertEquals(0, dao.count());
    }

}