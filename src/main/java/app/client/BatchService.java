package app.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.batch.BatchJob;

public class BatchService extends HttpServlet {
    private String message;
    private static boolean batchJobExecuted = false;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (batchJobExecuted || !BatchJob.status.equals("PENDING")) {
            message = "\n The batch job has already been executed \n";
        } else {
            BatchJob.execute();
            batchJobExecuted = true;
            //message = "\n The batch job was successfully triggered \n\n The job is now in progress... \n";
            message = "\n The batch job was successfully executed \n";
        }
        response.getWriter().println(message);
        response.setContentType("text/html");
    }

}