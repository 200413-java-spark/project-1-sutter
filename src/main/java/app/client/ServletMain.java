package app.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class ServletMain extends HttpServlet {

    private String message;

    public void init() throws ServletException {
       message = "Hello World!";
    }

    @Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		if (firstName != null && lastName != null) response.getWriter().println("Hello, " + firstName + " " + lastName + "!");
        else response.getWriter().println(message);
        response.setContentType("text/html");
    }

    // public void destroy() {
    //     // implement if necessary...
    //  }

}