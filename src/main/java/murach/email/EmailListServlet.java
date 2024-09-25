package murach.email;
import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import murach.business.User;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class EmailListServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EmailListServlet.class.getName());

    @Override
    public void init() throws ServletException {
        try {
            // Set up a FileHandler to write logs to a file
            FileHandler fileHandler = new FileHandler("D:\\Workspace\\WebProgramming\\example_intelji\\chap05_ex1_email\\src\\main\\email_list_servlet.log", true);

            // Set a simple formatter for the log file
            fileHandler.setFormatter(new SimpleFormatter());

            // Add the FileHandler to the logger
            logger.addHandler(fileHandler);

            // Optional: set the logger level to control what types of messages are logged
            logger.setLevel(java.util.logging.Level.INFO);
        } catch (IOException e) {
            throw new ServletException("Failed to initialize log file", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Log and print that the request is being handled by doPost


        String url = "/index.html";

        // get current action
        String action = request.getParameter("action");

        logger.info("Action parameter: " + action);
        System.out.println("Action parameter: " + action);

        if (action == null) {
            action = "join";  // default action
        }
        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.html";    // the "join" page
        }
        else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // store data in User object and save User object in db
            User user = new User(firstName, lastName, email);
            //  UserDB.insert(user);

            // set User object in request object and set URL
            request.setAttribute("user", user);
            url = "/thanks.jsp";   // the "thanks" page
        }

        // forward request and response objects to specified URL
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Log and print that the request is being handled by doGet
        doPost(request, response);
    }


}
