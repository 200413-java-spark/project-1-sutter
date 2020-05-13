package app.types;

public class State {
    public int id;
    public String state;
    public int leaseCount;

    State(String state, String leaseCount) {
        this.state = state;
        this.leaseCount = leaseCount;
    }

    State(int id, String state, String leaseCount) {
        this.id = id;
        this.state = state;
        this.leaseCount = leaseCount;
    }

}