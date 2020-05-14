package app.types;

public class State {
    
    public int id;
    public String state;
    public int leaseCount;

    public State(String state) {
        this.state = state;
    }

    public State(String state, int leaseCount) {
        this.state = state;
        this.leaseCount = leaseCount;
    }

    public State(int id, String state, int leaseCount) {
        this.id = id;
        this.state = state;
        this.leaseCount = leaseCount;
    }

}