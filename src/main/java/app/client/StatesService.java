package app.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.database.States;

public class StatesService extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (new States().count() <= 0) {
            String message = "\n\n The batch job is currently pending... \n\n You must first execute the batch job... \n\n";
            response.getWriter().println(message);
            response.setContentType("text/html");
            return;
        }
        

    }
}