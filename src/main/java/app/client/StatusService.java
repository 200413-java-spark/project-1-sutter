package app.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.batch.BatchJob;

public class StatusService extends HttpServlet {
    String message;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = BatchJob.status;
        message = "\n Batch job status: " + status + "\n";
        response.getWriter().println(message);
        response.setContentType("text/html");
    }

}