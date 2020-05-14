package app.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.batch.BatchJob;
import app.database.States;
import app.types.State;

public class StatesService extends HttpServlet {
    private String message;
    private static States states = new States();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String job = BatchJob.status;
        String param = request.getParameter("state");
        if (job.equals("PENDING")) {
            message = "\n The batch job is currently pending... \n\n You must first execute the batch job... \n";
        } else if (job.equals("IN PROGRESS")) {
            message = "\n The batch job is currently in progress... \n\n You must wait for the batch job to complete... \n";
        } else if (param == null) {
            message = "\n Request parameter 'state' is missing \n\n Example: state=TX \n";
        } else if (param.length() != 2) {
            message = "\n The value of request parameter 'state' is invalid \n\n Expected format: state=TX \n";
        } else {
            State state = states.select(new State(param));
            if (state == null) {
                message = "\n Invalid state abbreviation \n";
            } else {
                Integer leaseCount = state.leaseCount;
                message = "\n The government of " + state.state + " has " + leaseCount.toString() + " real estate leases \n";
            }
        }
        response.getWriter().println(message);
        response.setContentType("text/html");
    }

}